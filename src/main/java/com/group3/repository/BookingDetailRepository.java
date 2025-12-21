package com.group3.repository;
import com.group3.model.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingDetailRepository extends JpaRepository<BookingDetail, Integer> {
    
    List<BookingDetail> findByBooking_BookingId(Integer bookingId);
    
    List<BookingDetail> findByRoom_RoomId(Integer roomId);
}