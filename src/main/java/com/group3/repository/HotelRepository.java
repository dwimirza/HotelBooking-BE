package com.group3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group3.model.Hotel;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    List<Hotel> findByHotelNameContainingIgnoreCase(String hotelName);

    List<Hotel> findByCity(String city);

    List<Hotel> findByHotelNameContainingIgnoreCaseAndCity(
            String hotelName, String city);

    List<Hotel> findByHotelNameContainingIgnoreCaseAndStarRating(
            String hotelName, Integer starRating);

    List<Hotel> findByStarRating(Integer starRating);

    List<Hotel> findByCityAndStarRating(String city, Integer starRating);

    List<Hotel> findByHotelNameContainingIgnoreCaseAndCityAndStarRating(
            String hotelName, String city, Integer starRating);

        java.util.Optional<Hotel> findByHotelName(String hotelName);
}
