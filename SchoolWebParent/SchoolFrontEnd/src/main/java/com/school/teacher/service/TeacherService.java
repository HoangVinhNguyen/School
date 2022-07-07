package com.school.teacher.service;

import java.util.List;

import com.school.common.dto.AcademicTranscriptDto;
import com.school.common.dto.AcademicTranscriptStudentPointDto;
import com.school.common.dto.ClazzDto;
import com.school.common.dto.CommentDto;
import com.school.common.dto.CourseDto;
import com.school.common.dto.SavePointBodyReq;
import com.school.common.dto.StudentAndPointDto;
import com.school.common.dto.StudentAndTopicDto;
import com.school.common.dto.TopicDto;
import com.school.common.dto.UserDto;

public interface TeacherService {

	//public User findUserByEmail(String email);
	//public List<Clazz> findClassesOfIDTeacher(Long id);
	
	public UserDto findUserById(Long id);
	
	public List<ClazzDto> findClassesOfEmailTeacher(String email);
	public ClazzDto findClassById(Long id);
	
	public List<UserDto> findStudentInClass(Long id, String email);
	
	public List<CourseDto> findCourses(String email);
	
	public List<StudentAndPointDto> saveStudentAcademicTranscript(SavePointBodyReq savePointBodyReq);
	public AcademicTranscriptDto saveStudentPointAcademicTranscript(AcademicTranscriptStudentPointDto academicTranscriptDto);
	
	public String getNamePrinciple();
	
	public List<TopicDto> saveTopic(TopicDto testReqBody);
	public List<TopicDto> getTopicByTeacherId(Long teacherId);
	public TopicDto getTopicById(Long topicId);
	public StudentAndTopicDto getAnswer(Long idStudent, Long idTopic);
	public AcademicTranscriptDto getPointExisting(Long idStudent, Long idTopic);
	
	public List<CommentDto> findCommentByTopicId(Long idTopic);
	public CommentDto saveComment(CommentDto body);
}
