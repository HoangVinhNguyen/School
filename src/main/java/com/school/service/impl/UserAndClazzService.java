package com.school.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.school.DAO.IUserAndClazzDAO;
import com.school.constant.SystemConstant;
import com.school.entity.ClazzEntity;
import com.school.entity.UserAndClazzEntity;
import com.school.entity.UserEntity;
import com.school.model.ClazzModel;
import com.school.model.UserAndClazzModel;
import com.school.model.UserModel;
import com.school.service.IClazzService;
import com.school.service.IUserAndClazzService;
import com.school.service.IUserService;

@Service
public class UserAndClazzService implements IUserAndClazzService {

	@Autowired
	private IUserAndClazzDAO userAndClazzDAO;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IClazzService clazzService;

	@Override
	public List<UserAndClazzModel> findAll() {
		List<UserAndClazzModel> userAndClazzModels = new ArrayList<UserAndClazzModel>();
		List<UserAndClazzEntity> userAndClazzEntities = userAndClazzDAO.findAll();
		Iterator<UserAndClazzEntity> itr = userAndClazzEntities.iterator();
		while(itr.hasNext()) {
			UserAndClazzModel model = new UserAndClazzModel();
			model.loadFromEntity(itr.next());
			userAndClazzModels.add(model);
		}
		return userAndClazzModels;
	}

	@Override
	public UserAndClazzModel findOne(UserModel user, ClazzModel clazz) {
		if (clazz != null && user != null) {
			UserAndClazzModel model = new UserAndClazzModel();
			UserEntity userEntity = new UserEntity();
			userEntity.loadFromDTO(user);
			ClazzEntity clazzEntity = new ClazzEntity();
			clazzEntity.loadFromDTO(clazz);
			model.loadFromEntity(userAndClazzDAO.findOne(userEntity, clazzEntity));
			return model;
		}
		return null;
	}

	@Override
	public List<UserAndClazzModel> findOneByUser(String userEmail) {
		List<UserAndClazzModel> listModel = new ArrayList<>();
		List<UserAndClazzEntity> list = userAndClazzDAO.findOneByUserEmail(userEmail);
		if (!list.isEmpty()) {
			list.forEach(e -> {
				UserAndClazzModel model = new UserAndClazzModel();
				model.loadFromEntity(e);
				listModel.add(model);
			});
			return listModel;
		}
		return null;
	}

	@Override
	public List<UserAndClazzModel> findOneByCourseName(String name) {
		List<UserAndClazzModel> userAndClazzModels = new ArrayList<UserAndClazzModel>();
		List<UserAndClazzEntity> userAndClazzEntities = userAndClazzDAO.findOneByClazzName(name);
		Iterator<UserAndClazzEntity> itr = userAndClazzEntities.iterator();
		while(itr.hasNext()) {
			UserAndClazzModel model = new UserAndClazzModel();
			model.loadFromEntity(itr.next());
			userAndClazzModels.add(model);
		}
		return userAndClazzModels;
	}

	@Override
	public Long save(UserAndClazzModel model, String method) {
		if (model != null && method != null) {
			model = getModifiedField(model, method);
			if (model.getClazzModel().getName() != null && model.getUserModel().getEmail() != null) {
				UserModel userModel = userService.findByEmail(model.getUserModel().getEmail());
				ClazzModel clazzModel = clazzService.findOneByName(model.getClazzModel().getName());
				if (userModel != null && clazzModel != null) {
					model.setUserModel(userModel);
					model.setClazzModel(clazzModel);
				}
				UserAndClazzEntity userAndClazzEntity = new UserAndClazzEntity();
				userAndClazzEntity.loadFromDTO(model);
				return userAndClazzDAO.save(userAndClazzEntity);
			}
		}
		return SystemConstant.ERROR;
	}

	@Override
	public Long delete(UserAndClazzModel model) {
		if (model != null && model.getId() != null) {
			UserAndClazzEntity userAndClazzEntity = new UserAndClazzEntity();
			userAndClazzEntity.loadFromDTO(model);
			return userAndClazzDAO.delete(userAndClazzEntity);
		}
		return SystemConstant.ERROR;
	}

	@Override
	public int getTotalItem() {
		return userAndClazzDAO.getTotalItem();
	}

	private UserAndClazzModel getModifiedField(UserAndClazzModel model, String method) {
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
