package com.neethu.BusTicketBooking.repository;

import com.neethu.BusTicketBooking.entity.BusSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusScheduleRepository extends JpaRepository<BusSchedule,Long> {
}
