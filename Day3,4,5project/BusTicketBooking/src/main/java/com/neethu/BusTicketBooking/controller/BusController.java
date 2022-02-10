package com.neethu.BusTicketBooking.controller;

import com.neethu.BusTicketBooking.entity.BookedTickets;
import com.neethu.BusTicketBooking.entity.Bus;
import com.neethu.BusTicketBooking.entity.BusSchedule;
import com.neethu.BusTicketBooking.service.BookedTicketsService;
import com.neethu.BusTicketBooking.service.BusScheduleService;
import com.neethu.BusTicketBooking.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Controller
public class BusController {

    @Autowired
    private BusService busService;

    @Autowired
    private BusScheduleService busScheduleService;

    @RequestMapping("/addBus")
    public String addBus(){
        return "addBus";
    }

    @PostMapping("/addBus")
    public String addBusDetails(HttpServletRequest request, Model model){
        String registrationNumber=request.getParameter("registrationNumber");
        String busName=request.getParameter("busName");
        String startingPlace=request.getParameter("startingPlace");
        String destinationPlace=request.getParameter("destinationPlace");


        Bus bus=new Bus(registrationNumber,busName,startingPlace,destinationPlace);
        busService.save(bus);
        model.addAttribute("message", "successfully added Bus Details");
        return "busSchedule";

    }

    @RequestMapping("/busSchedule")
    public String busSchedule(){
        return "busSchedule";
    }

    @PostMapping("/busSchedule")
    public String addBusSchedule(HttpServletRequest request,Model model){
        String registrationNumber=request.getParameter("registrationNumber");
        Date fromDate=Date.valueOf(request.getParameter("fromDate"));
        Time startingTime=Time.valueOf(request.getParameter("startingTime")+":00");

        BusSchedule busSchedule=new BusSchedule(fromDate,startingTime);
        busSchedule.setBus(busService.findByBusRegistrationNumber(registrationNumber));
        busScheduleService.save(busSchedule);
        return "busSchedule";
    }
    @RequestMapping("/{userName}/busList")
    public String busList(Model model){
        model.addAttribute("buses",busService.listAllBuses());
        return "busList";
    }

    @RequestMapping("/{userName}/viewSchedule/{regNo}")
    public String viewScheduleOfBuses(@PathVariable String regNo,@PathVariable String userName, Model model) {
        model.addAttribute("schedules", busScheduleService.busScheduleDetails(regNo));
        model.addAttribute("busName",busService.findByBusRegistrationNumber(regNo));
        model.addAttribute("userName",userName);
        return "viewSchedule";
    }


}
