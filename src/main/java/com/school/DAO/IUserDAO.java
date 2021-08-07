package com.school.DAO;

import java.util.List;

import com.school.entity.UserEntity;

public interface IUserDAO {

	public UserEntity findByEmailAndPasswordAndStatus(String email, String password, Integer status);
	public UserEntity findByEmail(String email);
	public UserEntity findByUserName(String username);
	public List<UserEntity> findAll();
	public UserEntity findOne(long id);
	public Long save(UserEntity userEntity);
	public Long delete(UserEntity userEntity);
}
