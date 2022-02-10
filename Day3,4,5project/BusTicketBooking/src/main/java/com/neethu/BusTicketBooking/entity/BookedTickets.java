package com.neethu.BusTicketBooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BookedTickets {

    @Id
    @GeneratedValue
    private Long id;
    private String bookedTickets;
    private double price;

    @JsonIgnore
    @ManyToOne
    private BusSchedule busSchedule;

    @JsonIgnore
    @ManyToOne
    private User user;

    public BookedTickets(String bookedTickets, double price) {
        this.bookedTickets = bookedTickets;
        this.price = price;
    }

    public BookedTickets(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookedTickets() {
        return bookedTickets;
    }

    public void setBookedTickets(String bookedTickets) {
        this.bookedTickets = bookedTickets;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public BusSchedule getBusSchedule() {
        return busSchedule;
    }

    public void setBusSchedule(BusSchedule busSchedule) {
        this.busSchedule = busSchedule;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
