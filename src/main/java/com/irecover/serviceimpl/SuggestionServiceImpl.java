package com.irecover.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irecover.entity.Suggestion;
import com.irecover.entity.Type;
import com.irecover.repository.SuggestionRepository;
import com.irecover.service.SuggestionService;

@Service
public class SuggestionServiceImpl implements SuggestionService {

	@Autowired
	private SuggestionRepository suggestionRepository;
	
//	@Autowired
//	private RecomendationRepository  recomendationRepository;

	@Override
	public Suggestion create(Suggestion suggestion) {
		return suggestionRepository.save(suggestion);
	}

	@Override
	public Suggestion update(Long suggestionId, Suggestion suggestion) {
		Suggestion updatedContent = suggestion;
		updatedContent.setId(suggestionId);
		return suggestionRepository.save(updatedContent);

	}

	@Override
	public void deleteById(Long suggestionId) {
		suggestionRepository.deleteById(suggestionId);

	}

	@Override
	public Optional<Suggestion> findById(Long suggestionId) {
		return suggestionRepository.findById(suggestionId);

	}

	@Override
	public Iterable<Suggestion> findAll() {
		return suggestionRepository.findAll();

	}

	@Override
	public Suggestion findByType(Type suggestionType) {
		return suggestionRepository.findByType(suggestionType);

	}

	@Override
	public Object getRecomendation() {
		return null;
		//return recomendationRepository.getRecomendation();
	}

}
