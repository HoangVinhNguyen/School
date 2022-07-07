package com.school.student.service;

import java.util.List;

import com.school.common.dto.CommentDto;
import com.school.common.dto.StudentAndCourseDto;
import com.school.common.dto.StudentAndTopicDto;
import com.school.common.dto.TopicDto;

public interface StudentService {

	public List<StudentAndCourseDto> findAcademicTranscriptByStudentId(Long idStudent);
	public List<TopicDto> findTopicByCourseIdAndClazzId(Long idCourse, Long idClass);
	public List<TopicDto> findTopicByCourseIdAndClazzIdFull(Long idCourse, Long idClass);
	
	public StudentAndTopicDto saveAnswer(StudentAndTopicDto studentAndTopic);
	public StudentAndTopicDto getAnswer(Long idStudent, Long idTopic);

	public List<CommentDto> findCommentByTopicId(Long idTopic);
	public CommentDto saveComment(CommentDto body);
}
