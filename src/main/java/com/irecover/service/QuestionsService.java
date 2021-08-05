package com.irecover.service;

import java.util.Optional;

import com.irecover.entity.QuestionEntity;

public interface QuestionsService {
	
	public Optional<QuestionEntity> findById(Long questionId);

	public Iterable<QuestionEntity> findAll();

}
