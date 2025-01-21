package com.shah.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shah.App.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{

}
