package com.example.doancuoiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.doancuoiki.model.UserModel;
import com.example.doancuoiki.service.IUserServices;
import com.example.doancuoiki.utils.Constant;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Cookie;


@Controller
public class LoginController {

	@Autowired
    private IUserServices userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Chuyển hướng tới trang login.html
    }
    
    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam(value = "remember", required = false) String remember,
            HttpServletResponse response,
            HttpSession session,
            Model model) {

        boolean isRememberMe = "on".equals(remember);
        String alertMsg = "";

        // Kiểm tra trường hợp tài khoản hoặc mật khẩu rỗng
        if (username.isEmpty() || password.isEmpty()) {
            alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
            model.addAttribute("alert", alertMsg);
            return "user/login";
        }
    
        
        // Xử lý đăng nhập
        UserModel user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("account", user);
//            session.setAttribute("fullname", user.getFullname()); // Lưu fullname vào session
//            session.setAttribute(Constant.SESSION_ACCOUNT, user); // Lưu toàn bộ UserModel vào session

            if (isRememberMe) {
                saveRememberMe(response, username);
            }
            return "redirect:home"; // Đăng nhập thành công, chuyển hướng về trang chính
        } else {
            alertMsg = "Tài khoản hoặc mật khẩu không đúng";
            model.addAttribute("alert", alertMsg);
            return "/login"; // Quay lại trang đăng nhập kèm theo thông báo
        }
    }
    
    private void saveRememberMe(HttpServletResponse response, String username) {
        Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
        cookie.setMaxAge(30 * 60); // 30 phút
        response.addCookie(cookie);
    }
    
}
