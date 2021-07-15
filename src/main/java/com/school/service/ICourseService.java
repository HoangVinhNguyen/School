package com.school.service;

import java.util.List;

import com.school.model.CourseModel;

public interface ICourseService {

	public List<CourseModel> findAll();
	public CourseModel findOne(long id);
	public CourseModel findOneByCode(String code);
	public CourseModel findOneByName(String name);
	public Long save(CourseModel courseModel);
	public Long delete(CourseModel courseModel);
	public int getTotalItem();
}
