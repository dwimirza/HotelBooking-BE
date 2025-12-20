package com.group3.service;

import com.group3.model.Facilities;
import com.group3.repository.FacilitiesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilitiesService {
    private final FacilitiesRepository facilitiesRepo;

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

    public Facilities create(Facilities facilities) {
        return facilitiesRepo.save(facilities);
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
