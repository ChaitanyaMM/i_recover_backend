package com.irecover.serviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.transform.ToListResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irecover.entity.HashTag;
import com.irecover.entity.User;
import com.irecover.entity.UserBlog;
import com.irecover.repository.HashTagRepository;
import com.irecover.repository.UserBlogRepository;
import com.irecover.service.UserBlogService;
import com.irecover.service.UserService;

@Service
public class UserBlogServiceImpl implements UserBlogService {

	@Autowired
	private UserBlogRepository userBlogRepository;

	@Autowired
	private HashTagRepository hashTagRepository;

	@Autowired
	private UserService userService;

	@Override
	public UserBlog create(UserBlog userBLog, Long userId) {
		Optional<User> fetchedUser = userService.findById(userId);
		userBLog.setUserId(fetchedUser.get());
		return userBlogRepository.save(userBLog);
	}

	@Override
	public UserBlog update(Long blogId, UserBlog userBLog) {
		UserBlog updateUserBLog = userBLog;
		updateUserBLog.setId(blogId);
		return userBlogRepository.save(updateUserBLog);

	}

	@Override
	public void deleteById(Long blogId) {
		userBlogRepository.deleteById(blogId);

	}

	@Override
	public Optional<UserBlog> findById(Long blogId) {
		return userBlogRepository.findById(blogId);

	}

	@Override
	public Iterable<UserBlog> findAll() {
		return userBlogRepository.findAll();

	}

	@Override
	public UserBlog findByUserId(User userId) {
		return userBlogRepository.findByUserId(userId);

	}

	@Override
	public List<UserBlog> finByHashTag(String hashTag) {
		List<HashTag> fetchedUserBlogIds = hashTagRepository.findByHashtag(hashTag);
		List<Long> userBlogIds = fetchedUserBlogIds.stream().map(id -> id.getUserBlog().getId())
				.collect(Collectors.toCollection(ArrayList::new));

		return userBlogRepository.findByIds(userBlogIds);

	}

}
