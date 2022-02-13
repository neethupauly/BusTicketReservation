package com.neethu.BusTicketBooking.repository;

import com.neethu.BusTicketBooking.entity.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketsRepository extends JpaRepository<Tickets,Long> {
   public Tickets getByTickets(String ticket);
}
