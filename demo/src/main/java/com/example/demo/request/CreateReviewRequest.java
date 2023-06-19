package com.example.demo.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CreateReviewRequest {
    @Column(nullable = false)
    private Double stars;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private Long hotel_id;
}
