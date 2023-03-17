package org.lessons.java;

import java.util.Comparator;

public class SortItems implements Comparator<Event> {
    @Override
    public int compare(Event d1, Event d2) {
        return d1.getDate().compareTo(d2.getDate());
    }
}
