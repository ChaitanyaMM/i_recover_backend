package com.irecover.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irecover.entity.QuestionEntity;
import com.irecover.repository.QuestionsReository;
import com.irecover.service.QuestionsService;

@Service
public class QuestionsServiceImpl implements QuestionsService{
	
	@Autowired
	private QuestionsReository qnsRepository;

	@Override
	public Optional<QuestionEntity> findById(Long questionId) {
		return qnsRepository.findById(questionId);
	}

	@Override
	public Iterable<QuestionEntity> findAll() {
		return qnsRepository.findAll();
	}

}
