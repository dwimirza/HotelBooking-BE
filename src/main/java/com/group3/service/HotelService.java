package com.group3.service;

import com.group3.entity.Hotel;
import com.group3.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    private final HotelRepository repo;

    public HotelService(HotelRepository repo) {
        this.repo = repo;
    }

    public Hotel save(Hotel hotel) {
        return repo.save(hotel);
    }

    public List<Hotel> findAll() {
        return repo.findAll();
    }

    public Optional<Hotel> findById(Integer id) {
        return repo.findById(id);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public List<Hotel> search(String hotelName, String city) {
        if (hotelName != null && city != null) {
            return repo.findByHotelNameContainingIgnoreCaseAndCity(hotelName, city);
        }
        if (hotelName != null) {
            return repo.findByHotelNameContainingIgnoreCase(hotelName);
        }
        if (city != null) {
            return repo.findByCity(city);
        }
        return repo.findAll();
    }

    public List<Hotel> findByStarRating(Integer starRating) {
        return repo.findByStarRating(starRating);
    }

    public List<Hotel> findByCityAndStarRating(String city, Integer starRating) {
        return repo.findByCityAndStarRating(city, starRating);
    }
}
