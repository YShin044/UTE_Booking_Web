package com.example.doancuoiki.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.doancuoiki.entity.Booking;
import com.example.doancuoiki.model.UserModel;
import com.example.doancuoiki.respository.BookingRepository;
import com.example.doancuoiki.respository.UserRepository;
import com.example.doancuoiki.service.IUserServices;
import com.example.doancuoiki.utils.Constant;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	 @Autowired
	    private BookingRepository bookingRepository;
	 @Autowired
	    private UserRepository userRepository;
	
	@Autowired
	private IUserServices userService;

	private void addUserToModel(HttpSession session, Model model) {
		String fullname = (String) session.getAttribute(Constant.SESSION_FULLNAME);
		String account = (String) session.getAttribute(Constant.SESSION_ACCOUNT);

		if (account != null && fullname != null) {
			model.addAttribute("fullname", fullname);
		}
	}

	@GetMapping("/admin")
	public String adminhome(HttpSession session, Model model) {
		addUserToModel(session, model); // Gọi phương thức để thêm thông tin người dùng vào model
		List<UserModel> users = userService.getalluser();
		List<UserModel> filteredUsers = users.stream().filter(user -> user.getStatus() != 2).toList();
		long filteredUser = filteredUsers.size();
		model.addAttribute("totalUsers", filteredUser);
		   
		
		 List<Booking> unconfirmedBookings = bookingRepository.findByConfirmNull();
		    // Đếm số lượng đơn chưa xác nhận
		    long unconfirmedCount = unconfirmedBookings.size();
		    // Thêm số lượng đơn chưa xác nhận vào model để hiển thị trên trang admin
		    model.addAttribute("unconfirmedCount", unconfirmedCount);
		
		
		
		
		
		return "adminhome"; // Trả về trang home.html
		
	}

	@GetMapping("/admin/user")
	public String adminuser(HttpSession session, Model model) {
		addUserToModel(session, model);

		// Lấy danh sách user từ service
		List<UserModel> users = userService.getalluser();

		// Lọc user có status khác 2
		List<UserModel> filteredUsers = users.stream().filter(user -> user.getStatus() != 2).toList();

		// Thêm danh sách đã lọc vào model
		model.addAttribute("users", filteredUsers);
		return "ManaUser";
	}

	@GetMapping("/admin/user/delete/{id}")
	public String userdelete(@PathVariable int id, HttpSession session, Model model) {
		addUserToModel(session, model);
		userService.deleteuser(id);
		return "redirect:/admin/user";
	}

	@GetMapping("/admin/user/edit/{id}")
	public String userEdit(@PathVariable("id") int id, HttpSession session, Model model) {
		addUserToModel(session, model);
		UserModel user = userService.findById(id);
		if (user != null) {
			model.addAttribute("user", user);
		} else {
			return "redirect:/admin/user";
		}
		return "ManaUserEdit";
	}

	@PostMapping("/admin/user/edit/{id}")
	public String useredit(@RequestParam("id") int id, @RequestParam("fullname") String name,
			@RequestParam("email") String email, @RequestParam("phone") String phone, HttpSession session,
			Model model) {
		addUserToModel(session, model);
		userService.updateuser(name, phone, email, id);
		return "redirect:/admin/user";
	}
	
	
	
	
	
	
	
	
	
	@PostMapping("/admin/delete-booking")
	@ResponseBody
	public ResponseEntity<?> deleteBooking(@RequestParam Long bookingId) {
	    if (bookingRepository.existsById(bookingId)) {
	        bookingRepository.deleteById(bookingId);
	        return ResponseEntity.ok().build();
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
	    }
	}
	@PostMapping("/admin/confirm-booking")
	@ResponseBody
	public ResponseEntity<?> confirmBooking(@RequestParam Long bookingId) {
	    Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
	    if (bookingOpt.isPresent()) {
	        Booking booking = bookingOpt.get();
	        booking.setConfirm(true); // Xác nhận đặt chỗ
	        bookingRepository.save(booking);
	        return ResponseEntity.ok().build();
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
	    }
	}
	
	
	
	
	//Xem toàn bộ đơn hàng
	@GetMapping("/admin/bookings")
	public String checkBooking(Model model) {
	
		List<Booking> bookings = bookingRepository.findAll();
		model.addAttribute("bookings", bookings);
		return "adminbooking";
	}
	
}

