package com.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.common.entity.Comment;
import com.school.common.entity.Topic;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	@Query("SELECT cm FROM Comment cm WHERE cm.isDeleted = FALSE AND cm.topic = :topic")
	public List<Comment> findCommentByTopic(@Param("topic") Topic idTopic);
}
