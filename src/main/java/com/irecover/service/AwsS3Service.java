package com.irecover.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface AwsS3Service {

	public void uploadMultipleFiles(List<MultipartFile> files);
	
	public void uploadFile(MultipartFile file);

}
