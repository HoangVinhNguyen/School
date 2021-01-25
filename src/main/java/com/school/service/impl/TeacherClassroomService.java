package com.school.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.school.DAO.ITeacherClassroomDAO;
import com.school.model.TeacherClassroomModel;
import com.school.paging.Pageble;
import com.school.service.ITeacherClassroomService;

public class TeacherClassroomService implements ITeacherClassroomService{

	@Inject
	private ITeacherClassroomDAO teacherClassroomDAO;
	
	@Override
	public List<TeacherClassroomModel> findAll(Pageble pageble) {
		return teacherClassroomDAO.findAll(pageble);
	}

	@Override
	public List<TeacherClassroomModel> findAll() {
		return teacherClassroomDAO.findAll();
	}

	@Override
	public TeacherClassroomModel findOne(long id) {
		return teacherClassroomDAO.findOne(id);
	}

	@Override
	public TeacherClassroomModel findOneByClassroom(String classroomId) {
		return teacherClassroomDAO.findOneByClassroom(classroomId);
	}

	@Override
	public List<TeacherClassroomModel> findAllByTeacherEmail(String userEmail) {
		return teacherClassroomDAO.findAllByTeacherEmail(userEmail);
	}
	
	@Override
	public List<TeacherClassroomModel> findAllByStudentEmail(String userEmail) {
		return teacherClassroomDAO.findAllByStudentEmail(userEmail);
	}

	@Override
	public Long save(TeacherClassroomModel teacherClassroomModel) {
		return teacherClassroomDAO.save(teacherClassroomModel);
	}

	@Override
	public Long delete(TeacherClassroomModel teacherClassroomModel) {
		return teacherClassroomDAO.delete(teacherClassroomModel);
	}

	@Override
	public int getTotalItem() {
		return teacherClassroomDAO.getTotalItem();
	}

	@Override
	public Long savePoint(TeacherClassroomModel teacherClassroomModel) {
		return teacherClassroomDAO.savePoint(teacherClassroomModel);
	}

	@Override
	public List<TeacherClassroomModel> findAllByClassroom(String className) {
		return teacherClassroomDAO.findAllByClassroom(className);
	}
}
