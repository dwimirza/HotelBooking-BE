package com.group3.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "hotels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Integer hotelId;
    
    @Column(name = "hotel_name", length = 255, nullable = false)
    private String hotelName;
    
    @Column(name = "city", length = 50, nullable = true)
    private String city;
    
    @Column(name = "address", columnDefinition = "TEXT", nullable = true)
    private String address;
    
    @Column(name = "phone_no", length = 50, nullable = true)
    private String phoneNo;
    
    @Column(name = "email", length = 255, nullable = true)
    private String email;
    
    @Column(name = "star_rating", nullable = true)
    private Integer starRating;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;
    
    @Column(name = "available_room", nullable = true)
    private Integer availableRoom;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
