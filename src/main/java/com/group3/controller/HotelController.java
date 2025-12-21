package com.group3.controller;

import com.group3.model.Hotel;
import com.group3.service.HotelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    private final HotelService service;

    public HotelController(HotelService service) {
        this.service = service;
    }

    @PostMapping
    public Hotel create(@RequestBody Hotel hotel) {
        return service.save(hotel);
    }

    @GetMapping
    public List<Hotel> list(
            @RequestParam(required = false) String hotelName,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Integer starRating) {
        return service.search(hotelName, city, starRating);
    }

    @GetMapping("/{hotelId}")
    public Hotel detail(@PathVariable("hotelId") Integer hotelId) {
        Optional<Hotel> hotel = service.findById(hotelId);
        return hotel.orElseThrow(() -> 
            new RuntimeException("Hotel not found with id: " + hotelId));
    }

    @PutMapping("/{hotelId}")
    public Hotel update(@PathVariable("hotelId") Integer hotelId,
                        @RequestBody Hotel hotel) {
        Optional<Hotel> existingHotel = service.findById(hotelId);
        if (existingHotel.isPresent()) {
            hotel.setHotelId(hotelId);
            return service.save(hotel);
        }
        throw new RuntimeException("Hotel not found with id: " + hotelId);
    }

    @DeleteMapping("/{hotelId}")
    public void delete(@PathVariable("hotelId") Integer hotelId) {
        Optional<Hotel> hotel = service.findById(hotelId);
        if (hotel.isPresent()) {
            service.delete(hotelId);
        } else {
            throw new RuntimeException("Hotel not found with id: " + hotelId);
        }
    }
}
