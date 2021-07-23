package com.school.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.school.DAO.IClassroomDAO;
import com.school.constant.SystemConstant;
import com.school.entity.ClassroomEntity;
import com.school.model.ClassroomModel;
import com.school.service.IClassroomService;

@Service
public class ClassroomService implements IClassroomService {

	@Autowired
	private IClassroomDAO classroomDAO;
	
	@Override
	public ClassroomModel findOne(long id) {
		ClassroomModel model = new ClassroomModel();
		model.loadFromEntity(classroomDAO.findOne(id));
		return model;
	}

	@Override
	public ClassroomModel findOneByCode(String code) {
		ClassroomModel model = new ClassroomModel();
		model.loadFromEntity(classroomDAO.findOneByCode(code));
		return model;
	}
	
	@Override
	public ClassroomModel findOneByName(String name) {
		ClassroomModel model = new ClassroomModel();
		model.loadFromEntity(classroomDAO.findOneByName(name));
		return model;
	}

	@Override
	public int getTotalItem() {
		return classroomDAO.getTotalItem();
	}

	@Override
	public Long save(ClassroomModel model, String method) {
		model = getModifiedField(model, method);
		ClassroomEntity classroomEntity = new ClassroomEntity();
		classroomEntity.loadFromDTO(model);
		Long result = classroomDAO.save(classroomEntity);
		return result;
	}

	@Override
	public List<ClassroomModel> findAll() {
		List<ClassroomModel> classroomModels = new ArrayList<ClassroomModel>();
		List<ClassroomEntity> classroomEntities = classroomDAO.findAll();
		Iterator<ClassroomEntity> itr = classroomEntities.iterator();
		while(itr.hasNext()) {
			ClassroomModel model = new ClassroomModel();
			model.loadFromEntity(itr.next());
			classroomModels.add(model);
		}
		return classroomModels;
	}

	@Override
	public Long delete(ClassroomModel model) {
		ClassroomEntity classroomEntity = new ClassroomEntity();
		classroomEntity.loadFromDTO(model);
		return classroomDAO.delete(classroomEntity);
	}
	
	private ClassroomModel getModifiedField(ClassroomModel model, String method) {
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
