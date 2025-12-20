package com.group3.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hotel_facilities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Facilities {

    @Id
    @Column(name = "hotel_id")
    private Integer hotelId;

    // @OneToOne
    // @MapsId
    // @JoinColumn(name = "hotel_id")
    // private Hotel hotel;

    @Column(name = "swimming_pool", nullable = false)
    private Boolean swimmingPool;

    @Column(name = "gymnasium", nullable = false)
    private Boolean gymnasium;

    @Column(name = "wifi", nullable = false)
    private Boolean wifi;

    @Column(name = "room_service", nullable = false)
    private Boolean roomService;

    @Column(name = "air_condition", nullable = false)
    private Boolean airCondition;

    @Column(name = "breakfast", nullable = false)
    private Boolean breakfast;
}
