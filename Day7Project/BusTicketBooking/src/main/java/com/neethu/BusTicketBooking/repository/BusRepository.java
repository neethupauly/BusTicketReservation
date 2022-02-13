package com.neethu.BusTicketBooking.repository;

import com.neethu.BusTicketBooking.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus,String> {
}
