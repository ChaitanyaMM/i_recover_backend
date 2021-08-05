package com.irecover.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irecover.entity.QuestionEntity;
import com.irecover.entity.Questioner;
import com.irecover.entity.User;
import com.irecover.repository.QuestionerRepository;
import com.irecover.service.QuestionerService;
import com.irecover.service.QuestionsService;
import com.irecover.service.UserService;

@Service
public class QuestionerServiceImpl implements QuestionerService {

	@Autowired
	private QuestionerRepository questionerRepository;

	@Autowired
	private UserService userService;

	@Override
	public Questioner create(Questioner questioner, Long userId) {
		Optional<User> fetchedUser = userService.findById(userId);
		questioner.setUser(fetchedUser.get());
		return questionerRepository.save(questioner);
	}

	@Override
	public Questioner update(Long questionId, Questioner questioner) {
		Questioner updateContent = questioner;
		updateContent.setId(questionId);

		return questionerRepository.save(updateContent);

	}

	@Override
	public void deleteById(Long questionId) {
		questionerRepository.deleteById(questionId);

	}

	@Override
	public Optional<Questioner> findById(Long questionId) {
		return questionerRepository.findById(questionId);

	}

	@Override
	public Iterable<Questioner> findAll() {
		return questionerRepository.findAll();

	}

	@Override
	public List<Questioner> findAllByUserId(Long userId) {
		return questionerRepository.findByUser(userService.findById(userId).get());

	}

}
