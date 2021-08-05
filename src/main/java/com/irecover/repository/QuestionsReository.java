package com.irecover.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.irecover.entity.QuestionEntity;

@Repository
public interface QuestionsReository extends CrudRepository<QuestionEntity, Long> {

}
