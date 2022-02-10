package com.neethu.BusTicketBooking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin {

    @Id
    private String adminName;
    @Column(nullable=false)
    private String password;

    public Admin(String adminName, String password) {
        this.adminName = adminName;
        this.password = password;
    }
    public Admin(){

    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
