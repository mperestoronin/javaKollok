package com.example.demo.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CreateHotelRequest {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Double pricePerNight;
    private Double rating;
    private String description;
}
