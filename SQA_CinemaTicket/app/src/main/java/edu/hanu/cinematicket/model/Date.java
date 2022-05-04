package edu.hanu.cinematicket.model;

public class Date {
    private String date;
    private String weekday;

    public Date(String date, String weekday) {
        this.date = date;
        this.weekday = weekday;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }
}
