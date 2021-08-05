package com.irecover.service;

import java.util.List;

import com.irecover.entity.HashTag;
import com.irecover.entity.UserBlog;

public interface HashTagService {
	
	public List<HashTag> findByHashTag(String hashTag);

}
