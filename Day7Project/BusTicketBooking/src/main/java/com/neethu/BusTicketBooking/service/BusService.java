package com.neethu.BusTicketBooking.service;

import com.neethu.BusTicketBooking.entity.Bus;
import com.neethu.BusTicketBooking.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;


    public Bus findByBusRegistrationNumber(String registrationNumber){
        return busRepository.getById(registrationNumber);
    }

    public List<Bus> listAllBuses()
    {
        return busRepository.findAll();
    }

    public Bus save(Bus bus) {
        return busRepository.save(bus);
    }
}
