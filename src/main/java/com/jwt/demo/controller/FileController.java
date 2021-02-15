package com.jwt.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jwt.demo.response.ResponseApi;
import com.jwt.demo.serviceImp.FileServiceImpl;

@RestController
public class FileController {

	@Autowired
	private FileServiceImpl fileService;
	
	@PostMapping(value = "/file")
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			return new ResponseEntity<Object>(fileService.uploadFile(file), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new ResponseApi(false, "internal server"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/files")
	public ResponseEntity<Object> allFiles() {
		try {
			return new ResponseEntity<Object>(fileService.getAllFiles(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new ResponseApi(false, "internal server"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/own/files")
	public ResponseEntity<Object> getFilesByUser() {
		try {
			return new ResponseEntity<Object>(fileService.getFilesByUser(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new ResponseApi(false, "internal server"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
