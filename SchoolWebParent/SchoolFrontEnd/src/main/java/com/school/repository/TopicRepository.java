package com.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.common.entity.Clazz;
import com.school.common.entity.Course;
import com.school.common.entity.Topic;
import com.school.common.entity.User;

public interface TopicRepository extends JpaRepository<Topic, Long> {

	@Query("SELECT topic FROM Topic topic WHERE topic.isDeleted = FALSE AND topic.id = :id")
	public Topic findTopicById(@Param("id") Long id);
	
	@Query("SELECT topic FROM Topic topic WHERE topic.isDeleted = FALSE AND topic.code = :code")
	public Topic findTopicByCode(@Param("code") String code);
	
	@Query("SELECT topic FROM Topic topic WHERE topic.isDeleted = FALSE AND topic.teacher = :teacher")
	public List<Topic> findTopicByTeacherId(@Param("teacher") User teacher);

	@Query("SELECT NEW Topic(topic.teacher, topic.course, topic.clazz) FROM Topic topic "
			+ "WHERE topic.isDeleted = FALSE AND topic.course = :course AND topic.clazz = :class")
	public List<Topic> findTopicByCourseIdAndClazzId(
			@Param("course") Course course,
			@Param("class") Clazz clazz);

	@Query("SELECT topic FROM Topic topic "
			+ "WHERE topic.isDeleted = FALSE AND topic.course = :course AND topic.clazz = :class")
	public List<Topic> findTopicByCourseIdAndClazzIdFull(
			@Param("course") Course course,
			@Param("class") Clazz clazz);
}
