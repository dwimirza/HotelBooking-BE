package com.group3.controller;

import com.group3.model.Room;
import com.group3.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    
    @Autowired
    private RoomService roomService;
     
    @PostMapping
    public ResponseEntity<Room> create(@RequestBody Room room) {
        Room createdRoom = roomService.create(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoom);
    }
    
    // @PostMapping("/bulk")
    // public ResponseEntity<List<Room>> createBulk(@RequestBody List<Room> rooms) {
    //     List<Room> createdRooms = roomService.createBulk(rooms);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(createdRooms);
    // }
    
    
    @GetMapping
    public ResponseEntity<List<Room>> getAll() {
        return ResponseEntity.ok(roomService.getAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Room> getById(@PathVariable Integer id) {
        return roomService.getById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Room>> getRoomsByHotel(@PathVariable Integer hotelId) {
        return ResponseEntity.ok(roomService.getRoomsByHotel(hotelId));
    }
    
    @GetMapping("/available")
    public ResponseEntity<List<Room>> getAvailableRooms() {
        return ResponseEntity.ok(roomService.getAvailableRooms());
    }
    
    @GetMapping("/available/hotel/{hotelId}")
    public ResponseEntity<List<Room>> getAvailableRoomsByHotel(@PathVariable Integer hotelId) {
        return ResponseEntity.ok(roomService.getAvailableRoomsByHotel(hotelId));
    }
    
    @GetMapping("/price-range")
    public ResponseEntity<List<Room>> getRoomsByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {
        return ResponseEntity.ok(roomService.getRoomsByPriceRange(minPrice, maxPrice));
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Room>> searchRooms(
            @RequestParam(required = false) String roomType,
            @RequestParam(required = false) Integer hotelId,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice) {
        return ResponseEntity.ok(roomService.searchRooms(roomType, hotelId, minPrice, maxPrice));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Room> update(@PathVariable Integer id, @RequestBody Room room) {
        try {
            Room updatedRoom = roomService.update(id, room);
            return ResponseEntity.ok(updatedRoom);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // @PutMapping("/{id}/decrease-availability")
    // public ResponseEntity<Room> decreaseAvailability(
    //         @PathVariable Integer id,
    //         @RequestParam Integer quantity) {
    //     try {
    //         Room updatedRoom = roomService.decreaseAvailability(id, quantity);
    //         return ResponseEntity.ok(updatedRoom);
    //     } catch (RuntimeException e) {
    //         return ResponseEntity.badRequest().build();
    //     }
    // }
    
    // @PutMapping("/{id}/increase-availability")
    // public ResponseEntity<Room> increaseAvailability(
    //         @PathVariable Integer id,
    //         @RequestParam Integer quantity) {
    //     try {
    //         Room updatedRoom = roomService.increaseAvailability(id, quantity);
    //         return ResponseEntity.ok(updatedRoom);
    //     } catch (RuntimeException e) {
    //         return ResponseEntity.badRequest().build();
    //     }
    // }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            roomService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // @DeleteMapping("/bulk")
    // public ResponseEntity<Void> deleteBulk(@RequestBody List<Integer> roomIds) {
    //     try {
    //         roomService.deleteBulk(roomIds);
    //         return ResponseEntity.noContent().build();
    //     } catch (RuntimeException e) {
    //         return ResponseEntity.badRequest().build();
    //     }
    // }
}

