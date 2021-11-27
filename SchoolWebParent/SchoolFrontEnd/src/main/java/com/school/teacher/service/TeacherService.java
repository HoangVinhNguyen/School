package com.school.teacher.service;

import java.util.List;

import com.school.common.dto.AcademicTranscriptDto;
import com.school.common.dto.ClazzDto;
import com.school.common.dto.CourseDto;
import com.school.common.dto.SavePointBodyReq;
import com.school.common.dto.UserDto;

public interface TeacherService {

	//public User findUserByEmail(String email);
	//public List<Clazz> findClassesOfIDTeacher(Long id);
	
	public UserDto findUserById(Long id);
	
	public List<ClazzDto> findClassesOfEmailTeacher(String email);
	
	public List<UserDto> findStudentInClass(Long id, String email);
	
	public List<CourseDto> findCourses(String email);
	
	public List<AcademicTranscriptDto> saveStudentAcademicTranscript(SavePointBodyReq savePointBodyReq);
	
	public String getNamePrinciple();
}
