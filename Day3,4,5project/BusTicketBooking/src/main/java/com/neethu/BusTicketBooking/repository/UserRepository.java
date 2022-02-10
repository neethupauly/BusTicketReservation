package com.neethu.BusTicketBooking.repository;

import com.neethu.BusTicketBooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
