package com.example.demo.services;

import com.example.demo.models.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingService extends CrudRepository<Booking, Long> {}
