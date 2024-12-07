package com.example.doancuoiki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConfirmController {

    @PostMapping("/confirm")
    public String bookNow(@RequestParam("fieldName") String fieldName,
                          @RequestParam("bookingDate") String bookingDate,
//                          @RequestParam("bookingTime") String bookingTime,
                          Model model) {
        // Xử lý dữ liệu đặt sân (nếu cần)
        System.out.println("Tên sân: " + fieldName);
        System.out.println("Ngày đặt: " + bookingDate);
//        System.out.println("Thời gian: " + bookingTime);

        // Truyền thông tin sân vào model để hiển thị trên trang xác nhận
        model.addAttribute("fieldName", fieldName);
        model.addAttribute("bookingDate", bookingDate);
//        model.addAttribute("bookingTime", bookingTime);

        // Hình ảnh của sân, tùy theo tên sân
        if ("Sân 5-1".equals(fieldName)) {
            model.addAttribute("fieldImage", "/images/anh1.jpg");
        } else if ("Sân 5-2".equals(fieldName)) {
            model.addAttribute("fieldImage", "/images/anh6.jpg");
        } else if ("Sân 7".equals(fieldName)) {
            model.addAttribute("fieldImage", "/images/anh3.jpg");
        }

        // Chuyển hướng đến trang xác nhận (redirect)
        return "confirmation";  // Chuyển hướng đến trang xác nhận
    }
}
