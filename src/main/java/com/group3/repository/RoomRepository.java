package com.group3.repository;

import com.group3.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    
    @Query("SELECT r FROM Room r WHERE r.hotel.id = :hotelId")
    List<Room> findByHotelId(@Param("hotelId") Integer hotelId);
    
    List<Room> findByRoomTypeContainingIgnoreCase(String roomType);
    
    List<Room> findByAvailabilityGreaterThan(Integer availability);
    
    @Query("SELECT r FROM Room r WHERE r.hotel.id = :hotelId AND r.availability > 0")
    List<Room> findAvailableRoomsByHotel(@Param("hotelId") Integer hotelId);
    
    @Query("SELECT r FROM Room r WHERE r.hotel.id = :hotelId AND r.roomType = :roomType AND r.availability > 0")
    List<Room> findAvailableRoomsByHotelAndType(
        @Param("hotelId") Integer hotelId, 
        @Param("roomType") String roomType
    );
    
    @Query("SELECT r FROM Room r WHERE r.price BETWEEN :minPrice AND :maxPrice")
    List<Room> findRoomsByPriceRange(
        @Param("minPrice") BigDecimal minPrice,
        @Param("maxPrice") BigDecimal maxPrice
    );
    
    @Query("SELECT r FROM Room r WHERE r.hotel.id = :hotelId AND r.price BETWEEN :minPrice AND :maxPrice")
    List<Room> findRoomsByHotelAndPriceRange(
        @Param("hotelId") Integer hotelId,
        @Param("minPrice") BigDecimal minPrice,
        @Param("maxPrice") BigDecimal maxPrice
    );
}
