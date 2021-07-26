package com.school.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.school.DAO.IInClassroomDAO;
import com.school.constant.SystemConstant;
import com.school.entity.InClassroomEntity;
import com.school.model.ClassroomModel;
import com.school.model.InClassroomModel;
import com.school.service.IInClassroomService;

@Service
public class InClassroomService implements IInClassroomService {

	@Autowired
	private IInClassroomDAO inClassroomDAO;

	@Override
	public List<InClassroomModel> findAll() {
		List<InClassroomModel> inClassroomModels = new ArrayList<InClassroomModel>();
		List<InClassroomEntity> inClassroomEntities = inClassroomDAO.findAll();
		Iterator<InClassroomEntity> itr = inClassroomEntities.iterator();
		while(itr.hasNext()) {
			InClassroomModel model = new InClassroomModel();
			model.loadFromEntity(itr.next());
			inClassroomModels.add(model);
		}
		return inClassroomModels;
	}

	@Override
	public InClassroomModel findOne(long id) {
		InClassroomModel model = new InClassroomModel();
		model.loadFromEntity(inClassroomDAO.findOne(id));
		return model;
	}

	@Override
	public InClassroomModel findOneByUser(String userEmail) {
		InClassroomModel model = new InClassroomModel();
		model.loadFromEntity(inClassroomDAO.findOneByUser(userEmail));
		return model;
	}

	@Override
	public List<InClassroomModel> findOneByClass(String className) {
		List<InClassroomModel> inClassroomModels = new ArrayList<InClassroomModel>();
		List<InClassroomEntity> inClassroomEntities = inClassroomDAO.findOneByClass(className);
		Iterator<InClassroomEntity> itr = inClassroomEntities.iterator();
		while(itr.hasNext()) {
			InClassroomModel model = new InClassroomModel();
			model.loadFromEntity(itr.next());
			inClassroomModels.add(model);
		}
		return inClassroomModels;
	}

	@Override
	public Long save(InClassroomModel model, String method) {
		model = getModifiedField(model, method);
		InClassroomEntity classroomEntity = new InClassroomEntity();
		classroomEntity.loadFromDTO(model);
		return inClassroomDAO.save(classroomEntity);
	}

	@Override
	public Long delete(InClassroomModel model) {
		InClassroomEntity classroomEntity = new InClassroomEntity();
		classroomEntity.loadFromDTO(model);
		return inClassroomDAO.delete(classroomEntity);
	}

	@Override
	public int getTotalItem() {
		return inClassroomDAO.getTotalItem();
	}

	private InClassroomModel getModifiedField(InClassroomModel model, String method) {
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
