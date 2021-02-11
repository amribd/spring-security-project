package com.jwt.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jwt.demo.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
	
	@Query(value = "SELECT r FROM RoleEntity r WHERE r.name = :name")
	RoleEntity findByName(@Param("name") String name);

}
