package com.neethu.BusTicketBooking.service;

import com.neethu.BusTicketBooking.entity.Admin;
import com.neethu.BusTicketBooking.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin findByAdminId(String adminName){
        return adminRepository.getById(adminName);
    }
    public Boolean adminExistsById(String adminName){
        return adminRepository.existsById(adminName);
    }
}
