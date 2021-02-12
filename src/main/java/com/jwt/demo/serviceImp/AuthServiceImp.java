package com.jwt.demo.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.demo.constants.ConstantAuthentication;
import com.jwt.demo.dao.UserRepository;
import com.jwt.demo.entity.User;
import com.jwt.demo.request.LoginRequest;
import com.jwt.demo.security.JwtProvider;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthServiceImp {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	public ResponseEntity<Object> authenticateUser(LoginRequest loginRequest) {
//		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		User user = userRepository.findUserByUserName(loginRequest.getUsername());
//		String token = jwtProvider.generateToken(loginRequest.getUsername());
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Authorization", );
//		log.info(ConstantAuthentication.CONNECTED);
//		return new ResponseEntity<Object>(new JwtAuthenticationResponse(token, "Bearer"), headers, HttpStatus.OK);
//	}
}
