package org.lessons.java;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concert extends Event {
    private LocalTime hour;
    private BigDecimal price;

    //CONSTRUCTOR
    public Concert(String title, LocalDate date, int totalSeats, LocalTime hour, BigDecimal price) throws IllegalArgumentException {
        super(title, date, totalSeats);
        checkPrice(price);
        this.hour = hour;
        this.price = price;
    }

    //GETTERS
    public LocalTime getHour() {
        return hour;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getFormattedHour(){
        DateTimeFormatter timeFormatted = DateTimeFormatter.ofPattern("HH:mm");
        return timeFormatted.format(hour);
    }

    public String getFormattedPrice(){
        DecimalFormat format = new DecimalFormat("##.##€");
        return format.format(price);
    }

    //SETTERS
    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public void setPrice(BigDecimal price) {
        checkPrice(price);
        this.price = price;
    }

    //METHODS


    @Override
    public String toString() {
        return getFormattedDate() + " " + getFormattedHour() + " - " + getTitle() + " - " + getFormattedPrice();
    }

    private void checkPrice(BigDecimal price){
        if (price.compareTo(BigDecimal.ZERO) < 0 ){
            throw new IllegalArgumentException("The price can't be minor than zero");
        }
    }
}
