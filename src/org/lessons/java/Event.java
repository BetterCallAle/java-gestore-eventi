package org.lessons.java;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event {
    String title;
    LocalDate date;
    int totalSeats;
    int bookedSeats;

    //CONSTRUCTOR
    public Event(String title, LocalDate date, int totalSeats) throws IllegalArgumentException{
        checkTitle(title);
        checkDate(date);
        checkTotalSeats(totalSeats);
        this.title = title;
        this.date = date;
        this.totalSeats = totalSeats;
        this.bookedSeats = 0;
    }

    //GETTERS
    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    //SETTERS
    public void setTitle(String title) {
        checkTitle(title);
        this.title = title;
    }

    public void setDate(LocalDate date) {
        checkDate(date);
        this.date = date;
    }

    //METHODS


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatter.format(date) + title;
    }

    private void checkTitle(String title) throws IllegalArgumentException{
        if (title.trim().isEmpty()){
            throw new IllegalArgumentException("Title can't be empty");
        }
    }

    private void checkDate(LocalDate date) throws IllegalArgumentException{
        LocalDate now = LocalDate.now();

        if (now.isAfter(date)){
            throw new IllegalArgumentException("Date can't be in the past. Your date: " + date + ", now: " + now);
        }
    }

    private void checkTotalSeats(int seats) throws IllegalArgumentException{
        if (seats <= 0){
            throw new IllegalArgumentException("The total seats can't be minor than zero");
        }
    }

    public void bookSeat(){
        checkDate(date);
        if (totalSeats == bookedSeats){
            throw new IllegalArgumentException("There are not available seats");
        }
        bookedSeats++;
    }

    public void cancel(){
        checkDate(date);
        checkTotalSeats(bookedSeats);
        bookedSeats--;
    }

    public int countRemainingSeats(){
        return totalSeats - bookedSeats;
    }
}
