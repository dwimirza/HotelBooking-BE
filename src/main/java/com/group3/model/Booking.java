package com.group3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer bookingId;

    // ===== USER =====
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private Users user;
     
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    @JsonIgnore
    private Hotel hotel;

    @Column(name = "hotel_id", insertable = false, updatable = false)
    private Integer hotelId;

    @Column(name = "booking_date")
    private LocalDateTime bookingDate;

    @Column(name = "status")
    private String status;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    // ===== BOOKING DETAILS =====
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @Builder.Default
    private List<BookingDetail> bookingDetails = new ArrayList<>();

    // Helper method
    public void addBookingDetail(BookingDetail detail) {
        if (bookingDetails == null) {
            bookingDetails = new ArrayList<>();
        }
        bookingDetails.add(detail);
        detail.setBooking(this);
    }
}
