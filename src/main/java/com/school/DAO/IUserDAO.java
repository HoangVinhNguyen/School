package com.school.DAO;

import java.util.List;

import com.school.model.UserModel;

public interface IUserDAO extends GenericDAO<UserModel>{

	public UserModel findByEmailAndPasswordAndStatus(String email, String password, Integer status);
	public Long findByEmail(String email);
	public List<UserModel> findAll();
	public UserModel findOne(long id);
	public Long save(UserModel userModel);
	public Long delete(UserModel userModel);
}
