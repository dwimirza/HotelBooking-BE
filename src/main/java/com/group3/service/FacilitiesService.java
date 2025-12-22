package com.group3.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.TransactionScoped;

import com.group3.model.Hotel;
import com.group3.model.Facilities;
import com.group3.repository.HotelRepository;
import com.group3.repository.FacilitiesRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilitiesService {
    private final FacilitiesRepository facilitiesRepo;

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private FacilitiesRepository facilitiesRepository;

    @Autowired
    private HotelRepository hotelRepository;

    public FacilitiesService(FacilitiesRepository facilitiesRepo) {
        this.facilitiesRepo = facilitiesRepo;
    }

    public List<Facilities> findAll() {
        return facilitiesRepo.findAll();
    }

    public Facilities findByHotelId(Integer hotelId) {
        return facilitiesRepo.findById(hotelId)
                .orElseThrow(() -> new RuntimeException(
                        "Facilities not found for hotelId: " + hotelId));
    }

    public List<Facilities> searchFacilities(Boolean swimmingPool,
            Boolean gymnasium,
            Boolean wifi,
            Boolean roomService,
            Boolean airCondition,
            Boolean breakfast) {
        return facilitiesRepo.searchFacilities(
            swimmingPool, gymnasium, wifi,
            roomService, airCondition, breakfast
    );
    }

    @Transactional
    public Facilities create(Facilities request) {
    Integer hotelId = request.getHotelId();
        
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));

        Facilities facilities = new Facilities();
        facilities.setHotelId(hotelId);
        facilities.setHotel(hotel);
        facilities.setSwimmingPool(request.getSwimmingPool());
        facilities.setGymnasium(request.getGymnasium());
        facilities.setWifi(request.getWifi());
        facilities.setRoomService(request.getRoomService());
        facilities.setAirCondition(request.getAirCondition());
        facilities.setBreakfast(request.getBreakfast());

        entityManager.persist(facilities);  // ini paksa INSERT
        return facilities;
    }


    public Facilities update(Integer hotelId, Facilities request) {
        Facilities existing = facilitiesRepo.findById(hotelId)
                .orElseThrow(() -> new RuntimeException(
                        "Facilities not found for hotelId: " + hotelId));

        existing.setSwimmingPool(request.getSwimmingPool());
        existing.setGymnasium(request.getGymnasium());
        existing.setWifi(request.getWifi());
        existing.setRoomService(request.getRoomService());
        existing.setAirCondition(request.getAirCondition());
        existing.setBreakfast(request.getBreakfast());

        return facilitiesRepo.save(existing);
    }
}
