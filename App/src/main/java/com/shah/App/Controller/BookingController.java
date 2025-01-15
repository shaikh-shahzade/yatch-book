package com.shah.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.shah.App.Service.BookingService;
import com.shah.App.model.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("api/bookings")
public class BookingController {
	
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable("id") Long id) {
        return bookingService.getBooking(id);
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @PutMapping("/{id}")
    public void updateBooking(@PathVariable("id") Long id, @RequestBody Booking booking) {
        bookingService.updateBooking(id, booking);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable("id") Long id) {
        bookingService.deleteBooking(id);
    }
    
    @GetMapping("/cancel/{id}")
    public void cancelBooking(@PathVariable("id") Long id) {
        bookingService.cancelBooking(id);
    }
    
    @GetMapping("/isBooked/{id}")
    public boolean isTicketBooked(@PathVariable("id") Long id) {
        return bookingService.isTicketBooked(id);
    }
}
