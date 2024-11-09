package com.example.doancuoiki.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.doancuoiki.entity.Booking;


public interface IBookingService {

	 // Tìm theo ngày đặt sân (với LocalDate)
    List<Booking> findByBookingDate(LocalDate bookingDate); 

    // Tìm theo ngày đặt sân có phân trang (với LocalDate)
    Page<Booking> findByBookingDate(LocalDate bookingDate, Pageable pageable);

    <S extends Booking> S save(S entity); // Lưu một đặt chỗ

    <S extends Booking> Optional<S> findOne(Example<S> example); // Tìm kiếm theo một ví dụ cụ thể

    List<Booking> findAll(Sort sort); // Tìm tất cả và sắp xếp

    Page<Booking> findAll(Pageable pageable); // Tìm tất cả và phân trang

    List<Booking> findAllById(Iterable<Long> ids); // Tìm tất cả theo danh sách ID

    Optional<Booking> findById(Long id); // Tìm theo ID

    long count(); // Đếm số lượng đặt chỗ

    void deleteById(Long id); // Xóa đặt chỗ theo ID

    void delete(Booking entity); // Xóa một đặt chỗ

    void deleteAll(); // Xóa tất cả đặt chỗ

    List<Booking> findAll(); // Tìm tất cả đặt chỗ

}
