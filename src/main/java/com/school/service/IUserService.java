package com.school.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.school.model.UserModel;

public interface IUserService {

	public UserModel findByEmailAndPasswordAndStatus(String email, String password, Integer status);
	public Long findByEmail(String email);
	public UserModel findByUserName(String email);
	public List<UserModel> findAll();
	public UserModel findOne(long id);
	public Long save(UserModel userModel, String method);
	public Long delete(UserModel userModel);
	
	public Long saveList(MultipartFile file, String role);
}
