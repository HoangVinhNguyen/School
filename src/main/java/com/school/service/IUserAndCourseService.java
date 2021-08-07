package com.school.service;

import java.util.List;

import com.school.model.CourseModel;
import com.school.model.UserAndCourseModel;
import com.school.model.UserModel;

public interface IUserAndCourseService {

	public List<UserAndCourseModel> findAll();
	public UserAndCourseModel findOne(UserModel user, CourseModel course);
	public List<UserAndCourseModel> findOneByUser(String userEmail);
	public List<UserAndCourseModel> findOneByCourseName(String name);
	public Long save(UserAndCourseModel userAndCourseModel, String method);
	public Long delete(UserAndCourseModel userAndCourseModel);
	public int getTotalItem();
}
