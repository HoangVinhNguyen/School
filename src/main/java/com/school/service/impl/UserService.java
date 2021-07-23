package com.school.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.school.DAO.IUserDAO;
import com.school.constant.SystemConstant;
import com.school.entity.UserEntity;
import com.school.model.UserModel;
import com.school.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UserModel findByEmailAndPasswordAndStatus(String email, String password, Integer status) {
		UserEntity userEntity = userDAO.findByEmailAndPasswordAndStatus(email, password, status);
		UserModel model = new UserModel();
		model.loadFromEntity(userEntity);
		return model;
	}

	@Override
	public Long findByEmail(String email) {
		return userDAO.findByEmail(email);
	}

	@Override
	public List<UserModel> findAll() {
		List<UserModel> userModels = new ArrayList<>();
		List<UserEntity> userEntities = userDAO.findAll();
		Iterator<UserEntity> itr = userEntities.iterator();
		while(itr.hasNext()) {
			UserModel model = new UserModel();
			model.loadFromEntity(itr.next());
			userModels.add(model);
		}
		return userModels;
	}

	@Override
	public UserModel findOne(long id) {
		UserModel model = new UserModel();
		model.loadFromEntity(userDAO.findOne(id));
		return model;
	}

	@Override
	public Long save(UserModel model, String method) {
		model = getModifiedField(model, method);
		UserEntity userEntity = new UserEntity();
		userEntity.loadFromDTO(model);
		return userDAO.save(userEntity);
	}

	@Override
	public Long delete(UserModel model) {
		model = getModifiedField(model, SystemConstant.MODIFY);
		UserEntity userEntity = new UserEntity();
		userEntity.loadFromDTO(model);
		return userDAO.delete(userEntity);
	}

	@Override
	public UserModel findByUserName(String email) {
		UserModel model = new UserModel();
		model.loadFromEntity(userDAO.findByUserName(email));
		return model;
	}
	
	private UserModel getModifiedField(UserModel model, String method) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		switch (method) {
		case SystemConstant.INSERT:
			model.setPassword(passwordEncoder.encode(model.getPassword()));
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
