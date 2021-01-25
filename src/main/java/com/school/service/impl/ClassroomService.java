package com.school.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.school.DAO.IClassroomDAO;
import com.school.model.ClassroomModel;
import com.school.paging.Pageble;
import com.school.service.IClassroomService;

public class ClassroomService implements IClassroomService {

	@Inject
	private IClassroomDAO classroomDAO;
	
	@Override
	public List<ClassroomModel> findAll(Pageble pageble) {
		return classroomDAO.findAll(pageble);
	}

	@Override
	public ClassroomModel findOne(long id) {
		return classroomDAO.findOne(id);
	}

	@Override
	public ClassroomModel findOneByCode(String code) {
		return classroomDAO.findOneByCode(code);
	}
	
	@Override
	public ClassroomModel findOneByName(String name) {
		return classroomDAO.findOneByName(name);
	}

	@Override
	public int getTotalItem() {
		return classroomDAO.getTotalItem();
	}

	@Override
	public Long save(ClassroomModel classroomModel) {
		return classroomDAO.save(classroomModel);
	}

	@Override
	public List<ClassroomModel> findAll() {
		return classroomDAO.findAll();
	}

	@Override
	public Long delete(ClassroomModel classroomModel) {
		return classroomDAO.delete(classroomModel);
	}

}
