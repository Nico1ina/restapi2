package com.nico.restapi2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingID;

    @Column(name = "courtName", nullable = false)
    private String courtName;

    @Column(name = "date", nullable = false)
    private int date;

    @Column(name = "playerName", nullable = false)
    private String playerName;

    @Column(name = "time", nullable = false)
    private int time;

    public Booking(int bookingID, String courtName, int date, String playerName, int time) {
        this.bookingID = bookingID;
        this.courtName = courtName;
        this.date = date;
        this.playerName = playerName;
        this.time = time;
    }
    public Booking(){
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}