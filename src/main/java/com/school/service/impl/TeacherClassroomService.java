package com.school.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.school.DAO.IPointDAO;
import com.school.constant.SystemConstant;
import com.school.entity.PointEntity;
import com.school.model.PointModel;
import com.school.service.ITeacherClassroomService;

@Service
public class TeacherClassroomService implements ITeacherClassroomService {

	@Autowired
	private IPointDAO pointDAO;

	@Override
	public List<PointModel> findAll() {
		List<PointModel> pointModels = new ArrayList<PointModel>();
		List<PointEntity> pointEntities = pointDAO.findAll();
		Iterator<PointEntity> itr = pointEntities.iterator();
		while(itr.hasNext()) {
			PointModel model = new PointModel();
			model.loadFromEntity(itr.next());
			pointModels.add(model);
		}
		return pointModels;
	}

	@Override
	public PointModel findOne(long id) {
		PointModel model = new PointModel();
		model.loadFromEntity(pointDAO.findOne(id));
		return model;
	}

	@Override
	public PointModel findOneByClassroom(String classroomId) {
		PointModel model = new PointModel();
		model.loadFromEntity(pointDAO.findOneByClassroom(classroomId));
		return model;
	}

	@Override
	public List<PointModel> findAllByTeacherEmail(String userEmail) {
		List<PointModel> pointModels = new ArrayList<PointModel>();
		List<PointEntity> pointEntities = pointDAO.findAllByTeacherEmail(userEmail);
		Iterator<PointEntity> itr = pointEntities.iterator();
		while(itr.hasNext()) {
			PointModel model = new PointModel();
			model.loadFromEntity(itr.next());
			pointModels.add(model);
		}
		return pointModels;
	}
	
	@Override
	public List<PointModel> findAllByStudentEmail(String userEmail) {
		List<PointModel> pointModels = new ArrayList<PointModel>();
		List<PointEntity> pointEntities = pointDAO.findAllByStudentEmail(userEmail);
		Iterator<PointEntity> itr = pointEntities.iterator();
		while(itr.hasNext()) {
			PointModel model = new PointModel();
			model.loadFromEntity(itr.next());
			pointModels.add(model);
		}
		return pointModels;
	}

	@Override
	public Long save(PointModel model, String method) {
		model = getModifiedField(model, method);
		PointEntity pointEntity = new PointEntity();
		pointEntity.loadFromDTO(model);
		return pointDAO.save(pointEntity);
	}

	@Override
	public Long delete(PointModel model) {
		PointEntity pointEntity = new PointEntity();
		pointEntity.loadFromDTO(model);
		return pointDAO.delete(pointEntity);
	}

	@Override
	public int getTotalItem() {
		return pointDAO.getTotalItem();
	}

	@Override
	public Long savePoint(PointModel model) {
		PointEntity pointEntity = new PointEntity();
		pointEntity.loadFromDTO(model);
		return pointDAO.savePoint(pointEntity);
	}

	@Override
	public List<PointModel> findAllByClassroom(String className) {
		List<PointModel> pointModels = new ArrayList<PointModel>();
		List<PointEntity> pointEntities = pointDAO.findAllByClassroom(className);
		Iterator<PointEntity> itr = pointEntities.iterator();
		while(itr.hasNext()) {
			PointModel model = new PointModel();
			model.loadFromEntity(itr.next());
			pointModels.add(model);
		}
		return pointModels;
	}
	
	private PointModel getModifiedField(PointModel model, String method) {
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
