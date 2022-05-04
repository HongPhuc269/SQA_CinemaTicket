package edu.hanu.cinematicket.model;

public class Ticket {

    private String seat;
    private String date;
    private String time;

    public Ticket(String seat, String date, String time) {
        this.seat = seat;
        this.date = date;
        this.time = time;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
