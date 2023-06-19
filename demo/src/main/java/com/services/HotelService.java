package com.services;

import com.models.Hotel;
import org.springframework.data.repository.CrudRepository;

public interface HotelService extends CrudRepository<Hotel, Long> {}
