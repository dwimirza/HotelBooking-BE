package com.group3.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group3.model.Booking;
import com.group3.model.BookingDetail;
import com.group3.model.Hotel;
import com.group3.model.Room;
import com.group3.model.request.BookingRequest;
import com.group3.model.request.BookingRequest.RoomBookingRequest;
import com.group3.repository.BookingRepository;
import com.group3.repository.HotelRepository;
import com.group3.repository.RoomRepository;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    public BookingService(BookingRepository bookingRepository,
            HotelRepository hotelRepository,
            RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

    @Transactional
    public Booking createBooking(BookingRequest request) {
        // Validasi hotel exist
        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));

        // Build booking
        Booking booking = Booking.builder()
                .hotel(hotel)
                .bookingDate(LocalDateTime.now())
                .status("PENDING")
                .totalAmount(request.getTotalAmount())
                .build();

        // Add booking details
        for (RoomBookingRequest roomReq : request.getRooms()) {
            Room room = roomRepository.findById(roomReq.getRoomId())
                    .orElseThrow(() -> new IllegalArgumentException("Room not found: " + roomReq.getRoomId()));

            BookingDetail detail = BookingDetail.builder()
                    .room(room)
                    .pricePerNight(roomReq.getPricePerNight())
                    .checkIn(roomReq.getCheckIn())
                    .checkOut(roomReq.getCheckOut())
                    .specialRequest(roomReq.getSpecialRequest())
                    .build();

            booking.addBookingDetail(detail);
        }

        // Save (cascade save semua details)
        return bookingRepository.save(booking);
    }

    // Method tambahan untuk READ
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Integer bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
    }

    public List<Booking> getBookingsByUserId(Integer userId) {
        return bookingRepository.findByUser_UserId(userId);
    }

    public List<Booking> getBookingsByStatus(String status) {
        return bookingRepository.findByStatus(status);
    }

    @Transactional
    public Booking updateBookingStatus(Integer bookingId, String newStatus) {
        Booking booking = getBookingById(bookingId);
        booking.setStatus(newStatus);
        return bookingRepository.save(booking);
    }

    @Transactional
    public void cancelBooking(Integer bookingId) {
        Booking booking = getBookingById(bookingId);
        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);
    }
}
