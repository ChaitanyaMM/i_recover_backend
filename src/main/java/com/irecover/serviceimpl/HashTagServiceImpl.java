package com.irecover.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irecover.entity.HashTag;
import com.irecover.entity.UserBlog;
import com.irecover.repository.HashTagRepository;
import com.irecover.service.HashTagService;

@Service
public class HashTagServiceImpl implements HashTagService {

	@Autowired
	private HashTagRepository hashTagRepository;

	@Override
	public List<HashTag> findByHashTag(String hashTag) {

		return hashTagRepository.findByHashtag(hashTag);
	}

}
