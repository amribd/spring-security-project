package com.jwt.demo.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jwt.demo.dao.RoleRepository;
import com.jwt.demo.dao.UserRepository;
import com.jwt.demo.entity.RoleEntity;
import com.jwt.demo.entity.User;
import com.jwt.demo.request.UserRequest;
import com.jwt.demo.response.ResponseApi;
import com.jwt.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImp implements UserService {
	
	@Autowired private UserRepository userRepository;
	
	@Autowired private RoleRepository roleRepository;
	
	@Autowired private PasswordEncoder passwordEncoder;
	
	public ResponseApi saveUser(UserRequest userRequest) {
		User userFound = userRepository.findUserByUserName(userRequest.getUserName());
		RoleEntity roleEntity = roleRepository.findByName(userRequest.getRoleName());
		if (!StringUtils.hasText(userRequest.getFirstName()) && !StringUtils.hasText(userRequest.getLastName()) &&  
			!StringUtils.hasText(userRequest.getUserName()) && !StringUtils.hasText(userRequest.getEmail()) ) {
			log.info("some of the field are not valid");
			return new ResponseApi(false, "please enter a valid field");
		} if (userFound != null) {
			log.info("This username already exists");
			return new ResponseApi(false, "This username already exists");
		} else {
			User userToSave = new User();
			userToSave.setAge(userRequest.getAge());
			userToSave.setFirstName(userRequest.getFirstName());
			userToSave.setLastName(userRequest.getLastName());
			userToSave.setUserName(userRequest.getUserName());
			userToSave.setEmail(userRequest.getEmail());
			userToSave.setRole(roleEntity);
			userToSave.setPassword(passwordEncoder.encode(userRequest.getPassword()));
			userRepository.save(userToSave);
			log.info("user saved successfully");
			return new ResponseApi(true, "user saved successfully");
		}
	}
	
	public List<User> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}

}
