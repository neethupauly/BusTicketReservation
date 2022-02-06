package com.neethu.BusTicketBooking.controller;

import com.neethu.BusTicketBooking.entity.Bus;
import com.neethu.BusTicketBooking.entity.BusSchedule;
import com.neethu.BusTicketBooking.service.BusScheduleService;
import com.neethu.BusTicketBooking.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Time;

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
}
