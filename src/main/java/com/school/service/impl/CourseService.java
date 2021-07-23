package com.school.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.school.DAO.ICourseDAO;
import com.school.constant.SystemConstant;
import com.school.entity.CourseEntity;
import com.school.model.CourseModel;
import com.school.service.ICourseService;

@Service
public class CourseService implements ICourseService {

	@Autowired
	private ICourseDAO courseDAO;
	
	@Override
	public CourseModel findOne(long id) {
		CourseModel model = new CourseModel();
		model.loadFromEntity(courseDAO.findOne(id));
		return model;
	}

	@Override
	public CourseModel findOneByCode(String code) {
		CourseModel model = new CourseModel();
		model.loadFromEntity(courseDAO.findOneByCode(code));
		return model;
	}
	
	@Override
	public CourseModel findOneByName(String name) {
		CourseModel model = new CourseModel();
		model.loadFromEntity(courseDAO.findOneByName(name));
		return model;
	}

	@Override
	public int getTotalItem() {
		return courseDAO.getTotalItem();
	}

	@Override
	public Long save(CourseModel model, String method) {
		model = getModifiedField(model, method);
		CourseEntity courseEntity = new CourseEntity();
		courseEntity.loadFromDTO(model);
		return courseDAO.save(courseEntity);
	}

	@Override
	public List<CourseModel> findAll() {
		List<CourseModel> courseModels = new ArrayList<CourseModel>();
		List<CourseEntity> courseEntities = courseDAO.findAll();
		Iterator<CourseEntity> itr = courseEntities.iterator();
		while(itr.hasNext()) {
			CourseModel model = new CourseModel();
			model.loadFromEntity(itr.next());
			courseModels.add(model);
		}
		return courseModels;
	}

	@Override
	public Long delete(CourseModel model) {
		CourseEntity courseEntity = new CourseEntity();
		courseEntity.loadFromDTO(model);
		return courseDAO.delete(courseEntity);
	}

	private CourseModel getModifiedField(CourseModel model, String method) {
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
