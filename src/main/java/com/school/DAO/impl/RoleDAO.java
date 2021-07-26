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
		String hql = "SELECT r FROM RoleEntity r WHERE r.id=?0 AND r.isDeleted=0";
		List list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, id).getResultList();
		if (!list.isEmpty()) {
			return (RoleEntity) list.get(0);
		}
		return null;
	}

	@Override
	public RoleEntity findOneByCode(String code) {
		String hql = "SELECT r FROM RoleEntity r WHERE r.code=?0 AND r.isDeleted=0";
		List list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, code).getResultList();
		if (!list.isEmpty()) {
			return (RoleEntity) list.get(0);
		}
		return null;
	}
	
	@Override
	public RoleEntity findOneByName(String name) {
		String hql = "SELECT r FROM RoleEntity r WHERE r.name=?0 AND r.isDeleted=0";
		List list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, name).getResultList();
		if (!list.isEmpty()) {
			return (RoleEntity) list.get(0);
		}
		return null;
	}

	@Override
	public int getTotalItem() {
		String hql = "SELECT r FROM RoleEntity r WHERE r.isDeleted=0";
		return (int) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}

	@Override
	public Long save(RoleEntity entity) {
		RoleEntity roleModelCheck;
		if (entity.getId() != null) {
			roleModelCheck = findOne(entity.getId());
			if (roleModelCheck != null && roleModelCheck.getId() != 0) {
				String hql = "UPDATE RoleEntity SET name=?0, code=?1, modifiedBy=?2, modifiedDate=?3 WHERE id=?4";
				sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, entity.getName())
				.setParameter(1, entity.getCode())
				.setParameter(2, entity.getModifiedBy())
				.setParameter(3, entity.getModifiedDate())
				.setParameter(4, entity.getId()).executeUpdate();
				return entity.getId();
			}
		}
		sessionFactory.getCurrentSession().save(entity);
		return entity.getId();
	}

	@Override
	public Long delete(RoleEntity entity) {
		String hql = "UPDATE RoleEntity SET modifiedBy=?0, modifiedDate=?1, isDeleted=1 WHERE id=?2";
		sessionFactory.getCurrentSession().createQuery(hql)
		.setParameter(0, entity.getModifiedBy())
		.setParameter(1, entity.getModifiedDate())
		.setParameter(2, entity.getId()).executeUpdate();
		return entity.getId();
	}
}
