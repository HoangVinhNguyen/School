package com.school.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.school.DAO.ICourseDAO;
import com.school.model.CourseModel;
import com.school.paging.Pageble;
import com.school.service.ICourseService;

public class CourseService implements ICourseService {

	@Inject
	private ICourseDAO courseDAO;
	
	@Override
	public List<CourseModel> findAll(Pageble pageble) {
		return courseDAO.findAll(pageble);
	}

	@Override
	public CourseModel findOne(long id) {
		return courseDAO.findOne(id);
	}

	@Override
	public CourseModel findOneByCode(String code) {
		return courseDAO.findOneByCode(code);
	}
	
	@Override
	public CourseModel findOneByName(String name) {
		return courseDAO.findOneByName(name);
	}

	@Override
	public int getTotalItem() {
		return courseDAO.getTotalItem();
	}

	@Override
	public Long save(CourseModel classroomModel) {
		return courseDAO.save(classroomModel);
	}

	@Override
	public List<CourseModel> findAll() {
		return courseDAO.findAll();
	}

	@Override
	public Long delete(CourseModel classroomModel) {
		return courseDAO.delete(classroomModel);
	}

}
