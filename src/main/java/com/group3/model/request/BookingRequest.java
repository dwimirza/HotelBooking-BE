package com.group3.model.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.*;

@Data
public class BookingRequest {

     private Integer userId;
    private Integer hotelId;
    private BigDecimal totalAmount;
    private List<RoomBookingRequest> rooms;

    @Data
    public static class RoomBookingRequest { 
        private Integer roomId;
        private BigDecimal pricePerNight;
        private LocalDate checkIn;
        private LocalDate checkOut;
        private String specialRequest;
    }
}
