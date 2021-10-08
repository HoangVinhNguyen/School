package com.school.admin.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.school.admin.exception.EntityNotFoundException;
import com.school.common.entity.Course;

public interface CourseService {

	public static int COURSE_PER_PAGE = 5;

	public List<Course> listAll();
	
	public Page<Course> listByPage(int pageNum, String sortField, String sortDir, String keyword);

	public Course save(Course Course);

	public boolean isNameUnique(Long id, String name);
	
	public boolean isCodeUnique(Long id, String code);
	
	public Course get(Long id) throws EntityNotFoundException;

	public void deleteCourse(Long id) throws EntityNotFoundException;
}
