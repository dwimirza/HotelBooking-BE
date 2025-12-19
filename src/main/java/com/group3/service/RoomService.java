package com.group3.service;

import com.group3.model.Room;
import com.group3.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoomService {
    
    @Autowired
    private RoomRepository roomRepository;
    
    // CREATE
    public Room create(Room room) {
        if (room.getAvailability() == null) {
            room.setAvailability(0);
        }
        return roomRepository.save(room);
    }
    
    // READ - Get all Roomss
    public List<Room> getAll() {
        return roomRepository.findAll();
    }
    
    // READ - Get room by ID
    public Optional<Room> getById(Integer roomId) {
        return roomRepository.findById(roomId);
    }
    
    // READ - Get rooms by hotel
    public List<Room> getRoomsByHotel(Integer hotelId) {
        return roomRepository.findByHotelId(hotelId);
    }
    
    // READ - Get available rooms
    public List<Room> getAvailableRooms() {
        return roomRepository.findByAvailabilityGreaterThan(0);
    }
    
    // READ - Get available rooms by hotel
    public List<Room> getAvailableRoomsByHotel(Integer hotelId) {
        return roomRepository.findAvailableRoomsByHotel(hotelId);
    }
    
    // READ - Get available rooms by hotel and type
    public List<Room> getAvailableRoomsByHotelAndType(Integer hotelId, String roomType) {
        return roomRepository.findAvailableRoomsByHotelAndType(hotelId, roomType);
    }
    
    // READ - Get rooms by price range
    public List<Room> getRoomsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return roomRepository.findRoomsByPriceRange(minPrice, maxPrice);
    }
    
    // READ - Get rooms by hotel and price range
    public List<Room> getRoomsByHotelAndPriceRange(Integer hotelId, BigDecimal minPrice, BigDecimal maxPrice) {
        return roomRepository.findRoomsByHotelAndPriceRange(hotelId, minPrice, maxPrice);
    }
    
    // UPDATE
    public Room update(Integer roomId, Room roomDetails) {
        return roomRepository.findById(roomId).map(room -> {
            if (roomDetails.getRoomType() != null) {
                room.setRoomType(roomDetails.getRoomType());
            }
            if (roomDetails.getPrice() != null) {
                room.setPrice(roomDetails.getPrice());
            }
            if (roomDetails.getAvailability() != null) {
                room.setAvailability(roomDetails.getAvailability());
            }
            // if (roomDetails.getHotel() != null) {
            //     room.setHotel(roomDetails.getHotel());
            // }
            return roomRepository.save(room);
        }).orElseThrow(() -> new RuntimeException("Room not found with ID: " + roomId));
    }
    
    // DELETE
    public void delete(Integer roomId) {
        if (!roomRepository.existsById(roomId)) {
            throw new RuntimeException("Room not found with ID: " + roomId);
        }
        roomRepository.deleteById(roomId);
    }
    
    // DELETE BULK
    public void deleteBulk(List<Integer> roomIds) {
        roomIds.forEach(id -> {
            if (roomRepository.existsById(id)) {
                roomRepository.deleteById(id);
            }
        });
    }
    
    // SEARCH/FILTER
    public List<Room> searchRooms(String roomType, Integer hotelId, BigDecimal minPrice, BigDecimal maxPrice) {
        if (hotelId != null && roomType != null) {
            return roomRepository.findAvailableRoomsByHotelAndType(hotelId, roomType);
        } else if (hotelId != null && minPrice != null && maxPrice != null) {
            return roomRepository.findRoomsByHotelAndPriceRange(hotelId, minPrice, maxPrice);
        } else if (hotelId != null) {
            return roomRepository.findByHotelId(hotelId);
        } else if (roomType != null) {
            return roomRepository.findByRoomTypeContainingIgnoreCase(roomType);
        } else if (minPrice != null && maxPrice != null) {
            return roomRepository.findRoomsByPriceRange(minPrice, maxPrice);
        }
        return getAll();
    }
    
    // Decrease availability (when room is booked)
    public Room decreaseAvailability(Integer roomId, Integer quantity) {
        return roomRepository.findById(roomId).map(room -> {
            int newAvailability = room.getAvailability() - quantity;
            if (newAvailability < 0) {
                throw new RuntimeException("Not enough rooms available");
            }
            room.setAvailability(newAvailability);
            return roomRepository.save(room);
        }).orElseThrow(() -> new RuntimeException("Room not found with ID: " + roomId));
    }
    
    // Increase availability (when booking is cancelled)
    public Room increaseAvailability(Integer roomId, Integer quantity) {
        return roomRepository.findById(roomId).map(room -> {
            room.setAvailability(room.getAvailability() + quantity);
            return roomRepository.save(room);
        }).orElseThrow(() -> new RuntimeException("Room not found with ID: " + roomId));
    }
}

