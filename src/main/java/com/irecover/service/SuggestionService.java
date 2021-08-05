package com.irecover.service;

import java.util.Optional;

import com.irecover.entity.Suggestion;
import com.irecover.entity.Type;

public interface SuggestionService {

	Suggestion create(Suggestion suggestion);

	Suggestion update(Long suggestionId, Suggestion suggestion);

	void deleteById(Long suggestionId);

	Optional<Suggestion> findById(Long suggestionId);

	Iterable<Suggestion> findAll();

	Suggestion findByType(Type suggestionType);

	Object getRecomendation();

}
