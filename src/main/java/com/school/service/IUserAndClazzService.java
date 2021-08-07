package com.school.service;

import java.util.List;

import com.school.model.ClazzModel;
import com.school.model.UserAndClazzModel;
import com.school.model.UserModel;

public interface IUserAndClazzService {

	public List<UserAndClazzModel> findAll();
	public UserAndClazzModel findOne(UserModel user, ClazzModel clazz);
	public List<UserAndClazzModel> findOneByUser(String userEmail);
	public List<UserAndClazzModel> findOneByCourseName(String name);
	public Long save(UserAndClazzModel model, String method);
	public Long delete(UserAndClazzModel model);
	public int getTotalItem();
}
