package com.irecover.service;

import java.util.List;
import java.util.Optional;

import com.irecover.entity.User;
import com.irecover.entity.UserBlog;

public interface UserBlogService {

	UserBlog create(UserBlog userBLog,Long userId);

	UserBlog update(Long blogId, UserBlog userBLog);

	void deleteById(Long blogId);

	Optional<UserBlog> findById(Long blogId);

	Iterable<UserBlog> findAll();

	UserBlog findByUserId(User userId);

	List<UserBlog> finByHashTag(String hashTag);


}
