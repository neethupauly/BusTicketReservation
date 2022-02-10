package com.neethu.BusTicketBooking.service;

import com.neethu.BusTicketBooking.entity.Bus;
import com.neethu.BusTicketBooking.entity.BusSchedule;
import com.neethu.BusTicketBooking.repository.BusScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusScheduleService {

    @Autowired
    private BusScheduleRepository busScheduleRepository;


    public BusSchedule findByBusId(Long id){
        return busScheduleRepository.getById(id);
    }

    public List<BusSchedule> listAllBusSchedules()
    {
        return busScheduleRepository.findAll();
    }

    public void save(BusSchedule busSchedule) {
         busScheduleRepository.save(busSchedule);
    }


    public List<BusSchedule> busScheduleDetails(String regNo) {
        return busScheduleRepository.findByBusRegistrationNumber(regNo);
    }
}
