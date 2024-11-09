package com.example.doancuoiki.controller.admin;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.doancuoiki.entity.Booking;
import com.example.doancuoiki.service.IBookingService;

@RestController
@RequestMapping("/admin/bookings")
public class AdminBookingController {

	@Autowired
    private IBookingService bookingService;
	
	// API để lấy danh sách tất cả các booking
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.findAll();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
	
    
    // API để lấy booking theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = bookingService.findById(id);
        return booking.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // API để tìm kiếm các booking theo ngày
    @GetMapping("/date")
    public ResponseEntity<List<Booking>> getBookingsByDate(@RequestParam String date) {
        LocalDate bookingDate = LocalDate.parse(date); // Chuyển đổi từ String sang LocalDate
        List<Booking> bookings = bookingService.findByBookingDate(bookingDate);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
    
    
    // API để tìm kiếm các booking theo ngày với phân trang
    @GetMapping("/date/paged")
    public ResponseEntity<Page<Booking>> getBookingsByDatePaged(
            @RequestParam String date,
            Pageable pageable) {
        LocalDate bookingDate = LocalDate.parse(date);
        Page<Booking> bookings = bookingService.findByBookingDate(bookingDate, pageable);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
    
    
    // API để tạo mới một booking
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking savedBooking = bookingService.save(booking);
        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }
    
    
    // API để cập nhật thông tin một booking
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking bookingDetails) {
        Optional<Booking> existingBooking = bookingService.findById(id);

        if (existingBooking.isPresent()) {
            Booking booking = existingBooking.get();
            booking.setDate(bookingDetails.getDate());
            booking.setTime(bookingDetails.getTime());
            booking.setField(bookingDetails.getField());
            booking.setStatus(bookingDetails.getStatus());
            booking.setUser(bookingDetails.getUser());

            Booking updatedBooking = bookingService.save(booking);
            return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // API để xóa một booking theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        if (bookingService.findById(id).isPresent()) {
            bookingService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
