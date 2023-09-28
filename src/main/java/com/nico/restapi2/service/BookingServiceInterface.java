package com.nico.restapi2.service;

import com.nico.restapi2.model.Booking;
import java.util.List;

public interface BookingServiceInterface {
    Booking saveBooking(Booking booking);
    Booking createBooking(Booking booking);
    List<Booking> getAllBookings();
    Booking getBookingById(int id);
    Booking updateBooking(Booking booking, int id);
    void deleteBooking(int id);
}
