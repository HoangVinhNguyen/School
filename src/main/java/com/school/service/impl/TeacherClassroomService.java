package com.school.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.school.DAO.ITeacherClassroomDAO;
import com.school.constant.SystemConstant;
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
			TeacherClassroomModel model = new TeacherClassroomModel();
			model.loadFromEntity(itr.next());
			teacherClassroomModels.add(model);
		}
		return teacherClassroomModels;
	}

	@Override
	public TeacherClassroomModel findOne(long id) {
		TeacherClassroomModel model = new TeacherClassroomModel();
		model.loadFromEntity(teacherClassroomDAO.findOne(id));
		return model;
	}

	@Override
	public TeacherClassroomModel findOneByClassroom(String classroomId) {
		TeacherClassroomModel model = new TeacherClassroomModel();
		model.loadFromEntity(teacherClassroomDAO.findOneByClassroom(classroomId));
		return model;
	}

	@Override
	public List<TeacherClassroomModel> findAllByTeacherEmail(String userEmail) {
		List<TeacherClassroomModel> teacherClassroomModels = new ArrayList<TeacherClassroomModel>();
		List<TeacherClassroomEntity> teacherClassroomEntities = teacherClassroomDAO.findAllByTeacherEmail(userEmail);
		Iterator<TeacherClassroomEntity> itr = teacherClassroomEntities.iterator();
		while(itr.hasNext()) {
			TeacherClassroomModel model = new TeacherClassroomModel();
			model.loadFromEntity(itr.next());
			teacherClassroomModels.add(model);
		}
		return teacherClassroomModels;
	}
	
	@Override
	public List<TeacherClassroomModel> findAllByStudentEmail(String userEmail) {
		List<TeacherClassroomModel> teacherClassroomModels = new ArrayList<TeacherClassroomModel>();
		List<TeacherClassroomEntity> teacherClassroomEntities = teacherClassroomDAO.findAllByStudentEmail(userEmail);
		Iterator<TeacherClassroomEntity> itr = teacherClassroomEntities.iterator();
		while(itr.hasNext()) {
			TeacherClassroomModel model = new TeacherClassroomModel();
			model.loadFromEntity(itr.next());
			teacherClassroomModels.add(model);
		}
		return teacherClassroomModels;
	}

	@Override
	public Long save(TeacherClassroomModel model, String method) {
		model = getModifiedField(model, method);
		TeacherClassroomEntity teacherClassroomEntity = new TeacherClassroomEntity();
		teacherClassroomEntity.loadFromDTO(model);
		return teacherClassroomDAO.save(teacherClassroomEntity);
	}

	@Override
	public Long delete(TeacherClassroomModel model) {
		TeacherClassroomEntity teacherClassroomEntity = new TeacherClassroomEntity();
		teacherClassroomEntity.loadFromDTO(model);
		return teacherClassroomDAO.delete(teacherClassroomEntity);
	}

	@Override
	public int getTotalItem() {
		return teacherClassroomDAO.getTotalItem();
	}

	@Override
	public Long savePoint(TeacherClassroomModel model) {
		TeacherClassroomEntity teacherClassroomEntity = new TeacherClassroomEntity();
		teacherClassroomEntity.loadFromDTO(model);
		return teacherClassroomDAO.savePoint(teacherClassroomEntity);
	}

	@Override
	public List<TeacherClassroomModel> findAllByClassroom(String className) {
		List<TeacherClassroomModel> teacherClassroomModels = new ArrayList<TeacherClassroomModel>();
		List<TeacherClassroomEntity> teacherClassroomEntities = teacherClassroomDAO.findAllByClassroom(className);
		Iterator<TeacherClassroomEntity> itr = teacherClassroomEntities.iterator();
		while(itr.hasNext()) {
			TeacherClassroomModel model = new TeacherClassroomModel();
			model.loadFromEntity(itr.next());
			teacherClassroomModels.add(model);
		}
		return teacherClassroomModels;
	}
	
	private TeacherClassroomModel getModifiedField(TeacherClassroomModel model, String method) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		switch (method) {
		case SystemConstant.INSERT:
			model.setModifiedDate(timestamp);
			model.setCreatedBy(authentication.getName());
			model.setCreatedDate(timestamp);
			break;
		case SystemConstant.MODIFY:
			model.setModifiedDate(timestamp);
			model.setModifiedBy(authentication.getName());
			break;
		}
		return model;
	}
	
}
