package com.school.service;

import java.util.List;

import com.school.model.PointModel;

public interface ITeacherClassroomService {

	public List<PointModel> findAll();
	public PointModel findOne(long id);
	public PointModel findOneByClassroom(String classroomId);
	public List<PointModel> findAllByClassroom(String className);
	public List<PointModel> findAllByTeacherEmail(String userEmail);
	public List<PointModel> findAllByStudentEmail(String userEmail);
	public Long save(PointModel pointModel, String method);
	public Long savePoint(PointModel pointModel);
	public Long delete(PointModel pointModel);
	public int getTotalItem();
}
