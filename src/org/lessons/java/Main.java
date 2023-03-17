package org.lessons.java;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Ask user event's data
        Scanner scan = new Scanner(System.in);
        System.out.print("How many events you want to add? ");
        int count = Integer.parseInt(scan.nextLine());
        List<Event> eventList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            System.out.println("----ADD NEW EVENT----");
            System.out.print("Add the event title: ");
            String title = scan.nextLine();

            int year = 0;
            int month = 0;
            int day = 0;
            int totalSeats = 0;
            LocalDate date = null;
            boolean stop = false;

            while (!stop){
                try {
                    System.out.print("Add the event year: ");
                    year = Integer.parseInt(scan.nextLine());
                    System.out.print("Add the event month: ");
                    month = Integer.parseInt(scan.nextLine());
                    System.out.print("Add the event day: ");
                    day = Integer.parseInt(scan.nextLine());
                    date = LocalDate.of(year, month, day);
                    System.out.print("Add the total seats: ");
                    totalSeats = Integer.parseInt(scan.nextLine());
                    stop = true;
                } catch (NumberFormatException e){
                    System.out.println("Please, add a number");
                } catch (DateTimeException e){
                    System.out.println("Please, add a valid date");
                }
            }

            //Create event istance
            Event event = null;
            try{
                event = new Event(title, date, totalSeats);
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                return;
            }

            //Book a seat
            stop = false;
            while(!stop){
                System.out.print("Do you want to book a seat? 1 - Yes | 2 - No ");
                String userChoice = scan.nextLine();

                if (userChoice.equals("1")) {
                    System.out.print("How many seat? ");

                    try{
                        int seats = Integer.parseInt(scan.nextLine());

                        if (seats <= event.countRemainingSeats() && seats > 0) {
                            for (int j = 0; j < seats; j++) {
                                event.bookSeat();
                            }
                            System.out.println("Seats booked: " + seats + ". Remaining seats: " + event.countRemainingSeats());
                        } else if(seats < 0) {
                            System.out.println("Add a valid number");
                        } else {
                            System.out.println("There are not " + seats + " seats available. Remainig seats: " + event.countRemainingSeats());
                        }
                    } catch (NumberFormatException e){
                        System.out.println("Please, add a valid number");
                    }


                } else if (userChoice.equals("2")) {
                        System.out.println("Thank you for reaching out");
                        stop = true;
                } else {
                        System.out.println("Invalid input");
                }
            }

            //Cancel
            stop = false;
            while (!stop){
                System.out.print("Do you want to cancel a booked seat? 1 - Yes | 2 - No ");
                String userChoice = scan.nextLine();
                if (userChoice.equals("1")){

                    System.out.print("How many booked seats do you want to cancel? ");

                    try{
                        int seatToCancel = Integer.parseInt(scan.nextLine());
                        if(seatToCancel > 0){
                            for (int j = 0; j < seatToCancel; j++) {
                                event.cancel();
                            }

                            System.out.println("Canceled seats: " + seatToCancel + ". Total seats remaining: " + event.countRemainingSeats());
                        } else {
                            System.out.println("Add a number greater than zero");
                        }
                    } catch (NumberFormatException e){
                        System.out.println("Please add a valid number");
                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }

                } else if(userChoice.equals("2")){
                    System.out.println("Thank you for reaching out");
                    stop = true;
                } else {
                    System.out.println("Invalid input");
                }
            }

            eventList.add(event);
        }



        Concert concert = new Concert("Twenty One Pilots Live", LocalDate.now().plusWeeks(3), 3000, LocalTime.of(20, 30), new BigDecimal("30.99"));
        System.out.println(concert);

        EventSchedule eventSchedule = new EventSchedule("New Events", eventList);

        LocalDate dateForSearch = null;
        try{
            System.out.println("For which date do you want to search for events? ");
            System.out.println("Year: ");
            int userYear = Integer.parseInt(scan.nextLine());
            System.out.println("Month: ");
            int userMonth = Integer.parseInt(scan.nextLine());
            System.out.println("Day: ");
            int userDay = Integer.parseInt(scan.nextLine());
            dateForSearch = LocalDate.of(userYear, userMonth, userDay);
        } catch (DateTimeException e){
            System.out.println("Add a valid date");
        } catch (NumberFormatException e){
            System.out.println("Type a valid number");
        }

        scan.close();

        if(eventSchedule.getAllEventsPerDate(dateForSearch).isEmpty()){
            System.out.println("There are not events in that date");
        } else {
            System.out.println(eventSchedule.getAllEventsPerDate(dateForSearch));
        }

        Collections.sort(eventList, new SortItems());

        for(Event e : eventList){
            System.out.println(e.getFormattedDate() + " - " + e.getTitle());
        }
    }
}
