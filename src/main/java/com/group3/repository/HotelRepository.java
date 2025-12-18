package com.group3.repository;

import com.group3.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    List<Hotel> findByHotelNameContainingIgnoreCase(String hotelName);

    List<Hotel> findByCity(String city);

    List<Hotel> findByHotelNameContainingIgnoreCaseAndCity(
            String hotelName, String city);

    List<Hotel> findByStarRating(Integer starRating);

    List<Hotel> findByCityAndStarRating(String city, Integer starRating);
}
