package com.school.DAO.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.DAO.IRoleDAO;
import com.school.entity.RoleEntity;

@Repository
@Transactional
public class RoleDAO implements IRoleDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<RoleEntity> findAll() {
		return (List<RoleEntity>) sessionFactory.getCurrentSession().createCriteria(RoleEntity.class);
	}

	@Override
	public RoleEntity findOne(long id) {
		String sql = "SELECT r FROM RoleEntity r WHERE r.id=?0 AND r.isDeleted=0";
		RoleEntity roleEntity = (RoleEntity) sessionFactory.getCurrentSession().createQuery(sql).setParameter(0, id).getSingleResult();
		return roleEntity;
	}

	@Override
	public RoleEntity findOneByCode(String code) {
		String sql = "SELECT r FROM RoleEntity r WHERE r.code=?0 AND r.isDeleted=0";
		RoleEntity roleEntity = (RoleEntity) sessionFactory.getCurrentSession().createQuery(sql).setParameter(0, code).list().get(0);
		return roleEntity;
	}
	
	@Override
	public RoleEntity findOneByName(String name) {
		String sql = "SELECT * FROM RoleEntity WHERE name = ? AND r.isDeleted = 0";
		return (RoleEntity) sessionFactory.getCurrentSession().createQuery(sql).setParameter(0, name);
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM RoleEntity WHERE r.isDeleted = 0";
		return (int) sessionFactory.getCurrentSession().createQuery(sql).uniqueResult();
	}

	@Override
	public Long save(RoleEntity roleEntity) {
		RoleEntity roleModelCheck;
		if (roleEntity.getId() != null) {
			roleModelCheck = findOne(roleEntity.getId());
			if (roleModelCheck != null && roleModelCheck.getId() != 0) {
				sessionFactory.getCurrentSession().merge(roleEntity);
				return roleEntity.getId();
			}
		}
		sessionFactory.getCurrentSession().save(roleEntity);
		return roleEntity.getId();
	}

	@Override
	public Long delete(RoleEntity roleEntity) {
		String sql = "UPDATE RoleEntity r SET modified_by=?, modified_date=?, r.isDeleted = 1 WHERE id=?";
		sessionFactory.getCurrentSession().createQuery(sql)
		.setParameter(0, roleEntity.getModifiedBy())
		.setParameter(1, roleEntity.getModifiedDate())
		.setParameter(2, roleEntity.getId());
		return roleEntity.getId();
	}
}
