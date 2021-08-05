package com.irecover.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.irecover.entity.QuestionEntity;
import com.irecover.entity.Questioner;
import com.irecover.service.QuestionerService;
import com.irecover.service.QuestionsService;

@RestController
@RequestMapping("/api/questioner")
public class QuestionerController {

	@Autowired
	private QuestionerService questionerService;

	@Autowired
	private QuestionsService qnsService;

	@PostMapping("/{userId}")
	public ResponseEntity<?> createQuestion(@RequestBody Questioner questioner, Long userId) {

		Questioner createdQuestion = questionerService.create(questioner, userId);

		return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
	}

	@PutMapping("/{questionId}")
	public ResponseEntity<?> updateBlog(@PathVariable Long questionId, @RequestBody Questioner questioner) {

		Questioner updatedContent = questionerService.update(questionId, questioner);

		return new ResponseEntity<>(updatedContent, HttpStatus.OK);
	}

	@DeleteMapping("/{questionId}")
	public ResponseEntity<?> deleteBlog(@PathVariable Long questionId) {
		questionerService.deleteById(questionId);

		return new ResponseEntity<>(questionId, HttpStatus.OK);

	}

	@GetMapping("/{questionId}")
	public ResponseEntity<?> getBlog(@PathVariable Long questionId) {
		Optional<Questioner> fetchedBlog = questionerService.findById(questionId);

		return new ResponseEntity<>(fetchedBlog.get(), HttpStatus.OK);

	}
	
	@GetMapping("/{userId}/list")
	public ResponseEntity<?> getUserQuestionList(@PathVariable Long userId) {
		List<Questioner> questionsList = questionerService.findAllByUserId(userId);
		return new ResponseEntity<>(questionsList, HttpStatus.OK);

	}

	@GetMapping("/list")
	public ResponseEntity<?> getQuestionsList() {
		Iterable<QuestionEntity> questionsList = qnsService.findAll();
		return new ResponseEntity<>(questionsList, HttpStatus.OK);

	}

}
