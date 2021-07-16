package com.school.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.DAO.ICourseDAO;
import com.school.entity.CourseEntity;
import com.school.model.CourseModel;
import com.school.service.ICourseService;

@Service
public class CourseService implements ICourseService {

	@Autowired
	private ICourseDAO courseDAO;
	
	@Override
	public CourseModel findOne(long id) {
		CourseModel courseModel = new CourseModel();
		courseModel.loadFromEntity(courseDAO.findOne(id));
		return courseModel;
	}

	@Override
	public CourseModel findOneByCode(String code) {
		CourseModel courseModel = new CourseModel();
		courseModel.loadFromEntity(courseDAO.findOneByCode(code));
		return courseModel;
	}
	
	@Override
	public CourseModel findOneByName(String name) {
		CourseModel courseModel = new CourseModel();
		courseModel.loadFromEntity(courseDAO.findOneByName(name));
		return courseModel;
	}

	@Override
	public int getTotalItem() {
		return courseDAO.getTotalItem();
	}

	@Override
	public Long save(CourseModel courseModel) {
		CourseEntity courseEntity = new CourseEntity();
		courseEntity.loadFromDTO(courseModel);
		return courseDAO.save(courseEntity);
	}

	@Override
	public List<CourseModel> findAll() {
		List<CourseModel> courseModels = new ArrayList<CourseModel>();
		List<CourseEntity> courseEntities = courseDAO.findAll();
		Iterator<CourseEntity> itr = courseEntities.iterator();
		while(itr.hasNext()) {
			CourseModel courseModel = new CourseModel();
			courseModel.loadFromEntity(itr.next());
			courseModels.add(courseModel);
		}
		return courseModels;
	}

	@Override
	public Long delete(CourseModel courseModel) {
		CourseEntity courseEntity = new CourseEntity();
		courseEntity.loadFromDTO(courseModel);
		return courseDAO.delete(courseEntity);
	}

}
