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
            @RequestParam(required = false) String city)  {
        return service.search(hotelName, city);
    }

    @GetMapping("/{code}")
    public Hotel detail(@PathVariable("code") Integer code) {
        Optional<Hotel> hotel = service.findById(code);
        return hotel.orElseThrow(() -> 
            new RuntimeException("Hotel not found with id: " + code));
    }

    @PutMapping("/{code}")
    public Hotel update(@PathVariable("code") Integer code,
                        @RequestBody Hotel hotel) {
        Optional<Hotel> existingHotel = service.findById(code);
        if (existingHotel.isPresent()) {
            hotel.setHotelId(code);
            return service.save(hotel);
        }
        throw new RuntimeException("Hotel not found with id: " + code);
    }

    @DeleteMapping("/{code}")
    public void delete(@PathVariable("code") Integer code) {
        Optional<Hotel> hotel = service.findById(code);
        if (hotel.isPresent()) {
            service.delete(code);
        } else {
            throw new RuntimeException("Hotel not found with id: " + code);
        }
    }
}
