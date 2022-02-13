package com.neethu.BusTicketBooking.service;

import com.neethu.BusTicketBooking.entity.User;
import com.neethu.BusTicketBooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user){
       return userRepository.save(user);
    }

    public User findById(String userName){
        return userRepository.getById(userName);
    }


    public boolean usernameExistsById(String userName) {
        return userRepository.existsById(userName);
    }
}
