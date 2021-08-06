package com.school.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.school.DAO.IUserAndCourseDAO;
import com.school.constant.SystemConstant;
import com.school.entity.CourseEntity;
import com.school.entity.UserAndCourseEntity;
import com.school.entity.UserEntity;
import com.school.model.CourseModel;
import com.school.model.UserAndCourseModel;
import com.school.model.UserModel;
import com.school.service.ICourseService;
import com.school.service.IUserAndCourseService;
import com.school.service.IUserService;

@Service
public class UserAndCourseService implements IUserAndCourseService {

	@Autowired
	private IUserAndCourseDAO userAndCourseDAO;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICourseService courseService;

	@Override
	public List<UserAndCourseModel> findAll() {
		List<UserAndCourseModel> inClassroomModels = new ArrayList<UserAndCourseModel>();
		List<UserAndCourseEntity> inClassroomEntities = userAndCourseDAO.findAll();
		Iterator<UserAndCourseEntity> itr = inClassroomEntities.iterator();
		while(itr.hasNext()) {
			UserAndCourseModel model = new UserAndCourseModel();
			model.loadFromEntity(itr.next());
			inClassroomModels.add(model);
		}
		return inClassroomModels;
	}

	@Override
	public UserAndCourseModel findOne(UserModel user, CourseModel course) {
		if (course != null && user != null) {
			UserAndCourseModel model = new UserAndCourseModel();
			UserEntity userEntity = new UserEntity();
			userEntity.loadFromDTO(user);
			CourseEntity courseEntity = new CourseEntity();
			courseEntity.loadFromDTO(course);
			model.loadFromEntity(userAndCourseDAO.findOne(userEntity, courseEntity));
			return model;
		}
		return null;
	}

	@Override
	public UserAndCourseModel findOneByUser(String userEmail) {
		UserAndCourseModel model = new UserAndCourseModel();
		model.loadFromEntity(userAndCourseDAO.findOneByUser(userEmail));
		return model;
	}

	@Override
	public List<UserAndCourseModel> findOneByCourseId(long id) {
		List<UserAndCourseModel> inClassroomModels = new ArrayList<UserAndCourseModel>();
		List<UserAndCourseEntity> inClassroomEntities = userAndCourseDAO.findOneByCourseId(id);
		Iterator<UserAndCourseEntity> itr = inClassroomEntities.iterator();
		while(itr.hasNext()) {
			UserAndCourseModel model = new UserAndCourseModel();
			model.loadFromEntity(itr.next());
			inClassroomModels.add(model);
		}
		return inClassroomModels;
	}

	@Override
	public Long save(UserAndCourseModel model, String method) {
		if (model != null && method != null) {
			model = getModifiedField(model, method);
			if (model.getCourseModel().getName() != null && model.getUserModel().getEmail() != null) {
				UserModel userModel = userService.findByEmail(model.getUserModel().getEmail());
				CourseModel courseModel = courseService.findOneByName(model.getCourseModel().getName());
				if (userModel != null && courseModel != null) {
					model.setUserModel(userModel);
					model.setCourseModel(courseModel);
				}
				if (model.getOldCourse().getName() != null) {
					CourseModel oldCourse = courseService.findOneByName(model.getOldCourse().getName());
					if (oldCourse != null) {
						model.setOldCourse(oldCourse);
					}
				}
				UserAndCourseEntity userAndCourseEntity = new UserAndCourseEntity();
				userAndCourseEntity.loadFromDTO(model);
				return userAndCourseDAO.save(userAndCourseEntity);
			}
		}
		return SystemConstant.ERROR;
	}

	@Override
	public Long delete(UserAndCourseModel model) {
		if (model != null) {
			UserModel userModel = userService.findOne(model.getUserModel().getId());
			CourseModel courseModel = courseService.findOne(model.getCourseModel().getId());
			if (userModel != null && courseModel != null) {
				model.setUserModel(userModel);
				model.setCourseModel(courseModel);
				UserAndCourseEntity userAndCourseEntity = new UserAndCourseEntity();
				userAndCourseEntity.loadFromDTO(model);
				return userAndCourseDAO.delete(userAndCourseEntity);
			}
			return SystemConstant.ERROR;
		}
		return SystemConstant.ERROR;
	}

	@Override
	public int getTotalItem() {
		return userAndCourseDAO.getTotalItem();
	}

	private UserAndCourseModel getModifiedField(UserAndCourseModel model, String method) {
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
