package org.lessons.java;

import java.time.LocalDate;
import java.util.*;

public class EventSchedule{
    private String title;
    private List<Event> events;

    //CONSTRUCTOR
    public EventSchedule(String title, List<Event> events){
        checkTitle(title);
        checkArray(events);
        this.title = title;
        this.events = new ArrayList<>();
        this.events.addAll(events);
    }

    //METHODS
    private void checkTitle(String title){
        if(title.trim().isEmpty()){
            throw new IllegalArgumentException("Title can't be empty");
        }
    }

    private void checkArray(List<Event> arr){
        if(arr.isEmpty()){
            throw new IllegalArgumentException("Add at least one event");
        }
    }

    public void addEvent(Event e){
        events.add(e);
    }

    public int countEvents(){
        return events.size();
    }

    public void removeAllEvents(){
        events.clear();
    }

    public List<Event> getAllEventsPerDate(LocalDate date){
        List<Event> a = new ArrayList<>();
        for (Event e : events){
            if(e.getDate().equals(date)){
                a.add(e);
            }
        }

        return a;
    }
}
