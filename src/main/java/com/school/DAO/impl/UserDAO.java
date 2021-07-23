package com.school.DAO.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.DAO.IUserDAO;
import com.school.entity.UserEntity;

@Repository
@Transactional
public class UserDAO implements IUserDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public UserEntity findByEmailAndPasswordAndStatus(String email, String password, Integer status) {
		StringBuilder sql = new StringBuilder("SELECT * FROM UserEntity as u ");
		sql.append(" INNER JOIN RoleEntity as r on r.id=u.role_id");
		sql.append(" where u.email=? and u.isDeleted=?");
		UserEntity user = (UserEntity) sessionFactory.getCurrentSession().createQuery(sql.toString())
				.setParameter(0, email).setParameter(1, password).list().get(0);
		return user;
	}

	@Override
	public Long findByEmail(String email) {
		StringBuilder sql = new StringBuilder("SELECT u FROM UserEntity as u ");
		//sql.append(" INNER JOIN RoleEntity as r on r.id=u.role_id");
		sql.append(" where u.email=? and u.isDeleted=0");
		UserEntity user = (UserEntity) sessionFactory.getCurrentSession().createQuery(sql.toString())
				.setParameter(0, email).list().get(0);
		return user.getId();
	}

	@Override
	public List<UserEntity> findAll() {
		StringBuilder sql = new StringBuilder("SELECT u FROM UserEntity u ");
		//sql.append(" INNER JOIN RoleEntity r on r.id=u.roleId");
		sql.append(" WHERE u.isDeleted=0");
		List<UserEntity> users = sessionFactory.getCurrentSession().createQuery(sql.toString()).list();
		return users.isEmpty() ? null : users;
	}
	
	@Override
	public Long save(UserEntity entity) {
		UserEntity userModelCheck;
		if (entity.getId() != null) {
			userModelCheck = findOne(entity.getId());
			if (userModelCheck != null && userModelCheck.getId() != 0) {
				String hql = "UPDATE UserEntity SET email=?0, fullname=?1, dob=?2, address=?3, "
						+ "role=?4, modifiedBy=?5, modifiedDate=?6 WHERE id=?7";
				sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, entity.getEmail())
				.setParameter(1, entity.getFullname())
				.setParameter(2, entity.getDob())
				.setParameter(3, entity.getAddress())
				.setParameter(4, entity.getRole())
				.setParameter(5, entity.getModifiedBy())
				.setParameter(6, entity.getModifiedDate())
				.setParameter(7, entity.getId()).executeUpdate();
				return entity.getId();
			}
		}
		sessionFactory.getCurrentSession().save(entity);
		return entity.getId();
	}

	@Override
	public UserEntity findOne(long id) {
		StringBuilder sql = new StringBuilder("SELECT u FROM UserEntity u");
		//sql.append(" INNER JOIN RoleEntity as r on r.id=u.role_id");
		sql.append(" WHERE u.isDeleted=0 and u.id=?0");
		return (UserEntity) sessionFactory.getCurrentSession().createQuery(sql.toString())
				.setParameter(0, id).list().get(0);
	}

	@Override
	public Long delete(UserEntity userEntity) {
		String sql = "UPDATE UserEntity SET modifiedBy=?0, modifiedDate=?1, isDeleted = 1 WHERE id=?2";
		sessionFactory.getCurrentSession().createQuery(sql.toString())
		.setParameter(0, userEntity.getModifiedBy())
		.setParameter(1, userEntity.getModifiedDate())
		.setParameter(2, userEntity.getId()).executeUpdate();
		return userEntity.getId();
	}

	@Override
	public UserEntity findByUserName(String username) {
		UserEntity user;
		try {
			String sql = "SELECT u FROM UserEntity u WHERE u.email=:email AND u.isDeleted=0";
			user= (UserEntity) sessionFactory.getCurrentSession().createQuery(sql)
				.setParameter("email", username).getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}
	
}
