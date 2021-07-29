package com.school.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.school.DAO.IInCourseDAO;
import com.school.constant.SystemConstant;
import com.school.entity.InCourseEntity;
import com.school.model.InCourseModel;
import com.school.service.IInCourseService;

@Service
public class InCourseService implements IInCourseService {

	@Autowired
	private IInCourseDAO inCourseDAO;

	@Override
	public List<InCourseModel> findAll() {
		List<InCourseModel> inClassroomModels = new ArrayList<InCourseModel>();
		List<InCourseEntity> inClassroomEntities = inCourseDAO.findAll();
		Iterator<InCourseEntity> itr = inClassroomEntities.iterator();
		while(itr.hasNext()) {
			InCourseModel model = new InCourseModel();
			model.loadFromEntity(itr.next());
			inClassroomModels.add(model);
		}
		return inClassroomModels;
	}

	@Override
	public InCourseModel findOne(long id) {
		InCourseModel model = new InCourseModel();
		model.loadFromEntity(inCourseDAO.findOne(id));
		return model;
	}

	@Override
	public InCourseModel findOneByUser(String userEmail) {
		InCourseModel model = new InCourseModel();
		model.loadFromEntity(inCourseDAO.findOneByUser(userEmail));
		return model;
	}

	@Override
	public List<InCourseModel> findOneByClass(String courseName) {
		List<InCourseModel> inClassroomModels = new ArrayList<InCourseModel>();
		List<InCourseEntity> inClassroomEntities = inCourseDAO.findOneByCourse(courseName);
		Iterator<InCourseEntity> itr = inClassroomEntities.iterator();
		while(itr.hasNext()) {
			InCourseModel model = new InCourseModel();
			model.loadFromEntity(itr.next());
			inClassroomModels.add(model);
		}
		return inClassroomModels;
	}

	@Override
	public Long save(InCourseModel model, String method) {
		model = getModifiedField(model, method);
		InCourseEntity classroomEntity = new InCourseEntity();
		classroomEntity.loadFromDTO(model);
		return inCourseDAO.save(classroomEntity);
	}

	@Override
	public Long delete(InCourseModel model) {
		InCourseEntity classroomEntity = new InCourseEntity();
		classroomEntity.loadFromDTO(model);
		return inCourseDAO.delete(classroomEntity);
	}

	@Override
	public int getTotalItem() {
		return inCourseDAO.getTotalItem();
	}

	private InCourseModel getModifiedField(InCourseModel model, String method) {
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
