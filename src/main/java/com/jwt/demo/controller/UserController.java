package com.jwt.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.demo.request.UserRequest;
import com.jwt.demo.response.ResponseApi;
import com.jwt.demo.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/user")
	public ResponseEntity<Object> saveUser(@RequestBody UserRequest request) {
		try {
			return new ResponseEntity<Object>(userService.saveUser(request), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new ResponseApi(false, "internal server"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/users")
	public ResponseEntity<Object> getUsers() {
		try {
			return new ResponseEntity<Object>(userService.getAllUsers(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new ResponseApi(false, "internal server"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
