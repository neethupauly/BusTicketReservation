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
import java.security.Principal;
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
    public String addBus(Principal principal,Model model){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
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
    @RequestMapping("/busList")
    public String busList(Principal principal,Model model){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
        model.addAttribute("buses",busService.listAllBuses());
        return "busList";
    }

    @RequestMapping("/viewSchedule/{regNo}")
    public String viewScheduleOfBuses(@PathVariable String regNo,Principal principal, Model model) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
        model.addAttribute("schedules", busScheduleService.busScheduleDetails(regNo));
        model.addAttribute("busName",busService.findByBusRegistrationNumber(regNo));

        return "viewSchedule";
    }

    @RequestMapping("/adminViewBuses")
    public String adminViewBusList(Principal principal,Model model){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
        model.addAttribute("buses",busScheduleService.listAllBusSchedules());
        return "adminViewBuses";
    }


    @RequestMapping("/updateBusDetails/{id}")
    public String updateBusDetails(Principal principal, @PathVariable Long id, Model model) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);

        }
        model.addAttribute("busDetails",busScheduleService.findByBusId(id));
        return "updateBusDetails";
    }


    @PostMapping("/updateBusDetails/{id}")
    public String updateBus(Principal principal, @PathVariable Long id, Model model, HttpServletRequest req) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
        BusSchedule busSchedule=busScheduleService.findByBusId(id);
        String regNo=req.getParameter("registrationNumber");
        Date fromDate = Date.valueOf(req.getParameter("fromDate"));
        String sTime= req.getParameter("startingTime");

        if (sTime.length() == 5) sTime += ":00";
        Time  startingTime= Time.valueOf(sTime);
        busSchedule.setFromDate(fromDate);
        busSchedule.setStartingTime(startingTime);
        busSchedule.setBus(busService.findByBusRegistrationNumber(regNo));
        busScheduleService.save(busSchedule);
        return "redirect:/adminViewBuses";

    }
}
