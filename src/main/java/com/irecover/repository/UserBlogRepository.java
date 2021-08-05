package com.irecover.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.irecover.entity.User;
import com.irecover.entity.UserBlog;

@Repository
public interface UserBlogRepository extends JpaRepository<UserBlog, Long> {

	UserBlog findByUserId(User userId);

	@Query("SELECT ub FROM UserBlog ub WHERE ub.id IN (:ids)")
	List<UserBlog> findByIds(@Param("ids") List<Long> ids);

}
