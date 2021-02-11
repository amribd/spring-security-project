package com.jwt.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
	
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private int age;
	private String email;
	private String roleName;

}
