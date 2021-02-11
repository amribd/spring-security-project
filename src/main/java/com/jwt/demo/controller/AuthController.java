package com.jwt.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.demo.dao.UserRepository;
import com.jwt.demo.entity.User;
import com.jwt.demo.request.LoginRequest;
import com.jwt.demo.security.JwtProvider;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AuthController {
	
	@Autowired private UserRepository userRepository;
	
	@Autowired private JwtProvider jwtProvider;
	
	@Autowired private PasswordEncoder passwordEncoder;
	
	@PostMapping(value = "/login")
	public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
		try {
			User user = userRepository.findUserByUserName(loginRequest.getUsername());
			if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
				String token = jwtProvider.generateToken(user.getUserName());
				return new ResponseEntity<Object>(token, HttpStatus.OK);
			} return new ResponseEntity<Object>("this credentials doesn't exists", HttpStatus.OK);
		} catch (Exception e) {
			log.error("internal server");
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
