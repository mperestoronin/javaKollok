package com.services;

import com.models.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingService extends CrudRepository<Booking, Long> {}
