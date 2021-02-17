package com.jwt.demo.controller;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/download/{fileName:.+}")
	public ResponseEntity downloadFileFromLocal(@PathVariable String fileName) {
		Path path = Paths .get("C:\\files\\" + fileName);
		Resource resource = null;
		try {
			resource = new UrlResource(path.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("application/octet-stream."))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
}
