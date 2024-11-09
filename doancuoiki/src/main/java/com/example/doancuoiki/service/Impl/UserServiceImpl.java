package com.example.doancuoiki.service.Impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.doancuoiki.model.UserModel;
import com.example.doancuoiki.respository.UserRepository;
import com.example.doancuoiki.service.IUserServices;

@Service
public class UserServiceImpl implements IUserServices {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserModel login(String username, String password) {
        Optional<UserModel> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent() && password.equals(userOpt.get().getPassword())) {
            return userOpt.get();
        }
        return null;
    }

    @Override
    public UserModel FindByUserName(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public void insert(UserModel user) {
        // Lưu mật khẩu trực tiếp, không mã hóa
        userRepository.save(user);
    }

    @Override
    public boolean register(String email, String password, String username, String fullname, String phone) {
        if (checkExistEmail(email) || checkExistUsername(username) || checkExistPhone(phone)) {
            return false;
        }

        UserModel user = new UserModel();
        user.setEmail(email);
        user.setPassword(password); // Lưu mật khẩu trực tiếp, không mã hóa
        user.setUsername(username);
        user.setFullname(fullname);
        user.setPhone(phone);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }

    @Override
    public String generateResetToken(String email) {
        UserModel user = FindByEmail(email);
        if (user == null) {
            return null;
        }

        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        userRepository.save(user);
        return token;
    }

    @Override
    public boolean isValidToken(String token) {
        return userRepository.existsByResetToken(token);
    }

    @Override
    public UserModel findById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void update(UserModel user) {
        userRepository.save(user);
    }

    @Override
    public UserModel FindByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
