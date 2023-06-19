package com.controllers;
import com.models.Hotel;
import com.models.Review;
import com.services.HotelService;
import com.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final HotelService hotelService;

    @Autowired
    public ReviewController(ReviewService reviewService, HotelService hotelService) {
        this.reviewService = reviewService;
        this.hotelService = hotelService;
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<List<Review>> getReviewsForHotel(@PathVariable Long hotelId) {
        Optional<Hotel> hotelOptional = hotelService.findById(hotelId);
        return hotelOptional
                .map(hotel -> ResponseEntity.ok(hotel.getReviews()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Review> addReviewForHotel(@RequestBody Review newReview) {
        Long hotelId = newReview.getHotel().getId();
        Optional<Hotel> hotelOptional = hotelService.findById(hotelId);
        if (hotelOptional.isPresent()) {
            newReview.setHotel(hotelOptional.get());
            return ResponseEntity.ok(reviewService.save(newReview));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

