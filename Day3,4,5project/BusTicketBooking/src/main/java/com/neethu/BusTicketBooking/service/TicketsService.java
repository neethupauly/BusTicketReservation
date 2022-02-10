package com.neethu.BusTicketBooking.service;

import com.neethu.BusTicketBooking.entity.Tickets;
import com.neethu.BusTicketBooking.repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketsService {

    @Autowired
    private TicketsRepository ticketsRepository;


    public Tickets getByTickets(String ticket) {
        return ticketsRepository.getByTickets(ticket);
    }
}
