package com.example.demo.services;

import com.example.demo.models.Hotel;
import com.example.demo.request.CreateHotelRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HotelService extends CrudRepository<Hotel, Long> {
    Optional<Hotel> findByName(String name);
}
