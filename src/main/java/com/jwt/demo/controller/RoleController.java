package com.jwt.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.demo.entity.RoleEntity;
import com.jwt.demo.response.ResponseApi;
import com.jwt.demo.service.RoleService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@GetMapping(value = "/roles")
	public ResponseEntity<Object> getAllRoleOrByName(@RequestParam(required = false) String name) {
		try {
			List<RoleEntity> rolesOrByname = roleService.getAllRolesOrByname(name);
			return new ResponseEntity<Object>(rolesOrByname, HttpStatus.OK);
		} catch (Exception e) {
			log.error("internal server");
			return new ResponseEntity<Object>(new ResponseApi(false, "inrernal server"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/role")
	public ResponseEntity<Object> addRole(@RequestBody RoleEntity roleEntity) {
		try {
			return new ResponseEntity<Object>(roleService.saveRole(roleEntity), HttpStatus.OK);
		} catch (Exception e) {
			log.error("internal server");
			return new ResponseEntity<Object>(new ResponseApi(false, "inrernal server"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
