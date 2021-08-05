package com.irecover.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsS3Config {
//
//	@Value("${spring.application.name}")
//	private String appName;

	@Value("${cloud.aws.credentials.accessKey}")
	private String AWS_ACCESS_KEY_ID;

	@Value("${cloud.aws.credentials.secretKey}")
	private String AWS_SECRET_ACCESS_KEY;

//	@Bean
//	public AmazonS3 amazonS3Client(AWSCredentialsProvider credentialsProvider,
//			@Value("${cloud.aws.region.static}") String region) {
//		return AmazonS3ClientBuilder.standard().withCredentials(credentialsProvider).withRegion(region).build();
//	}

	@Bean
	public AmazonS3 S3() {
		AWSCredentials awsCredentials = new BasicAWSCredentials(AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY);

		return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.withRegion(Regions.AP_SOUTH_1).build();
	}

}
