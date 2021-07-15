package com.school.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.school.DAO.IClassroomDAO;
import com.school.entity.ClassroomEntity;
import com.school.model.ClassroomModel;
import com.school.service.IClassroomService;

public class ClassroomService implements IClassroomService {

	@Autowired
	private IClassroomDAO classroomDAO;
	
	@Override
	public ClassroomModel findOne(long id) {
		ClassroomModel classroomModel = new ClassroomModel();
		classroomModel.loadFromEntity(classroomDAO.findOne(id));
		return classroomModel;
	}

	@Override
	public ClassroomModel findOneByCode(String code) {
		ClassroomModel classroomModel = new ClassroomModel();
		classroomModel.loadFromEntity(classroomDAO.findOneByCode(code));
		return classroomModel;
	}
	
	@Override
	public ClassroomModel findOneByName(String name) {
		ClassroomModel classroomModel = new ClassroomModel();
		classroomModel.loadFromEntity(classroomDAO.findOneByName(name));
		return classroomModel;
	}

	@Override
	public int getTotalItem() {
		return classroomDAO.getTotalItem();
	}

	@Override
	public Long save(ClassroomModel classroomModel) {
		ClassroomEntity classroomEntity = new ClassroomEntity();
		classroomEntity.loadFromDTO(classroomModel);
		return classroomDAO.save(classroomEntity);
	}

	@Override
	public List<ClassroomModel> findAll() {
		List<ClassroomModel> classroomModels = new ArrayList<ClassroomModel>();
		List<ClassroomEntity> classroomEntities = classroomDAO.findAll();
		Iterator<ClassroomEntity> itr = classroomEntities.iterator();
		while(itr.hasNext()) {
			ClassroomModel classroomModel = new ClassroomModel();
			classroomModel.loadFromEntity(itr.next());
			classroomModels.add(classroomModel);
		}
		return classroomModels;
	}

	@Override
	public Long delete(ClassroomModel classroomModel) {
		ClassroomEntity classroomEntity = new ClassroomEntity();
		classroomEntity.loadFromDTO(classroomModel);
		return classroomDAO.delete(classroomEntity);
	}

}
