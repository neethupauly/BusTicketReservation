package com.neethu.BusTicketBooking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class User {

    @Id
    private String userName;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private Long phoneNumber;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user" )
    private List<BookedTickets> bookedTickets;

    public User(String userName, String name, Long phoneNumber, String password) {
        this.userName = userName;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public User(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<BookedTickets> getBookedTickets() {
        return bookedTickets;
    }

    public void setBookedTickets(List<BookedTickets> bookedTickets) {
        this.bookedTickets = bookedTickets;
    }
}
