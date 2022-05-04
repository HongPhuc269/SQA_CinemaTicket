package edu.hanu.cinematicket.model;

public class Seat {
    private String seatNum;

    public String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }

    public Seat(String seatNum) {
        this.seatNum = seatNum;
    }
}
