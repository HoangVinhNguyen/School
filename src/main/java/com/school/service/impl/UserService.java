package com.school.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.DAO.IUserDAO;
import com.school.entity.UserEntity;
import com.school.model.UserModel;
import com.school.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDAO userDAO;
	
	@Override
	public UserModel findByEmailAndPasswordAndStatus(String email, String password, Integer status) {
		UserEntity userEntity = userDAO.findByEmailAndPasswordAndStatus(email, password, status);
		UserModel userModel = new UserModel();
		userModel.loadFromEntity(userEntity);
		return userModel;
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
			UserModel userModel = new UserModel();
			userModel.loadFromEntity(itr.next());
			userModels.add(userModel);
		}
		return userModels;
	}

	@Override
	public UserModel findOne(long id) {
		UserModel userModel = new UserModel();
		userModel.loadFromEntity(userDAO.findOne(id));
		return userModel;
	}

	@Override
	public Long save(UserModel userModel) {
		UserEntity userEntity = new UserEntity();
		userEntity.loadFromDTO(userModel);
		return userDAO.save(userEntity);
	}

	@Override
	public Long delete(UserModel userModel) {
		UserEntity userEntity = new UserEntity();
		userEntity.loadFromDTO(userModel);
		return userDAO.delete(userEntity);
	}

	@Override
	public UserModel findByUserName(String email) {
		UserModel userModel = new UserModel();
		userModel.loadFromEntity(userDAO.findByUserName(email));
		return userModel;
	}

}
