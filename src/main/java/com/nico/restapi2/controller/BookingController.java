package com.nico.restapi2.controller;


import com.nico.restapi2.model.Booking;
import com.nico.restapi2.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v4")
public class BookingController {

    private BookingService bookingService;
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    public BookingController(BookingService bookingService){
        //super();
        this.bookingService = bookingService;
    }

    @PostMapping("/bookings")
    public ResponseEntity<Booking> saveBooking(@RequestBody Booking booking) {
        logger.info("New booking was successfully created");
        return new ResponseEntity<>(bookingService.saveBooking(booking), HttpStatus.CREATED);
    }

    @GetMapping("/availability")
    public List<Booking> getAllBookings(){
        return bookingService.getAllBookings();
    }

    @GetMapping("/myBookings/{id}")
    @ResponseBody
    public ResponseEntity<Booking> getBookingById(@PathVariable("id") int Id){
        return new ResponseEntity<Booking>(bookingService.getBookingById(Id), HttpStatus.OK);
    }

    @PutMapping("/bookings/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable("id") int id, @RequestBody Booking booking){
        logger.info("Booking was successfully updated");
        return new ResponseEntity<Booking>(bookingService.updateBooking(booking, id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteBooking/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable ("id") int id){
        bookingService.deleteBooking(id);
        return new ResponseEntity<String>("Booking was successfully deleted.", HttpStatus.OK );
    }

}
