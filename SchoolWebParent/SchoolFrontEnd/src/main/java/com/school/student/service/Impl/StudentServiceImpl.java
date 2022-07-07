package com.school.student.service.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.school.common.dto.CommentDto;
import com.school.common.dto.StudentAndCourseDto;
import com.school.common.dto.StudentAndTopicDto;
import com.school.common.dto.TopicDto;
import com.school.common.entity.Clazz;
import com.school.common.entity.Comment;
import com.school.common.entity.Course;
import com.school.common.entity.StudentAndCourse;
import com.school.common.entity.StudentAndTopic;
import com.school.common.entity.Topic;
import com.school.common.entity.User;
import com.school.repository.CommentRepository;
import com.school.repository.StudentAndTopicRepository;
import com.school.repository.TopicRepository;
import com.school.service.UserService;
import com.school.student.repository.StudentRepository;
import com.school.student.service.StudentService;
import com.school.teacher.repository.StudentAndCourseRepository;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository repo;
	
	@Autowired
	private TopicRepository repoTest;
	
	@Autowired
	private StudentAndTopicRepository repoStudentAndTopic;
	
	@Autowired
	private StudentAndCourseRepository repoStudentAndCourse;
	
	@Autowired
	private CommentRepository repoComment;
	
	@Autowired
	private UserService userService;

	@Override
	public List<StudentAndCourseDto> findAcademicTranscriptByStudentId(Long idStudent) {
		User student = userService.findUserById(idStudent);
		Optional<List<StudentAndCourse>> op = Optional.ofNullable(repoStudentAndCourse.findAcademicTranscriptByStudentId(student));
		if (op.isPresent()) {
			List<StudentAndCourse> academicTranscripts = op.get();
			List<StudentAndCourseDto> listStudent = academicTranscripts.stream().map(StudentAndCourseDto::new).collect(Collectors.toList());
			List<StudentAndCourseDto> result = new ArrayList<>();
			result = new ArrayList<>(
					listStudent.stream()
				    .collect(
				        Collectors.toMap(StudentAndCourseDto::getCourse, Function.identity(), (StudentAndCourseDto account1, StudentAndCourseDto account2) -> {
				            account1.getListPoint().addAll(account2.getListPoint());
				            account2.getListPoint().clear();
				            return account1;
				        })
				    ).values());
			return result;
		}
		return null;
	}

	@Override
	public List<TopicDto> findTopicByCourseIdAndClazzId(Long idCourse, Long idClass) {
		
		List<TopicDto> results = new ArrayList<>();
		Optional<List<Topic>> opListTest = Optional.ofNullable(repoTest.findTopicByCourseIdAndClazzId(new Course(idCourse), new Clazz(idClass)));
		if (opListTest.isPresent()) {
			List<Topic> listEntity = opListTest.get();
			results = listEntity.stream().map(TopicDto::new).collect(Collectors.toList());
		}
		return results;
	}

	@Override
	public List<TopicDto> findTopicByCourseIdAndClazzIdFull(Long idCourse, Long idClass) {
		
		List<TopicDto> results = new ArrayList<>();
		Optional<List<Topic>> opListTest = Optional.ofNullable(repoTest.findTopicByCourseIdAndClazzIdFull(new Course(idCourse), new Clazz(idClass)));
		if (opListTest.isPresent()) {
			List<Topic> listEntity = opListTest.get();
			results = listEntity.stream().map(TopicDto::new).collect(Collectors.toList());
		}
		return results;
	}

	@Override
	public StudentAndTopicDto saveAnswer(StudentAndTopicDto studentAndTopic) {
		LocalDateTime dateNow = LocalDateTime.now();
		Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
		StudentAndTopicDto result = null;
		if (studentAndTopic.getId() == null) {
			StringBuilder adminControl = new StringBuilder(auth.get().getName());
			StudentAndTopic entity = new StudentAndTopic(studentAndTopic);
			entity.setCreatedBy(adminControl.toString());
			entity.setCreatedDate(dateNow);
			result = new StudentAndTopicDto(repoStudentAndTopic.save(entity));
		}
		return result;
	}

	@Override
	public StudentAndTopicDto getAnswer(Long idStudent, Long idTopic) {
		User student = userService.findUserById(idStudent);
		Topic topic = repoTest.findTopicById(idTopic);
		if (student != null && topic != null) {
			return new StudentAndTopicDto(repoStudentAndTopic.findAnswerByStudentAndTopic(student, topic));
		}
		return null;
	}

	@Override
	public List<CommentDto> findCommentByTopicId(Long idTopic) {
		Topic checkTopic = repoTest.findTopicById(idTopic);
		List<CommentDto> results = null;
		if (checkTopic != null) {
			List<Comment> listComment = repoComment.findCommentByTopic(checkTopic);
			results = listComment.stream().map(CommentDto::new).collect(Collectors.toList());
		}
		return results;
	}

	@Override
	public CommentDto saveComment(CommentDto body) {
		LocalDateTime dateNow = LocalDateTime.now();
		Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
		CommentDto result = null;
		if (body.getId() == null) {
			StringBuilder adminControl = new StringBuilder(auth.get().getName());
			Comment entity = Comment.convertToAcademicTranscript(body);
			entity.setCreatedBy(adminControl.toString());
			entity.setCreatedDate(dateNow);
			result = new CommentDto(repoComment.save(entity));
		}
		return result;
	}
	
}
