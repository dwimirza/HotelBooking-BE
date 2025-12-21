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

    @GetMapping
    public List<Facilities> getAll() {
        return service.findAll();
    }

    @GetMapping("/{hotelId}")
    public Facilities getByHotelId(@PathVariable Integer hotelId) {
        return service.findByHotelId(hotelId);
    }

    @GetMapping("/search")
    public List<Facilities> searchFacilities(
            @RequestParam(required = false) Boolean swimmingPool,
            @RequestParam(required = false) Boolean gymnasium,
            @RequestParam(required = false) Boolean wifi,
            @RequestParam(required = false) Boolean roomService,
            @RequestParam(required = false) Boolean airCondition,
            @RequestParam(required = false) Boolean breakfast) {
        return service.searchFacilities(swimmingPool, gymnasium, wifi,
                roomService, airCondition, breakfast);
    }

    @PostMapping
    public Facilities create(@RequestBody Facilities facilities) {
        return service.create(facilities);
    }

    @PutMapping("/{hotelId}")
    public Facilities update(@PathVariable Integer hotelId,
            @RequestBody Facilities request) {
        return service.update(hotelId, request);
    }
}
