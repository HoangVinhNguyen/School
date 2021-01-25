package com.school.DAO.impl;

import java.sql.Timestamp;
import java.util.List;

import com.school.DAO.IUserDAO;
import com.school.mapper.UserMapper;
import com.school.model.UserModel;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO{

	@Override
	public UserModel findByEmailAndPasswordAndStatus(String email, String password, Integer status) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user as u ");
		sql.append(" INNER JOIN role as r on r.id=u.role_id");
		sql.append(" where u.email=? and u.is_deleted=?");
		List<UserModel> users = query(sql.toString(), new UserMapper(), email, status);
		return users.isEmpty() ? null : users.get(0);
	}

	@Override
	public Long findByEmail(String email) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user as u ");
		sql.append(" INNER JOIN role as r on r.id=u.role_id");
		sql.append(" where u.email=? and u.is_deleted=0");
		List<UserModel> users = query(sql.toString(), new UserMapper(), email);
		return users.isEmpty() ? null : users.get(0).getId();
	}

	@Override
	public List<UserModel> findAll() {
		StringBuilder sql = new StringBuilder("SELECT * FROM user as u ");
		sql.append(" INNER JOIN role as r on r.id=u.role_id");
		sql.append(" WHERE u.is_deleted=0");
		List<UserModel> users = query(sql.toString(), new UserMapper());
		return users.isEmpty() ? null : users;
	}
	
	@Override
	public Long save(UserModel userModel) {
		UserModel userModelCheck;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if (userModel.getId() != null) {
			userModelCheck = findOne(userModel.getId());
			if (userModelCheck != null && userModelCheck.getId() != 0) {
				String sql = "UPDATE user SET email=?, fullname=?, dob=?, address=?, role_id=?, modified_by=?, modified_date=? WHERE id=?";
				return update(sql, userModel.getEmail(), userModel.getFullname(), userModel.getDob() == null ? timestamp : userModel.getDob(), userModel.getAddress(), userModel.isRoledId(),
						userModel.getModifiedBy(), userModel.getModifiedDate(), userModel.getId());
			}
		}
		String sql = "INSERT INTO user (email, fullname, dob, address, role_id, created_by, created_date, modified_date) values (?, ?, ?, ?, ?, ?, ?, ?)";
		return insert(sql, userModel.getEmail(), userModel.getFullname(), userModel.getDob() == null ? timestamp : userModel.getDob(), userModel.getAddress(), 
				userModel.isRoledId(), userModel.getCreatedBy(), userModel.getCreatedDate(), userModel.getModifiedDate());
	}

	@Override
	public UserModel findOne(long id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user as u ");
		sql.append(" INNER JOIN role as r on r.id=u.role_id");
		sql.append(" WHERE u.is_deleted=0 and u.id=?");
		List<UserModel> userModel = query(sql.toString(), new UserMapper(), id);
		return userModel.isEmpty() ? null : userModel.get(0);
	}

	@Override
	public Long delete(UserModel userModel) {
		String sql = "UPDATE user SET modified_by=?, modified_date=?, is_deleted = 1 WHERE id=?";
		return update(sql, userModel.getModifiedBy(), userModel.getModifiedDate(), userModel.getId());
	}
	
}
