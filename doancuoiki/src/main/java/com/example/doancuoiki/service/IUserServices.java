package com.example.doancuoiki.service;

import com.example.doancuoiki.model.UserModel;

public interface IUserServices {

	UserModel login (String username, String password);
	
	
	UserModel FindByUserName(String username);
	
	
	void insert(UserModel user);
	
	boolean register(String email, String password, String username, String fullname, String phone);
	
	boolean checkExistEmail(String email);
	
	boolean checkExistUsername(String username);
	
	boolean checkExistPhone(String phone);

	String generateResetToken(String email);

	boolean isValidToken(String token);

	UserModel findById(int userId);

	void update(UserModel user);


	UserModel FindByEmail(String email);


	

}
