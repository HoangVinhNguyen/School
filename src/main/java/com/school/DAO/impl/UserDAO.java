package com.school.DAO.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.DAO.IUserDAO;
import com.school.entity.UserEntity;

@Repository
@Transactional
public class UserDAO implements IUserDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public UserEntity findByEmailAndPasswordAndStatus(String email, String password, Integer status) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user as u ");
		sql.append(" INNER JOIN role as r on r.id=u.role_id");
		sql.append(" where u.email=? and u.is_deleted=?");
		UserEntity user = (UserEntity) sessionFactory.getCurrentSession().createQuery(sql.toString())
				.setParameter(0, email).setParameter(1, password).list().get(0);
		return user;
	}

	@Override
	public Long findByEmail(String email) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user as u ");
		sql.append(" INNER JOIN role as r on r.id=u.role_id");
		sql.append(" where u.email=? and u.is_deleted=0");
		UserEntity user = (UserEntity) sessionFactory.getCurrentSession().createQuery(sql.toString())
				.setParameter(0, email).list().get(0);
		return user.getId();
	}

	@Override
	public List<UserEntity> findAll() {
		StringBuilder sql = new StringBuilder("SELECT * FROM user as u ");
		sql.append(" INNER JOIN role as r on r.id=u.role_id");
		sql.append(" WHERE u.is_deleted=0");
		List<UserEntity> users = sessionFactory.getCurrentSession().createQuery(sql.toString()).list();
		return users.isEmpty() ? null : users;
	}
	
	@Override
	public Long save(UserEntity userEntity) {
		UserEntity userModelCheck;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if (userEntity.getId() != null) {
			userModelCheck = findOne(userEntity.getId());
			if (userModelCheck != null && userModelCheck.getId() != 0) {
				sessionFactory.getCurrentSession().merge(userEntity);
				return userEntity.getId();
			}
		}
		sessionFactory.getCurrentSession().save(userEntity);
		return userEntity.getId();
	}

	@Override
	public UserEntity findOne(long id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user as u ");
		sql.append(" INNER JOIN role as r on r.id=u.role_id");
		sql.append(" WHERE u.is_deleted=0 and u.id=?");
		return (UserEntity) sessionFactory.getCurrentSession().createQuery(sql.toString())
				.setParameter(0, id);
	}

	@Override
	public Long delete(UserEntity userEntity) {
		String sql = "UPDATE user SET modified_by=?, modified_date=?, is_deleted = 1 WHERE id=?";
		sessionFactory.getCurrentSession().createQuery(sql.toString())
		.setParameter(0, userEntity.getModifiedBy())
		.setParameter(1, userEntity.getModifiedDate())
		.setParameter(2, userEntity.getId());
		return userEntity.getId();
	}
	
}
