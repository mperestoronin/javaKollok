package com.controllers;

import com.models.Booking;
import com.models.Hotel;
import com.services.BookingService;
import com.services.HotelService;
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
    public ResponseEntity<Booking> bookHotel(@RequestBody Booking newBooking) {
        Long hotelId = newBooking.getHotel().getId();
        Optional<Hotel> hotelOptional = hotelService.findById(hotelId);
        if (hotelOptional.isPresent()) {
            newBooking.setHotel(hotelOptional.get());
            return ResponseEntity.ok(bookingService.save(newBooking));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

