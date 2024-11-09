package com.example.doancuoiki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/home")
    public String home() {
        return "index";  // Tên của tệp HTML trong thư mục templates, không cần đuôi .html
    }
}
