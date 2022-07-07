package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.common.entity.StudentAndTopic;
import com.school.common.entity.Topic;
import com.school.common.entity.User;

public interface StudentAndTopicRepository extends JpaRepository<StudentAndTopic, Long> {

	@Query("SELECT answer FROM StudentAndTopic answer WHERE answer.student = :student AND answer.topic = :topic")
	public StudentAndTopic findAnswerByStudentAndTopic(
			@Param("student") User student,
			@Param("topic") Topic topic
			);
}
