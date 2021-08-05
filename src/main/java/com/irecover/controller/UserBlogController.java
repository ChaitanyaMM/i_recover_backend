package com.irecover.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irecover.entity.UserBlog;
import com.irecover.service.UserBlogService;
import com.irecover.service.UserService;


@RestController
@RequestMapping("/api/userblog")
public class UserBlogController {

	@Autowired
	private UserBlogService userBlogService;
	
	@Autowired
	private UserService userService;

	@PostMapping("/{userId}")
	public ResponseEntity<?> createBlog(@RequestBody UserBlog userBLog,@PathVariable Long userId) {

		UserBlog createdBlog = userBlogService.create(userBLog,userId);

		return new ResponseEntity<>(createdBlog, HttpStatus.CREATED);
	}

	@PutMapping("/{blogId}")
	public ResponseEntity<?> updateBlog(@PathVariable Long blogId, @RequestBody UserBlog userBLog) {

		UserBlog updatedBlog = userBlogService.update(blogId, userBLog);

		return new ResponseEntity<>(updatedBlog, HttpStatus.OK);
	}

	@DeleteMapping("/{blogId}")
	public ResponseEntity<?> deleteBlog(@PathVariable Long blogId) {
		userBlogService.deleteById(blogId);

		return new ResponseEntity<>(blogId, HttpStatus.OK);

	}

	@GetMapping("/{blogId}")
	public ResponseEntity<?> getBlog(@PathVariable Long blogId) {
		Optional<UserBlog> fetchedBlog = userBlogService.findById(blogId);

		return new ResponseEntity<>(fetchedBlog.get(), HttpStatus.OK);

	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getUserBlog(@PathVariable Long userId) {
		userService.findById(userId);
		UserBlog fetchedBlog = userBlogService.findByUserId(userService.findById(userId).get());

		return new ResponseEntity<>(fetchedBlog, HttpStatus.OK);

	}
	
	@GetMapping("/list")
	public ResponseEntity<?> getBlogList() {
		Iterable<UserBlog> fetchedBlogList = userBlogService.findAll();

		return new ResponseEntity<>(fetchedBlogList, HttpStatus.OK);

	}


}
