package com.jwt.demo.service;

import java.util.List;

import com.jwt.demo.entity.User;
import com.jwt.demo.request.UserRequest;
import com.jwt.demo.response.ResponseApi;

public interface UserService {

	public ResponseApi saveUser(UserRequest userRequest);
	public List<User> getAllUsers();
	
}
