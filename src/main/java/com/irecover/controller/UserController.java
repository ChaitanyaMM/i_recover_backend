package com.irecover.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.irecover.dto.LoginDTO;
import com.irecover.entity.Gender;
import com.irecover.entity.User;
import com.irecover.exception.DataConflictException;
import com.irecover.exception.InvalidInputException;
import com.irecover.service.AwsS3Service;
import com.irecover.service.FileStorage;
import com.irecover.service.UserService;
import com.irecover.util.PasswordEncryptDecryptor;

import com.irecover.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/user")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	private final static String default_url = "https://i-recover-backend.s3.ap-south-1.amazonaws.com/male-icon-7928.png";
	private final static String default_image = "male-icon-7928.png";

	private final static String default_female_url = "https://i-recover-backend.s3.ap-south-1.amazonaws.com/female-icon.png";
	private final static String default_female_image = "female-icon.png";

	private final static String s3Path = "https://i-recover-backend.s3.ap-south-1.amazonaws.com/";

	@Autowired
	private UserService userService;

	@Autowired
	private AwsS3Service awsS3Service;

	@Autowired
	private FileStorage fileStorage;

	private User imageUpload(User user, MultipartFile profileImage, boolean update) {
		User userDetails = user;
		if (update) {
			uploadFile(profileImage, userDetails);
		} else {
			if (profileImage == null) {
				if (user.getGenderType().equals(Gender.MALE)) {
					userDetails.setProfileImageName(default_image);
					userDetails.setProfileImageUrl(default_url);
				} else {
					userDetails.setProfileImageName(default_female_image);
					userDetails.setProfileImageUrl(default_female_url);
				}

			} else {
				uploadFile(profileImage, userDetails);
			}
		}
		return userDetails;
	}

	private User s3Upload(User user, MultipartFile profileImage, boolean update) {
		User userDetails = user;
		if (update) {
			awsS3Service.uploadFile(profileImage);
			userDetails.setProfileImageName(profileImage.getOriginalFilename());
			userDetails.setProfileImageUrl(s3Path + profileImage.getOriginalFilename());
		} else {
			if (profileImage == null) {
				if (user.getGenderType().equals(Gender.MALE)) {
					userDetails.setProfileImageName(default_image);
					userDetails.setProfileImageUrl(default_url);
				} else {
					userDetails.setProfileImageName(default_female_image);
					userDetails.setProfileImageUrl(default_female_url);
				}
			} else {
				userDetails.setProfileImageName(profileImage.getOriginalFilename());
				userDetails.setProfileImageUrl(s3Path + profileImage.getOriginalFilename());
				awsS3Service.uploadFile(profileImage);
			}
		}
		return userDetails;

	}

	private void uploadFile(MultipartFile profileImage, User userDetails) {
		try {
			fileStorage.storeUserProfileImage(profileImage);
			log.info("File uploaded successfully! -> filename = " + profileImage.getOriginalFilename());
		} catch (Exception e) {
			log.info("Fail! -> uploaded filename: = " + profileImage.getOriginalFilename());
		}

		Resource path = fileStorage.loadprofileImage(profileImage.getOriginalFilename());
		System.out.println("PATH :=" + path.toString());
		userDetails.setProfileImageName(profileImage.getOriginalFilename());
		userDetails.setProfileImageUrl(path.toString());
	}

	@PostMapping("/signup")
	public ResponseEntity<?> userSignUp(@RequestParam("user") String user,
			@RequestParam(value = "file", required = false) MultipartFile profileImage) throws Exception {
		User userData = null;
		userData = new ObjectMapper().readValue(user, User.class);

		User isemailExists = userService.findByEmail(userData.getEmail());
		if (isemailExists != null) {
			log.error("email exists");
			throw new DataConflictException("Email already exists, please choose different email id");
		}
		userData.setPassword(PasswordEncryptDecryptor.encrypt(userData.getPassword()));
		User userDetails = s3Upload(userData, profileImage, false);
		userData = userService.save(userDetails);

		return new ResponseEntity<>(userData, HttpStatus.CREATED);
	}

	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@RequestBody LoginDTO loginDTO) throws Exception {

		User user = userService.findByEmail(loginDTO.getEmail());
		if (user == null) {
			log.error("Email Not Found");
			throw new ResourceNotFoundException("Email Not Found!");
		}
		String pwd = PasswordEncryptDecryptor.encrypt(loginDTO.getPassword());
		if (!pwd.equalsIgnoreCase(user.getPassword())) {
			log.error("Invalid password");
			throw new ResourceNotFoundException("Invalid password!");
		}

		// final String token = jwtTokenGenerator.generateToken(user);
		return new ResponseEntity<>("login succesful", HttpStatus.OK);

	}

	@PostMapping("/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable(value = "userId") Long userId, @RequestParam("user") String user,
			@RequestParam(value = "file", required = false) MultipartFile profileImage) {
		User updatedUser = null;
		try {
			updatedUser = new ObjectMapper().readValue(user, User.class);
			if (user != null) {
				User fetchEmail = userService.findByEmail(updatedUser.getEmail());
				if (fetchEmail != null) {
					// User userDetails = imageUpload(user, profileImage, true);
					User userDetails = s3Upload(updatedUser, profileImage, true);
					updatedUser = userService.update(userDetails, userId);
				} else {
					throw new ResourceNotFoundException("Not_Found!");
				}
			} else {
				throw new InvalidInputException("invalidInput!");
			}
		} catch (Exception e) {
			log.error("failed {} " + e.getMessage());
		}
		return new ResponseEntity<>("Successful", HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> getUser(@PathVariable Long userId) {

		Optional<User> fetchedUser = userService.findById(userId);
		if (!fetchedUser.isPresent()) {
			throw new ResourceNotFoundException("User Not_Found");
		}
		return new ResponseEntity<>(fetchedUser.get(), HttpStatus.OK);

	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
		userService.deleteById(userId);
		return new ResponseEntity<>("Successful", HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<?> getUsers() {
		Iterable<User> fetchedUsersList = userService.findAll();
		return new ResponseEntity<>(fetchedUsersList, HttpStatus.OK);

	}

}
