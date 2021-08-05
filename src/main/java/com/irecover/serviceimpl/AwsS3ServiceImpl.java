package com.irecover.serviceimpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.irecover.service.AwsS3Service;

@Service
public class AwsS3ServiceImpl implements AwsS3Service {

	Logger log = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private AmazonS3 amazonS3Client;

	@Value("${app.awsServices.bucketName}")
	private String bucketName;

	@Override
	public void uploadMultipleFiles(List<MultipartFile> files) {
		if (files != null) {
			files.forEach(multipartFile -> {
				File file = convertMultiPartFileToFile(multipartFile);
				String uniqueFileName = multipartFile.getOriginalFilename();
				uploadFileToS3bucket(uniqueFileName, file, bucketName);
			});
		}
	}
	
	@Override
	public void uploadFile(MultipartFile file) {

		File object = convertMultiPartFileToFile(file);
		String uniqueFileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		uploadFileToS3bucket(uniqueFileName, object, bucketName);
	

	}

	private void uploadFileToS3bucket(String fileName, File file, String bucketName) {
		amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, file));

	}

	private S3Object downloadFileFromS3bucket(String fileName, String bucketName) {
		S3Object object = amazonS3Client.getObject(bucketName, fileName);
		return object;

	}

	private void deleteFileFromS3bucket(String fileName, String bucketName) {
		amazonS3Client.deleteObject(bucketName, fileName);
	}

	private File convertMultiPartFileToFile(MultipartFile file) {
		File convertedFile = new File(file.getOriginalFilename());
		try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
			fos.write(file.getBytes());
		} catch (IOException e) {
			log.error("Error converting multipartFile to file", e);
		}
		return convertedFile;
	}

}
