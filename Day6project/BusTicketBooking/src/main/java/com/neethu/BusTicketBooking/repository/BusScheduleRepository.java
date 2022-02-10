package com.neethu.BusTicketBooking.repository;

import com.neethu.BusTicketBooking.entity.BusSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusScheduleRepository extends JpaRepository<BusSchedule,Long> {
    public List<BusSchedule> findByBusRegistrationNumber(String regNo);
}
