package com.school.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.DAO.ITeacherClassroomDAO;
import com.school.entity.TeacherClassroomEntity;
import com.school.model.TeacherClassroomModel;
import com.school.service.ITeacherClassroomService;

@Service
public class TeacherClassroomService implements ITeacherClassroomService {

	@Autowired
	private ITeacherClassroomDAO teacherClassroomDAO;

	@Override
	public List<TeacherClassroomModel> findAll() {
		List<TeacherClassroomModel> teacherClassroomModels = new ArrayList<TeacherClassroomModel>();
		List<TeacherClassroomEntity> teacherClassroomEntities = teacherClassroomDAO.findAll();
		Iterator<TeacherClassroomEntity> itr = teacherClassroomEntities.iterator();
		while(itr.hasNext()) {
			TeacherClassroomModel teacherClassroomModel = new TeacherClassroomModel();
			teacherClassroomModel.loadFromEntity(itr.next());
			teacherClassroomModels.add(teacherClassroomModel);
		}
		return teacherClassroomModels;
	}

	@Override
	public TeacherClassroomModel findOne(long id) {
		TeacherClassroomModel teacherClassroomModel = new TeacherClassroomModel();
		teacherClassroomModel.loadFromEntity(teacherClassroomDAO.findOne(id));
		return teacherClassroomModel;
	}

	@Override
	public TeacherClassroomModel findOneByClassroom(String classroomId) {
		TeacherClassroomModel teacherClassroomModel = new TeacherClassroomModel();
		teacherClassroomModel.loadFromEntity(teacherClassroomDAO.findOneByClassroom(classroomId));
		return teacherClassroomModel;
	}

	@Override
	public List<TeacherClassroomModel> findAllByTeacherEmail(String userEmail) {
		List<TeacherClassroomModel> teacherClassroomModels = new ArrayList<TeacherClassroomModel>();
		List<TeacherClassroomEntity> teacherClassroomEntities = teacherClassroomDAO.findAllByTeacherEmail(userEmail);
		Iterator<TeacherClassroomEntity> itr = teacherClassroomEntities.iterator();
		while(itr.hasNext()) {
			TeacherClassroomModel teacherClassroomModel = new TeacherClassroomModel();
			teacherClassroomModel.loadFromEntity(itr.next());
			teacherClassroomModels.add(teacherClassroomModel);
		}
		return teacherClassroomModels;
	}
	
	@Override
	public List<TeacherClassroomModel> findAllByStudentEmail(String userEmail) {
		List<TeacherClassroomModel> teacherClassroomModels = new ArrayList<TeacherClassroomModel>();
		List<TeacherClassroomEntity> teacherClassroomEntities = teacherClassroomDAO.findAllByStudentEmail(userEmail);
		Iterator<TeacherClassroomEntity> itr = teacherClassroomEntities.iterator();
		while(itr.hasNext()) {
			TeacherClassroomModel teacherClassroomModel = new TeacherClassroomModel();
			teacherClassroomModel.loadFromEntity(itr.next());
			teacherClassroomModels.add(teacherClassroomModel);
		}
		return teacherClassroomModels;
	}

	@Override
	public Long save(TeacherClassroomModel teacherClassroomModel) {
		TeacherClassroomEntity teacherClassroomEntity = new TeacherClassroomEntity();
		teacherClassroomEntity.loadFromDTO(teacherClassroomModel);
		return teacherClassroomDAO.save(teacherClassroomEntity);
	}

	@Override
	public Long delete(TeacherClassroomModel teacherClassroomModel) {
		TeacherClassroomEntity teacherClassroomEntity = new TeacherClassroomEntity();
		teacherClassroomEntity.loadFromDTO(teacherClassroomModel);
		return teacherClassroomDAO.delete(teacherClassroomEntity);
	}

	@Override
	public int getTotalItem() {
		return teacherClassroomDAO.getTotalItem();
	}

	@Override
	public Long savePoint(TeacherClassroomModel teacherClassroomModel) {
		TeacherClassroomEntity teacherClassroomEntity = new TeacherClassroomEntity();
		teacherClassroomEntity.loadFromDTO(teacherClassroomModel);
		return teacherClassroomDAO.savePoint(teacherClassroomEntity);
	}

	@Override
	public List<TeacherClassroomModel> findAllByClassroom(String className) {
		List<TeacherClassroomModel> teacherClassroomModels = new ArrayList<TeacherClassroomModel>();
		List<TeacherClassroomEntity> teacherClassroomEntities = teacherClassroomDAO.findAllByClassroom(className);
		Iterator<TeacherClassroomEntity> itr = teacherClassroomEntities.iterator();
		while(itr.hasNext()) {
			TeacherClassroomModel teacherClassroomModel = new TeacherClassroomModel();
			teacherClassroomModel.loadFromEntity(itr.next());
			teacherClassroomModels.add(teacherClassroomModel);
		}
		return teacherClassroomModels;
	}
}
