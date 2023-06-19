package com.example.demo.request;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class CreateBookingRequest {
    @Column(nullable = false)
    private Date checkIn;

    @Column(nullable = false)
    private Date checkOut;

    @Column(nullable = false)
    private Long hotel_id;
}
