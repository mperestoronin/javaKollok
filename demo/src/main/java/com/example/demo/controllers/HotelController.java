package com.example.demo.controllers;
import com.example.demo.models.Hotel;
import com.example.demo.request.CreateHotelRequest;
import com.example.demo.services.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;


    @GetMapping
    public List<Hotel> getAllHotels() {
        return (List<Hotel>) hotelService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        return hotelService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public void createHotel(@RequestBody CreateHotelRequest request) {
        if (hotelService.findByName(request.getName()).isPresent()) {
            throw new IllegalArgumentException("Hotel is already present");
        }
        Hotel hotel = new Hotel();
        hotel.setName(request.getName());
        hotel.setPricePerNight(request.getPricePerNight());
        hotel.setRating(request.getRating());
        hotel.setDescription(request.getDescription());
        hotelService.save(hotel);
    }
}

