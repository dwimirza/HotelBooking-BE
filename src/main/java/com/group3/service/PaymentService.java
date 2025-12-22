package com.group3.service;

import com.group3.model.Payment;
import com.group3.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaymentService {
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    // CREATE
    public Payment create(Payment payment) {
        if (payment.getPaymentDate() == null) {
            payment.setPaymentDate(LocalDateTime.now());
        }
        if (payment.getStatus() == null) {
            payment.setStatus("pending");
        }
        return paymentRepository.save(payment);
    }
    
    // CREATE BULK
    public List<Payment> createBulk(List<Payment> payments) {
        payments.forEach(payment -> {
            if (payment.getPaymentDate() == null) {
                payment.setPaymentDate(LocalDateTime.now());
            }
            if (payment.getStatus() == null) {
                payment.setStatus("pending");
            }
        });
        return paymentRepository.saveAll(payments);
    }
    
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }
    
    public Optional<Payment> getById(Integer paymentId) {
        return paymentRepository.findById(paymentId);
    }
    
    // READ - Get payments by booking
    public List<Payment> getPaymentsByBooking(Integer bookingId) {
        return paymentRepository.findByBookingId(bookingId);
    }
    
    // READ - Get payments by status
    public List<Payment> getPaymentsByStatus(String status) {
        return paymentRepository.findByStatus(status);
    }
    
    // READ - Get payments by method
    public List<Payment> getPaymentsByMethod(String paymentMethod) {
        return paymentRepository.findByPaymentMethod(paymentMethod);
    }
    
    // READ - Get payments by date range
    public List<Payment> getPaymentsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return paymentRepository.findPaymentsByDateRange(startDate, endDate);
    }
    
    // UPDATE
    public Payment update(Integer paymentId, Payment paymentDetails) {
        return paymentRepository.findById(paymentId).map(payment -> {
            if (paymentDetails.getAmount() != null) {
                payment.setAmount(paymentDetails.getAmount());
            }
            if (paymentDetails.getPaymentMethod() != null) {
                payment.setPaymentMethod(paymentDetails.getPaymentMethod());
            }
            if (paymentDetails.getStatus() != null) {
                payment.setStatus(paymentDetails.getStatus());
            }
            if (paymentDetails.getPaymentDate() != null) {
                payment.setPaymentDate(paymentDetails.getPaymentDate());
            }
            return paymentRepository.save(payment);
        }).orElseThrow(() -> new RuntimeException("Payment not found with ID: " + paymentId));
    }
    
    // DELETE
    public void delete(Integer paymentId) {
        if (!paymentRepository.existsById(paymentId)) {
            throw new RuntimeException("Payment not found with ID: " + paymentId);
        }
        paymentRepository.deleteById(paymentId);
    }
    
    // DELETE BULK
    public void deleteBulk(List<Integer> paymentIds) {
        paymentIds.forEach(id -> {
            if (paymentRepository.existsById(id)) {
                paymentRepository.deleteById(id);
            }
        });
    }
    
    // SEARCH/FILTER
    public List<Payment> searchPayments(Integer bookingId, String status, String paymentMethod) {
        if (bookingId != null && status != null) {
            return paymentRepository.findByBookingIdAndStatus(bookingId, status);
        } else if (bookingId != null) {
            return paymentRepository.findByBookingId(bookingId);
        } else if (status != null) {
            return paymentRepository.findByStatus(status);
        } else if (paymentMethod != null) {
            return paymentRepository.findByPaymentMethod(paymentMethod);
        }
        return getAll();
    }
    
    // Get total amount by status
    public BigDecimal getTotalByStatus(String status) {
        return paymentRepository.getTotalByStatus(status);
    }
}
