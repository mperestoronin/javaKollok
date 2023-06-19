package com.example.demo.controllers;
import com.example.demo.models.Hotel;
import com.example.demo.models.Review;
import com.example.demo.request.CreateReviewRequest;
import com.example.demo.services.HotelService;
import com.example.demo.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        List<Review> reviewList = new ArrayList<>();
        for (var item: reviewService.findAllByHotel(hotelId)) {
            reviewList.add(item);
        }
        return ResponseEntity.ok(reviewList);
    }

    @PostMapping
    public ResponseEntity<Review> addReviewForHotel(@RequestBody CreateReviewRequest request) {
        Optional<Hotel> hotelOptional = hotelService.findById(request.getHotel_id());
        if (hotelOptional.isPresent()) {
            final Review newReview = new Review();
            newReview.setHotel(request.getHotel_id());
            newReview.setText(request.getText());
            newReview.setStars(request.getStars());
            return ResponseEntity.ok(reviewService.save(newReview));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

