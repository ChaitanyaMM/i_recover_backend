package com.irecover.repository;
 
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.irecover.entity.Questioner;
import com.irecover.entity.User;


@Repository
public interface QuestionerRepository extends CrudRepository<Questioner, Long>{

	List<Questioner> findByUser(User userId);

}
