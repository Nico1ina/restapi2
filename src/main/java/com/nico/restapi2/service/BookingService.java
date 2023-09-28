package com.nico.restapi2.service;

import com.nico.restapi2.exceptions.ResourceNotFoundException;
import com.nico.restapi2.model.Booking;
import com.nico.restapi2.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BookingService implements BookingServiceInterface {

    @Autowired
    private BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository){
        super();
        this.bookingRepository = bookingRepository;
    }
    @Override
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking createBooking(Booking booking) {
        return null;
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(int id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if(booking.isPresent()){
            return booking.get();
        }else{
            throw new ResourceNotFoundException("Booking", "id", booking);
        }
    }

    @Override
    public Booking updateBooking(Booking booking, int id) {
        Booking b = bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException
                ("Booking", "id", id));
        b.setCourtName(booking.getCourtName());
        b.setPlayerName(booking.getPlayerName());
        b.setDate(booking.getDate());
        b.setTime(booking.getTime());
        bookingRepository.save(b);
        return b;
    }

    @Override
    public void deleteBooking(int id) {
        bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking", "id", id));
        bookingRepository.deleteById(id);
    }
}
