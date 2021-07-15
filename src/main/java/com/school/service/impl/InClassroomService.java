package com.school.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.school.DAO.IInClassroomDAO;
import com.school.entity.InClassroomEntity;
import com.school.model.InClassroomModel;
import com.school.service.IInClassroomService;

public class InClassroomService implements IInClassroomService {

	@Autowired
	private IInClassroomDAO inClassroomDAO;

	@Override
	public List<InClassroomModel> findAll() {
		List<InClassroomModel> inClassroomModels = new ArrayList<InClassroomModel>();
		List<InClassroomEntity> inClassroomEntities = inClassroomDAO.findAll();
		Iterator<InClassroomEntity> itr = inClassroomEntities.iterator();
		while(itr.hasNext()) {
			InClassroomModel inClassroomModel = new InClassroomModel();
			inClassroomModel.loadFromEntity(itr.next());
			inClassroomModels.add(inClassroomModel);
		}
		return inClassroomModels;
	}

	@Override
	public InClassroomModel findOne(long id) {
		InClassroomModel inClassroomModel = new InClassroomModel();
		inClassroomModel.loadFromEntity(inClassroomDAO.findOne(id));
		return inClassroomModel;
	}

	@Override
	public InClassroomModel findOneByUser(String userEmail) {
		InClassroomModel inClassroomModel = new InClassroomModel();
		inClassroomModel.loadFromEntity(inClassroomDAO.findOneByUser(userEmail));
		return inClassroomModel;
	}

	@Override
	public List<InClassroomModel> findOneByClass(String className) {
		List<InClassroomModel> inClassroomModels = new ArrayList<InClassroomModel>();
		List<InClassroomEntity> inClassroomEntities = inClassroomDAO.findOneByClass(className);
		Iterator<InClassroomEntity> itr = inClassroomEntities.iterator();
		while(itr.hasNext()) {
			InClassroomModel inClassroomModel = new InClassroomModel();
			inClassroomModel.loadFromEntity(itr.next());
			inClassroomModels.add(inClassroomModel);
		}
		return inClassroomModels;
	}

	@Override
	public Long save(InClassroomModel inClassroomModel) {
		InClassroomEntity classroomEntity = new InClassroomEntity();
		classroomEntity.loadFromDTO(inClassroomModel);
		return inClassroomDAO.save(classroomEntity);
	}

	@Override
	public Long delete(InClassroomModel inClassroomModel) {
		InClassroomEntity classroomEntity = new InClassroomEntity();
		classroomEntity.loadFromDTO(inClassroomModel);
		return inClassroomDAO.delete(classroomEntity);
	}

	@Override
	public int getTotalItem() {
		return inClassroomDAO.getTotalItem();
	}

}
