package com.neethu.BusTicketBooking.controller;

import com.neethu.BusTicketBooking.entity.BookedTickets;
import com.neethu.BusTicketBooking.entity.Tickets;
import com.neethu.BusTicketBooking.service.BookedTicketsService;
import com.neethu.BusTicketBooking.service.BusScheduleService;
import com.neethu.BusTicketBooking.service.TicketsService;
import com.neethu.BusTicketBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class TicketController {

    @Autowired
    private BookedTicketsService bookedTicketsService;

    @Autowired
    private TicketsService ticketsService;

    @Autowired
    private BusScheduleService busScheduleService;

    @Autowired
    private UserService userService;

    @RequestMapping("/{userName}/seatMap/{id}")
    public String viewSeatMap(@PathVariable String userName, @PathVariable Long id, Model model) {
        model.addAttribute("userName", userName);
        model.addAttribute("id", id);
        return "seatMap";
    }

    @RequestMapping("/{userName}/bookedPage/{id}")
    public String ticketBooking(@PathVariable String userName, @PathVariable Long id, Model model) {
        model.addAttribute("userName", userName);
        model.addAttribute("bus", id);
        return "bookedPage";
    }

    @PostMapping("/{userName}/bookedPage/{id}")
    public String bookedTickets(@PathVariable String userName, @PathVariable Long id, HttpServletRequest request, Model model) {
        model.addAttribute("bus", id);
        String bookedTickets = request.getParameter("bookedSeats");
        String[] bookedTicket = bookedTickets.split(",");
        for (String ticket : bookedTicket) {
            BookedTickets bookedTickets1 = bookedTicketsService.getByBookedTicketsAndBusScheduleId(ticket, id);
            if (!(Objects.isNull(bookedTickets1))) {
                model.addAttribute("message1", "These Tickets are already booked...!");
                return "redirect:/" + userName + "/seatMap/" + id;

            }
        }
        for (String ticket : bookedTicket) {
//            BookedTickets bookedtickets=bookedTicketsService.getByTicketsAndBusScheduleId(ticket,id);
            Tickets tickets = ticketsService.getByTickets(ticket);
            BookedTickets bookedTickets1 = new BookedTickets(tickets.getTickets(), tickets.getPrice());
            bookedTickets1.setBusSchedule(busScheduleService.findByBusId(id));
            bookedTickets1.setUser(userService.findById(userName));
            bookedTicketsService.saveTickets(bookedTickets1);
            model.addAttribute("message", "Successfully booked the Tickets");

        }

        return "bookedPage";
    }

    @RequestMapping("/{userName}/busBookings")
    public String viewBusBookings(@PathVariable String userName, Model model) {
        model.addAttribute("userName", userName);
        List<BookedTickets> bookedTickets = bookedTicketsService.getAllBookedTickets();
        ArrayList<BookedTickets> bookedTicketWithUserName = new ArrayList<BookedTickets>();
        for (BookedTickets ticket : bookedTickets) {

            if (ticket.getUser().getUserName().equals(userName)) {
                bookedTicketWithUserName.add(ticket);
            }
        }
        model.addAttribute("seats", bookedTicketWithUserName);
        System.out.println(userName);
        return "busBookings";
    }
}
