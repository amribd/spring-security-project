package com.jwt.demo.service;

import java.util.List;

import com.jwt.demo.entity.RoleEntity;
import com.jwt.demo.response.ResponseApi;

public interface RoleService {

	List<RoleEntity> getAllRolesOrByname(String name);
	ResponseApi saveRole(RoleEntity roleEntity);
}
