package com.jwt.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.demo.entity.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

}
