package com.neethu.BusTicketBooking.repository;

import com.neethu.BusTicketBooking.entity.BookedTickets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookedTicketsRepository extends JpaRepository<BookedTickets,Long> {
   public BookedTickets getByBookedTicketsAndBusScheduleId(String ticket, Long id);

//   public BookedTickets getByUserUserName(String userName);

  public BookedTickets findByUserUserName(String userName);

   public List<BookedTickets> getByBusSchedule(Long id);
}
