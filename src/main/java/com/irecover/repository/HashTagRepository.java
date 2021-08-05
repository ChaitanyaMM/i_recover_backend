package com.irecover.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.irecover.entity.HashTag;
import com.irecover.entity.UserBlog;

@Repository
public interface HashTagRepository extends CrudRepository<HashTag, Long> {

	List<HashTag> findByHashtag(String hashTag);

}
