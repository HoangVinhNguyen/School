package com.school.teacher.service.Impl;

import java.awt.desktop.SystemSleepEvent;
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

import com.school.common.dto.AcademicBodyReq;
import com.school.common.dto.AcademicTranscriptDto;
import com.school.common.dto.ClazzDto;
import com.school.common.dto.CourseDto;
import com.school.common.dto.SavePointBodyReq;
import com.school.common.dto.UserDto;
import com.school.common.entity.AcademicTranscript;
import com.school.common.entity.Clazz;
import com.school.common.entity.Course;
import com.school.common.entity.User;
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
	public List<AcademicTranscriptDto> saveStudentAcademicTranscript(SavePointBodyReq savePointBodyReq) {
		List<AcademicTranscriptDto> academicTranscriptDtos = new ArrayList<>();
		if (savePointBodyReq != null) {
			savePointBodyReq.getListStudents().forEach(student -> {
				AcademicTranscriptDto academicTranscriptDto = new AcademicTranscriptDto(
						savePointBodyReq.getTeacher(),
						student,
						savePointBodyReq.getClazz(),
						savePointBodyReq.getCourse(),
						student.getPoint());
				academicTranscriptDtos.add(new AcademicTranscriptDto(academicTranscriptService.save(academicTranscriptDto)));
			});
		}
		return academicTranscriptDtos;
	}
}
