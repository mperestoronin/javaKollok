package com.services;

import com.models.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewService extends CrudRepository<Review, Long> {}