package com.school.DAO.impl;

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
	private SessionFactory sessionFactory;
	
	@Override
	public UserEntity findByEmailAndPasswordAndStatus(String email, String password, Integer status) {
		StringBuilder sql = new StringBuilder("SELECT * FROM UserEntity as u ");
		//sql.append(" INNER JOIN RoleEntity as r on r.id=u.role_id");
		sql.append(" WHERE u.email=? and u.isDeleted=?0 and u.password=?1");
		UserEntity user = (UserEntity) sessionFactory.getCurrentSession().createQuery(sql.toString())
				.setParameter(0, email).setParameter(1, password).list().get(0);
		return user;
	}

	@Override
	public UserEntity findByEmail(String email) {
		StringBuilder sql = new StringBuilder("SELECT u FROM UserEntity as u ");
		//sql.append(" INNER JOIN RoleEntity as r on r.id=u.role_id");
		sql.append(" WHERE u.email=?0 and u.isDeleted=0");
		List list = sessionFactory.getCurrentSession().createQuery(sql.toString())
				.setParameter(0, email).getResultList();
		if (!list.isEmpty()) {
			UserEntity user = (UserEntity) list.get(0);
			return user;
		}
		return null;
	}

	@Override
	public List<UserEntity> findAll() {
		StringBuilder sql = new StringBuilder("SELECT u FROM UserEntity u ");
		//sql.append(" INNER JOIN RoleEntity r on r.id=u.roleId");
		sql.append(" WHERE u.isDeleted=0");
		List<UserEntity> users = sessionFactory.getCurrentSession().createQuery(sql.toString()).list();
//		users.stream().forEach(e -> e.getCourse().stream().forEach(i -> System.out.println(i.getName())));
		return users.isEmpty() ? null : users;
	}
	
	@Override
	public Long save(UserEntity entity) {
		if (entity.getId() != null) {
			UserEntity entityCheck = findByEmail(entity.getEmail());
			if (entityCheck != null) {
				String hql = "UPDATE UserEntity SET email=?0, fullname=?1, dob=?2, address=?3, phone=?4 "
						+ "role=?5, modifiedBy=?6, modifiedDate=?7 WHERE id=?8";
				sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, entity.getEmail())
				.setParameter(1, entity.getFullname())
				.setParameter(2, entity.getDob())
				.setParameter(3, entity.getAddress())
				.setParameter(4, entity.getPhone())
				.setParameter(5, entity.getRole())
				.setParameter(6, entity.getModifiedBy())
				.setParameter(7, entity.getModifiedDate())
				.setParameter(8, entity.getId()).executeUpdate();
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
		List list = sessionFactory.getCurrentSession().createQuery(sql.toString())
				.setParameter(0, id).getResultList();
		if (!list.isEmpty()) {
			return (UserEntity) list.get(0);
		}
		return null;
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
