package com.school.DAO.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.DAO.IGradeDAO;
import com.school.entity.GradeEntity;

@Repository
@Transactional
public class GradeDAO implements IGradeDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public GradeEntity findOne(long id) {
		return (GradeEntity) sessionFactory.getCurrentSession().get(GradeEntity.class, id);
	}

	@Override
	public GradeEntity findOneByCode(String code) {
		String hql = "SELECT g FROM GradeEntity g WHERE g.code = ?0 AND g.isDeleted = 0";
		List list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, code).getResultList();
		if (!list.isEmpty()) {
			return (GradeEntity) list.get(0);
		}
		return null;
	}
	
	@Override
	public GradeEntity findOneByName(String name) {
		String hql = "SELECT g FROM GradeEntity g WHERE g.name = ?0 AND g.isDeleted = 0";
		List list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, name).getResultList();
		if (!list.isEmpty()) {
			return (GradeEntity) list.get(0);
		}
		return null;
	}

	@Override
	public int getTotalItem() {
		String hql = "SELECT g FROM GradeEntity g WHERE g.isDeleted = 0";
		return (int) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}

	@Override
	public Long save(GradeEntity entity) {
		GradeEntity gradeModelCheck;
		if (entity.getId() != null) {
			gradeModelCheck = findOne(entity.getId());
			if (gradeModelCheck != null && gradeModelCheck.getId() != 0) {
				String hql = "UPDATE GradeEntity SET name=?0, code=?1, levelGradeEntity=?2, modifiedBy=?3, modifiedDate=?4 WHERE id=?5";
				sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, entity.getName())
				.setParameter(1, entity.getCode())
				.setParameter(2, entity.getLevelGradeEntity())
				.setParameter(3, entity.getModifiedBy())
				.setParameter(4, entity.getModifiedDate())
				.setParameter(5, entity.getId()).executeUpdate();
				return entity.getId();
			}
		}
		else {
			sessionFactory.getCurrentSession().save(entity);
			return entity.getId();
		}
		return 0L;
	}

	@Override
	public List<GradeEntity> findAll() {
		String hql = "SELECT g FROM GradeEntity g WHERE g.isDeleted=0";
		@SuppressWarnings("unchecked")
		List<GradeEntity> list = sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}

	@Override
	public Long delete(GradeEntity entity) {
		String hql = "UPDATE GradeEntity SET modifiedBy=?0, modifiedDate=?1, isDeleted=1 WHERE id=?2";
		sessionFactory.getCurrentSession().createQuery(hql)
		.setParameter(0, entity.getModifiedBy())
		.setParameter(1, entity.getModifiedDate())
		.setParameter(2, entity.getId()).executeUpdate();
		return entity.getId();
	}

}
