package com.neethu.BusTicketBooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
public class BusSchedule {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Date fromDate;
    @Column(nullable = false)
    private Time startingTime;

    @JsonIgnore
    @ManyToOne
    private Bus bus;

    @OneToMany(mappedBy = "busSchedule")
    private List<BookedTickets> bookedTickets;

    public BusSchedule( Date fromDate, Time startingTime) {
        this.fromDate = fromDate;
        this.startingTime = startingTime;
    }
    public BusSchedule(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Time getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Time startingTime) {
        this.startingTime = startingTime;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

}
