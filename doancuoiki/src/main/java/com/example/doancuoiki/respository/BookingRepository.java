package com.example.doancuoiki.respository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.doancuoiki.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	// Tìm kiếm các đặt sân theo ngày
    List<Booking> findByDate(LocalDate date);

    // Tìm kiếm các đặt sân theo ngày và phân trang
    Page<Booking> findByDate(LocalDate date, Pageable pageable);
}
