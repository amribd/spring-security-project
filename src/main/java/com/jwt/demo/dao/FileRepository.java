package com.jwt.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jwt.demo.entity.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
	
	@Query(value = "SELECT f FROM FileEntity f WHERE f.user.userName = :username")
	List<FileEntity> findByUser(@Param("username") String username);

}
