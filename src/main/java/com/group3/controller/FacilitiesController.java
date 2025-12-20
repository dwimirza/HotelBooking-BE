package com.group3.controller;

import com.group3.model.Facilities;
import com.group3.service.FacilitiesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel-facilities")
public class FacilitiesController {
    private final FacilitiesService service;

    public FacilitiesController(FacilitiesService service) {
        this.service = service;
    }

    // GET: semua fasilitas (untuk admin / debugging)
    @GetMapping
    public List<Facilities> getAll() {
        return service.findAll();
    }

    // GET: fasilitas untuk 1 hotel tertentu
    @GetMapping("/{hotelId}")
    public Facilities getByHotelId(@PathVariable Integer hotelId) {
        return service.findByHotelId(hotelId);
    }

    // POST: buat fasilitas baru untuk hotel yang SUDAH ada
    @PostMapping
    public Facilities create(@RequestBody Facilities facilities) {
        return service.create(facilities);
    }

    // PUT: update fasilitas untuk hotel tertentu
    @PutMapping("/{hotelId}")
    public Facilities update(@PathVariable Integer hotelId,
                                  @RequestBody Facilities request) {
        return service.update(hotelId, request);
    }
}
