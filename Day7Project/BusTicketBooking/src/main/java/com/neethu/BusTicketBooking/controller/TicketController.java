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
import java.security.Principal;
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

    @RequestMapping("/seatMap/{id}")
    public String viewSeatMap(Principal principal, @PathVariable Long id, Model model) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
        model.addAttribute("id", id);
        return "seatMap";
    }

    @PostMapping("seatMap/{id}")
    public String busTicketBooking(Principal principal,@PathVariable Long id,Model model){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
        int tNumber=0;
        String[] tickets=new String[36];
        List<BookedTickets> bookedTickets=bookedTicketsService.getByBusScheduleId(id);
        for(BookedTickets ticket:bookedTickets){
            tickets[tNumber]=ticket.getBookedTickets();
            tNumber++;
        }
        model.addAttribute("tickets",tickets);
        model.addAttribute("bus",id);
        return "seatMap";
    }

//    @RequestMapping("/bookedPage/{id}")
//    public String ticketBooking(Principal principal, @PathVariable Long id, Model model) {
//        if (principal != null) {
//            String username = principal.getName();
//            model.addAttribute("userName", username);
//        }
//        model.addAttribute("bus", id);
//        return "bookedPage";
//    }

    @PostMapping("/bookedPage/{id}")
    public String bookedTickets(Principal principal, @PathVariable Long id, HttpServletRequest request, Model model) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
        model.addAttribute("bus", id);
        String bookedTickets = request.getParameter("bookedSeats");
        String[] bookedTicket = bookedTickets.split(",");
        for (String ticket : bookedTicket) {
            BookedTickets bookedTickets1 = bookedTicketsService.getByBookedTicketsAndBusScheduleId(ticket, id);
            if (!(Objects.isNull(bookedTickets1))) {
                model.addAttribute("message1", "These Tickets are already booked...!");
//                return "redirect:/seatMap/" + id;
                return "seatMap";

            }
        }
        for (String ticket : bookedTicket) {
//            BookedTickets bookedtickets=bookedTicketsService.getByTicketsAndBusScheduleId(ticket,id);
            Tickets tickets = ticketsService.getByTickets(ticket);
            BookedTickets bookedTickets1 = new BookedTickets(tickets.getTickets(), tickets.getPrice());
            bookedTickets1.setBusSchedule(busScheduleService.findByBusId(id));
            bookedTickets1.setUser(userService.findById(principal.getName()));
            bookedTicketsService.saveTickets(bookedTickets1);
            model.addAttribute("message", "Successfully booked the Tickets");

        }

        return "redirect:/busBookings";
    }

    @RequestMapping("/busBookings")
    public String viewBusBookings(Principal principal, Model model) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
        List<BookedTickets> bookedTickets = bookedTicketsService.getAllBookedTickets();
        ArrayList<BookedTickets> bookedTicketWithUserName = new ArrayList<BookedTickets>();
        for (BookedTickets ticket : bookedTickets) {

            if (ticket.getUser().getUserName().equals(principal.getName())) {
                bookedTicketWithUserName.add(ticket);
            }
        }
        model.addAttribute("seats", bookedTicketWithUserName);
        return "busBookings";
    }


}
