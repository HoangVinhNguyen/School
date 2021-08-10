package com.school.service;

import java.util.List;

import com.school.model.ClazzModel;
import com.school.model.CourseModel;
import com.school.model.PointModel;
import com.school.model.UserModel;

public interface IPointService {

	public List<PointModel> findAll();
	public PointModel findOne(long id);
	public PointModel findOneByUserCourseClazz(UserModel teacher, UserModel student, CourseModel course, ClazzModel clazz);
	public List<PointModel> findAllByCourse(CourseModel course);
	public List<PointModel> findAllByClazz(ClazzModel clazz);
	public List<PointModel> findAllByTeacher(UserModel teacher);
	public List<PointModel> findAllByStudent(UserModel student);
	public Long save(PointModel model, String method);
	public Long savePoint(PointModel model, String method);
	public Long delete(PointModel model, String method);
	public int getTotalItem();
}
