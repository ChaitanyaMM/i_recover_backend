package com.irecover.service;

import java.util.List;
import java.util.Optional;

import com.irecover.entity.Questioner;

public interface QuestionerService {


	Questioner create(Questioner questioner, Long userId);

	Questioner update(Long questionId, Questioner questioner);

	void deleteById(Long questionId);

	Optional<Questioner> findById(Long questionId);

	Iterable<Questioner> findAll();

	List<Questioner> findAllByUserId(Long userId);

}
