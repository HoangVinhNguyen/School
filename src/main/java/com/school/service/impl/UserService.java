package com.school.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.school.DAO.IUserDAO;
import com.school.model.UserModel;
import com.school.service.IUserService;

public class UserService implements IUserService{

	@Inject
	private IUserDAO userDAO;
	
	@Override
	public UserModel findByEmailAndPasswordAndStatus(String email, String password, Integer status) {
		return userDAO.findByEmailAndPasswordAndStatus(email, password, status);
	}

	@Override
	public Long findByEmail(String email) {
		return userDAO.findByEmail(email);
	}

	@Override
	public List<UserModel> findAll() {
		return userDAO.findAll();
	}

	@Override
	public UserModel findOne(long id) {
		return userDAO.findOne(id);
	}

	@Override
	public Long save(UserModel userModel) {
		return userDAO.save(userModel);
	}

	@Override
	public Long delete(UserModel userModel) {
		return userDAO.delete(userModel);
	}

}
