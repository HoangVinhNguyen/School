package com.school.teacher.service.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
import com.school.common.entity.Clazz;
import com.school.common.entity.Comment;
import com.school.common.entity.Course;
import com.school.common.entity.Topic;
import com.school.common.entity.User;
import com.school.repository.ClazzRepository;
import com.school.repository.CommentRepository;
import com.school.repository.StudentAndTopicRepository;
import com.school.repository.TopicRepository;
import com.school.service.UserService;
import com.school.teacher.repository.TeacherRepository;
import com.school.teacher.service.AcademicTranscriptService;
import com.school.teacher.service.TeacherService;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	private TeacherRepository repo;
	
	@Autowired
	private AcademicTranscriptService academicTranscriptService;
	
	@Autowired
	private TopicRepository repoTest;

	@Autowired
	private StudentAndTopicRepository repoStudentAndTopic;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ClazzRepository repoClazz;
	
	@Autowired
	private CommentRepository repoComment;

//	@Override
//	public User findUserByEmail(String email) {
//		Optional<User> op = Optional.ofNullable(repo.findTeacherByEmail(email));
//		return op.orElseGet(null);
//	}

//	@Override
//	public List<Clazz> findClassesOfIDTeacher(Long id) {
//		Optional<List<Clazz>> op = Optional.ofNullable(repo.findClassesOfIDTeacher(id));
//		return op.orElseGet(null);
//	}
	
	@Override
	public UserDto findUserById(Long id) {
		Optional<User> op = Optional.ofNullable(repo.findUserById(id));
		if (op.isPresent()) {
			return new UserDto(op.get());
		}
		return null;
	}
	
	@Override
	public List<ClazzDto> findClassesOfEmailTeacher(String email) {
		Optional<List<Clazz>> op = Optional.ofNullable(repo.findClassesOfEmailTeacher(email));
		if (op.isPresent()) {
			List<Clazz> clazzes = op.get();
			List<ClazzDto> clazzesDto = clazzes.stream().map(ClazzDto::new).collect(Collectors.toList());
			return clazzesDto;
		}
		return null;
	}

	
	@Override
	public ClazzDto findClassById(Long id) {
		Optional<Clazz> op = Optional.ofNullable(repoClazz.findClazzById(id));
		if (op.isPresent()) {
			Clazz clazzes = op.get();
			ClazzDto clazzesDto = new ClazzDto(clazzes);
			return clazzesDto;
		}
		return null;
	}

	@Override
	public List<UserDto> findStudentInClass(Long id, String email) {
		Optional<List<User>> op = Optional.ofNullable(repo.findStudentInClass(id, email));
		if (op.isPresent()) {
			List<User> users = op.get();
			List<UserDto> usersDto = users.stream().map(UserDto::new).collect(Collectors.toList());
			return usersDto;
		}
		return null;
	}

	@Override
	public List<CourseDto> findCourses(String email) {
		Optional<List<Course>> op = Optional.ofNullable(repo.findCourses(email));
		if (op.isPresent()) {
			List<Course> courses = op.get();
			List<CourseDto> coursesDto = courses.stream().map(CourseDto::new).collect(Collectors.toList());
			return coursesDto;
		}
		return null;
	}

	@Override
	public String getNamePrinciple() {
		Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
		if (auth.isPresent()) {
			return auth.get().getName();
		}
		return null;
	}

	@Override
	public List<StudentAndPointDto> saveStudentAcademicTranscript(SavePointBodyReq savePointBodyReq) {
		List<StudentAndPointDto> studentAndPointDtos = new ArrayList<>();
		
		if (savePointBodyReq != null) {
			savePointBodyReq.getListStudents().forEach(student -> {
				if (student.getListPoint().size() > 0) {
					student.getListPoint().forEach(point -> {
						if (point.getId() != null) {
							AcademicTranscriptDto academicTranscriptDto = new AcademicTranscriptDto(
									point.getId(),
									point.getPoint());
							academicTranscriptService.saveModifyPointAcademicById(academicTranscriptDto);
						}
					});
				}
			});
		}
		return studentAndPointDtos;
	}
	
	@Override
	public AcademicTranscriptDto saveStudentPointAcademicTranscript(AcademicTranscriptStudentPointDto academicTranscriptDto) {
		AcademicTranscriptDto checkAcdemic = getPointExisting(academicTranscriptDto.getStudent().getId(),
				academicTranscriptDto.getTopic().getId());
		if (checkAcdemic != null && checkAcdemic.getId() != null) {
			return checkAcdemic;
		}
		return academicTranscriptService.saveAcademicStudent(academicTranscriptDto);
	}

	@Override
	public List<TopicDto> saveTopic(TopicDto testReqBody) {
		Topic result = null;
		List<TopicDto> listTestDto = new ArrayList<>();
		List<Topic> listTest= new ArrayList<>();
		LocalDateTime dateNow = LocalDateTime.now();
		Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
		if (testReqBody != null) {
			if (testReqBody.getId() == null) {
				StringBuilder adminControl = new StringBuilder(auth.get().getName());
				Topic entity = new Topic(testReqBody);
				entity.setCreatedBy(adminControl.toString());
				entity.setCreatedDate(dateNow);
				result = repoTest.save(entity);
			} else {
				StringBuilder adminControl = new StringBuilder(auth.get().getName());
				Topic entity = new Topic(testReqBody);
				entity.setModifiedBy(adminControl.toString());
				entity.setModifiedDate(dateNow);
				result = repoTest.save(entity);
			}
		}
		if (result != null) {
			listTest = repoTest.findTopicByTeacherId(result.getTeacher());
		}
		listTestDto = listTest.stream().map(TopicDto::new).collect(Collectors.toList());
		return listTestDto;
	}

	@Override
	public List<TopicDto> getTopicByTeacherId(Long teacherId) {
		List<TopicDto> listTestDto = new ArrayList<>();
		Optional<User> opTeacher = Optional.ofNullable(repo.findUserById(teacherId));
		if (opTeacher.isPresent()) {
			List<Topic> listTest= new ArrayList<>();
			User teacher = opTeacher.get();
			listTest = repoTest.findTopicByTeacherId(teacher);
			listTestDto = listTest.stream().map(TopicDto::new).collect(Collectors.toList());
		}
		return listTestDto;
	}

	@Override
	public TopicDto getTopicById(Long topicId) {
		TopicDto topicDto = null;
		Topic topic = null;
		topic = repoTest.findTopicById(topicId);
		topicDto = new TopicDto(topic);
		return topicDto;
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
	public AcademicTranscriptDto getPointExisting(Long idStudent, Long idTopic) {
		AcademicTranscriptDto result = new AcademicTranscriptDto();
		UserDto student = new UserDto(userService.findUserById(idStudent));
		TopicDto topic = new TopicDto(repoTest.findTopicById(idTopic));
		if (student != null && topic != null) {
			result = academicTranscriptService.findPointExisting(student, topic);
			return result;
		}
		return result;
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
