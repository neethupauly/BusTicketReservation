package com.neethu.BusTicketBooking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tickets {

    @Id
    @GeneratedValue
    private Long id;
    private String tickets;
    private double price;

    public Tickets(String tickets, double price) {
        this.tickets = tickets;
        this.price = price;
    }
    public Tickets(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
