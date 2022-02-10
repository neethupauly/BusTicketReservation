package com.neethu.BusTicketBooking.controller;

import com.neethu.BusTicketBooking.entity.User;
import com.neethu.BusTicketBooking.service.AdminService;
import com.neethu.BusTicketBooking.service.BusService;
import com.neethu.BusTicketBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BusService busService;

    @Autowired
    private AdminService adminService;

    @RequestMapping("/home")
    public String homepage() {
        return "home";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/register")
    public String userSignup(HttpServletRequest request, Model model) {
        String userName = request.getParameter("userName");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        Long phoneNumber = Long.parseLong(request.getParameter("phoneNumber"));

        model.addAttribute("userName", userName);
        model.addAttribute("name", name);
        model.addAttribute("password", password);
        model.addAttribute("phoneNumber", phoneNumber);

        User user1 = new User(userName, name, phoneNumber, password);
        userService.save(user1);
        model.addAttribute("message", "successfully registered");
        return "register";
    }

    @PostMapping("/login")
    public String userSignIn(Model model, HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
//        String name=request.getParameter("name");

        User user2 = userService.findById(userName);

        if (adminService.adminExistsById(userName)) {
            if (Objects.equals(adminService.findByAdminId(userName).getPassword(), password)) {
                System.out.println("Admin logged in successfully");
                return "addBus";
            } else {
                System.out.println("Error occurred during Login");
                return "login";
            }
        } else if (userService.usernameExistsById(userName)) {
            if (Objects.equals(userService.findById(userName).getPassword(), password)) {
                model.addAttribute("message", "Successfully logged in");
                model.addAttribute("userName", userName);
                System.out.println(userName);
//                model.addAttribute("name", userService.findById(userName).getName());
                model.addAttribute("buses",busService.listAllBuses());
                return "busList";
            } else {
                model.addAttribute("error", "Invalid Password..!");
                return "login";
            }
        } else {
            model.addAttribute("error2", "Invalid Credentials..!");
            return "login";
        }
    }

}
