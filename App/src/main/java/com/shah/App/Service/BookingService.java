package com.shah.App.Service;

import java.util.List;

import com.shah.App.model.Booking;

public interface BookingService {
    
    void cancelBooking(Long id);
    boolean isTicketBooked(Long id);
    Booking createBooking(Booking booking);
    Booking getBooking(Long id);
    void updateBooking(Long id, Booking updatedBooking);
    void deleteBooking(Long id);
    public List<Booking> getAllBookings();
    public List<Booking> getAllBookingsByUser();

}
