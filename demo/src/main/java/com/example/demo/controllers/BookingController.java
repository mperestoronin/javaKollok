package com.example.demo.controllers;

import com.example.demo.models.Booking;
import com.example.demo.models.Hotel;
import com.example.demo.request.CreateBookingRequest;
import com.example.demo.services.BookingService;
import com.example.demo.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;
    private final HotelService hotelService;

    @Autowired
    public BookingController(BookingService bookingService, HotelService hotelService) {
        this.bookingService = bookingService;
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<Booking> bookHotel(@RequestBody CreateBookingRequest request) {
        Optional<Hotel> hotelOptional = hotelService.findById(request.getHotel_id());
        if (hotelOptional.isPresent()) {
            final Booking newBooking = new Booking();
            newBooking.setHotel_id(request.getHotel_id());
            newBooking.setCheckOut(request.getCheckOut());
            newBooking.setCheckIn(request.getCheckIn());
            return ResponseEntity.ok(bookingService.save(newBooking));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

