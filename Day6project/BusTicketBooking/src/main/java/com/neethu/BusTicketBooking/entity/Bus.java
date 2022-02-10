package com.neethu.BusTicketBooking.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Bus {

    @Id
    private String registrationNumber;
    @Column(nullable = false)
    private String busName;
    @Column(nullable = false)
    private String startingPlace;
    @Column(nullable = false)
    private String destinationPlace;

    @OneToMany(mappedBy = "bus")
    private List<BusSchedule> busSchedules;


    public Bus(String registrationNumber, String busName, String startingPlace, String destinationPlace) {
        this.registrationNumber = registrationNumber;
        this.busName = busName;
        this.startingPlace = startingPlace;
        this.destinationPlace = destinationPlace;
    }
    public Bus(){

    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getStartingPlace() {
        return startingPlace;
    }

    public void setStartingPlace(String startingPlace) {
        this.startingPlace = startingPlace;
    }

    public String getDestinationPlace() {
        return destinationPlace;
    }

    public void setDestinationPlace(String destinationPlace) {
        this.destinationPlace = destinationPlace;
    }

    public List<BusSchedule> getBusSchedules() {
        return busSchedules;
    }

    public void setBusSchedules(List<BusSchedule> busSchedules) {
        this.busSchedules = busSchedules;
    }


}
