package com.school.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.school.DAO.IInClassroomDAO;
import com.school.model.InClassroomModel;
import com.school.paging.Pageble;
import com.school.service.IInClassroomService;

public class InClassroomService implements IInClassroomService{

	@Inject
	private IInClassroomDAO inClassroomDAO;
	
	@Override
	public List<InClassroomModel> findAll(Pageble pageble) {
		return inClassroomDAO.findAll(pageble);
	}

	@Override
	public List<InClassroomModel> findAll() {
		return inClassroomDAO.findAll();
	}

	@Override
	public InClassroomModel findOne(long id) {
		return inClassroomDAO.findOne(id);
	}

	@Override
	public InClassroomModel findOneByUser(String userEmail) {
		return inClassroomDAO.findOneByUser(userEmail);
	}

	@Override
	public List<InClassroomModel> findOneByClass(String className) {
		return inClassroomDAO.findOneByClass(className);
	}

	@Override
	public Long save(InClassroomModel inClassroomModel) {
		return inClassroomDAO.save(inClassroomModel);
	}

	@Override
	public Long delete(InClassroomModel inClassroomModel) {
		return inClassroomDAO.delete(inClassroomModel);
	}

	@Override
	public int getTotalItem() {
		return inClassroomDAO.getTotalItem();
	}

}
