
package com.shah.App.Service.impl;

import com.shah.App.Service.BookingService;
import com.shah.App.model.Booking;
import com.shah.App.repository.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;




@Service
public class BookingServiceImpl implements BookingService {

	private final BookingRepository bookingRepository;
	
	@Override
	public void deleteBooking(Long id) {
		bookingRepository.deleteById(id);
	}

	@Override
	public void cancelBooking(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isTicketBooked(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Booking createBooking(Booking booking) {
		return bookingRepository.save(booking);
	}

	@Override
	public Booking getBooking(Long id) {
		// TODO Auto-generated method stub
		return bookingRepository.findById(id).orElse(null);
	}

	@Override
	public void updateBooking(Long id, Booking updatedBooking) {
		// TODO Auto-generated method stub
		Booking existingBooking = bookingRepository.findById(id).orElse(null);
		if (existingBooking != null) {
			//update property
			bookingRepository.save(existingBooking);
		}
	}

	@Autowired
	public BookingServiceImpl(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}

	@Override
	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	@Override
	public List<Booking> getAllBookingsByUser() {
		// TODO Auto-generated method stub
		return null;
	}




	
}
