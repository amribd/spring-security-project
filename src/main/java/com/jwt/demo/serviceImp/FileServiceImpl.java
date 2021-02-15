package com.jwt.demo.serviceImp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;

import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jwt.demo.dao.FileRepository;
import com.jwt.demo.dao.UserRepository;
import com.jwt.demo.entity.FileEntity;
import com.jwt.demo.entity.User;
import com.jwt.demo.response.ResponseApi;

@Service
public class FileServiceImpl {
	
	@Value("${path.file}")
	String pathFile;
	
	@Autowired private FileRepository fileRepository;
	@Autowired private UserRepository userRepository;
	
	public ResponseApi uploadFile(MultipartFile file) {
		Path target = Paths.get(pathFile + file.getOriginalFilename());
		try {
			Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseApi(false, e.getMessage());
		}
		saveFile(file);
		return new ResponseApi(true, "file was copied successfuly by " + authenticatedUser().getUserName());
	}
	
	public List<FileEntity> getAllFiles() {
		List<FileEntity> files = fileRepository.findAll();
		return files;
	}
	
	private User authenticatedUser() {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 return userRepository.findUserByUserName(authentication.getName());
	}
	
	private void saveFile(MultipartFile file) {
		FileEntity fileEntity = new FileEntity();
		fileEntity.setFileName(FilenameUtils.removeExtension(file.getOriginalFilename()));
		fileEntity.setExtension(FilenameUtils.getExtension(file.getOriginalFilename()));
		fileEntity.setUser(authenticatedUser());
		fileRepository.save(fileEntity);
	}
	
	public List<FileEntity> getFilesByUser() {
		List<FileEntity> filesByUser = fileRepository.findByUser(authenticatedUser().getUserName());
		return filesByUser;
	}
	
	

}
