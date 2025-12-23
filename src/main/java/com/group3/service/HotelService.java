package com.group3.service;

import com.group3.model.Hotel;
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

    public Optional<Hotel> findByName(String hotelName) {
        return repo.findByHotelName(hotelName);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public List<Hotel> search(String hotelName, Integer starRating) {
         if (hotelName == null && starRating == null) {
            return repo.findAll();
        } else if (hotelName != null && starRating == null) {
            return repo.findByHotelNameContainingIgnoreCase(hotelName);
        } else if (hotelName == null && starRating != null) {
            return repo.findByStarRating(starRating);
        } else {
            return repo.findByHotelNameContainingIgnoreCaseAndStarRating(hotelName, starRating);
        }
    }

    public List<Hotel> findByStarRating(Integer starRating) {
        return repo.findByStarRating(starRating);
    }

    public List<Hotel> findByCityAndStarRating(String city, Integer starRating) {
        return repo.findByCityAndStarRating(city, starRating);
    }
    
}
