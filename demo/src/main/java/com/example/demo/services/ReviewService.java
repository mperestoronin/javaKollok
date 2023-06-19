package com.example.demo.services;

import com.example.demo.models.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewService extends CrudRepository<Review, Long> {
    Iterable<Review> findAllByHotel(Long hotel);
}