package com.irecover.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.irecover.entity.Suggestion;
import com.irecover.entity.Type;

 
@Repository
public interface SuggestionRepository extends CrudRepository<Suggestion, Long>{

	Suggestion findByType(Type suggestionType);

}
