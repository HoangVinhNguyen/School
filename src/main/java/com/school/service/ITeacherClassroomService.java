package com.school.service;

import java.util.List;

import com.school.model.TeacherClassroomModel;

public interface ITeacherClassroomService {

	public List<TeacherClassroomModel> findAll();
	public TeacherClassroomModel findOne(long id);
	public TeacherClassroomModel findOneByClassroom(String classroomId);
	public List<TeacherClassroomModel> findAllByClassroom(String className);
	public List<TeacherClassroomModel> findAllByTeacherEmail(String userEmail);
	public List<TeacherClassroomModel> findAllByStudentEmail(String userEmail);
	public Long save(TeacherClassroomModel teacherClassroomModel);
	public Long savePoint(TeacherClassroomModel teacherClassroomModel);
	public Long delete(TeacherClassroomModel teacherClassroomModel);
	public int getTotalItem();
}
