package com.jwt.demo.serviceImp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.demo.dao.RoleRepository;
import com.jwt.demo.entity.RoleEntity;
import com.jwt.demo.response.ResponseApi;
import com.jwt.demo.service.RoleService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class RoleServiceImp implements RoleService {

@Autowired private RoleRepository roleRepository;
	
	public List<RoleEntity> getAllRolesOrByname(String name) {
		List<RoleEntity> roles = new ArrayList<RoleEntity>();
		if (name == null) {
			roles = roleRepository.findAll();
			if (roles.size() > -1) {
				log.info("list of roles was fetched");
			}
			else {
				log.info("list of roles was null");
			}
		} if (name != null) {
			RoleEntity role = roleRepository.findByName(name);
			if (role != null) {
				roles = Arrays.asList(role);
				log.info("role was fetched");
			} else {
				roles = null;
			}
		}
		return roles;
	}
	
	public ResponseApi saveRole(RoleEntity roleEntity) {
		RoleEntity role = roleRepository.findByName(roleEntity.getName());
		if (!roleEntity.getName().startsWith("ROLE_")) {
			System.out.println(roleEntity.getName());
			System.out.println(roleEntity.getName().startsWith("ROLE_"));
			log.info("the role must start with ROLE_");
			return new ResponseApi(false, "the role must start with ROLE_");
		} if (role != null) {
			log.info("role already exists");
			return new ResponseApi(false, "role already exists");
		} else {
			RoleEntity roleToSave = new RoleEntity();
			roleToSave.setName(roleEntity.getName());
			roleRepository.save(roleToSave);
			log.info("role was saved successfully");
			return new ResponseApi(true, "roles was saved successfully");
		}
	}
}
