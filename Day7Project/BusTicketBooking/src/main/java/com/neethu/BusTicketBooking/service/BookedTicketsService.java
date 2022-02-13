package com.neethu.BusTicketBooking.service;

import com.neethu.BusTicketBooking.entity.BookedTickets;
import com.neethu.BusTicketBooking.repository.BookedTicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookedTicketsService {

    @Autowired
    private BookedTicketsRepository bookedTicketsRepository;

    public BookedTickets getByBookedTicketsAndBusScheduleId(String ticket, Long id) {
        return bookedTicketsRepository.getByBookedTicketsAndBusScheduleId(ticket,id);
    }

    public void saveTickets(BookedTickets bookedTickets1) {
        bookedTicketsRepository.save(bookedTickets1);
    }

    public BookedTickets getByUserName(String userName) {
        return bookedTicketsRepository.findByUserUserName(userName);
    }

    public List<BookedTickets> getAllBookedTickets() {
        return bookedTicketsRepository.findAll();
    }


    public List<BookedTickets> getByBusScheduleId(Long id) {
        return bookedTicketsRepository.getByBusSchedule(id);
    }
}
