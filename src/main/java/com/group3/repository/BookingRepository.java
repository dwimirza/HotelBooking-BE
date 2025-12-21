package com.group3.repository;

import com.group3.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByUser_UserId(Integer userId);
    
    List<Booking> findByHotel_HotelId(Integer hotelId);
    
    List<Booking> findByStatus(String status);
    
    List<Booking> findByUser_UserIdAndStatus(Integer userId, String status);
    
}
