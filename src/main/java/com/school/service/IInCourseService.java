package com.school.service;

import java.util.List;

import com.school.model.InCourseModel;

public interface IInCourseService {

	public List<InCourseModel> findAll();
	public InCourseModel findOne(long id);
	public InCourseModel findOneByUser(String userEmail);
	public List<InCourseModel> findOneByClass(String courseName);
	public Long save(InCourseModel inCourseModel, String method);
	public Long delete(InCourseModel inCourseModel);
	public int getTotalItem();
}
