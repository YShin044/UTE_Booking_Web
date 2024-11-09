package com.example.doancuoiki.service.Impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.doancuoiki.entity.Booking;
import com.example.doancuoiki.respository.BookingRepository;
import com.example.doancuoiki.service.IBookingService;


@Service
public class BookingServiceImpl implements IBookingService {

	
	@Autowired
    BookingRepository bookingRepository;
	
	public BookingServiceImpl(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}
	
	
	// Tìm kiếm theo ngày đặt sân
    @Override
    public List<Booking> findByBookingDate(LocalDate bookingDate) {
        return bookingRepository.findByDate(bookingDate);
    }

    // Tìm kiếm theo ngày đặt sân có phân trang
    @Override
    public Page<Booking> findByBookingDate(LocalDate bookingDate, Pageable pageable) {
        return bookingRepository.findByDate(bookingDate, pageable);
    }

	@Override
	public <S extends Booking> S save(S entity) {
		// Lưu một đối tượng Booking vào cơ sở dữ liệu
        return bookingRepository.save(entity);
	}

	@Override
	public <S extends Booking> Optional<S> findOne(Example<S> example) {
		// Tìm kiếm một đối tượng Booking theo ví dụ được cung cấp
        return bookingRepository.findOne(example);
	}

	@Override
	public List<Booking> findAll(Sort sort) {
		// Tìm tất cả các đối tượng Booking và sắp xếp theo tiêu chí sort
        return bookingRepository.findAll(sort);
	}

	@Override
	public Page<Booking> findAll(Pageable pageable) {
		// Tìm tất cả các đối tượng Booking và phân trang
        return bookingRepository.findAll(pageable);
	}

	@Override
	public List<Booking> findAllById(Iterable<Long> ids) {
		// Tìm tất cả các đối tượng Booking theo danh sách ID
        return bookingRepository.findAllById(ids);
	}

	@Override
	public Optional<Booking> findById(Long id) {
		// Tìm một đối tượng Booking theo ID
        return bookingRepository.findById(id);
	}

	@Override
	public long count() {
		// Đếm tổng số lượng đối tượng Booking trong cơ sở dữ liệu
        return bookingRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		// Xóa một đối tượng Booking theo ID
        bookingRepository.deleteById(id);
		
	}

	@Override
	public void delete(Booking entity) {
		// Xóa một đối tượng Booking cụ thể
        bookingRepository.delete(entity);
		
	}

	@Override
	public void deleteAll() {
		// Xóa tất cả các đối tượng Booking
        bookingRepository.deleteAll();
		
	}

	@Override
	public List<Booking> findAll() {
		// Tìm tất cả các đối tượng Booking
        return bookingRepository.findAll();
	}

}
