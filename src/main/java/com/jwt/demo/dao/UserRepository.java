package com.jwt.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jwt.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query(value = "SELECT u FROM User u WHERE u.userName = :userName")
	User findUserByUserName(@Param("userName") String userName);

}
