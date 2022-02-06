package com.neethu.BusTicketBooking.repository;

import com.neethu.BusTicketBooking.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,String> {
}
