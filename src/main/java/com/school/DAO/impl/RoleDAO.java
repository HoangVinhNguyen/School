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
	SessionFactory sessionFactory;
	
	@Override
	public List<RoleEntity> findAll() {
		return (List<RoleEntity>) sessionFactory.getCurrentSession().createCriteria(RoleEntity.class);
	}

	@Override
	public RoleEntity findOne(long id) {
		String sql = "SELECT * FROM role WHERE id = ? AND is_deleted = 0";
		return (RoleEntity) sessionFactory.getCurrentSession().createQuery(sql).setParameter(0, id);
	}

	@Override
	public RoleEntity findOneByCode(String code) {
		String sql = "SELECT * FROM role WHERE code = ? AND is_deleted = 0";
		return (RoleEntity) sessionFactory.getCurrentSession().createQuery(sql).setParameter(0, code);
	}
	
	@Override
	public RoleEntity findOneByName(String name) {
		String sql = "SELECT * FROM role WHERE name = ? AND is_deleted = 0";
		return (RoleEntity) sessionFactory.getCurrentSession().createQuery(sql).setParameter(0, name);
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM role WHERE is_deleted = 0";
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
		String sql = "UPDATE role SET modified_by=?, modified_date=?, is_deleted = 1 WHERE id=?";
		sessionFactory.getCurrentSession().createQuery(sql)
		.setParameter(0, roleEntity.getModifiedBy())
		.setParameter(1, roleEntity.getModifiedDate())
		.setParameter(2, roleEntity.getId());
		return roleEntity.getId();
	}
}
