package com.school.DAO.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.DAO.IClassroomDAO;
import com.school.entity.ClassroomEntity;

@Repository
@Transactional
public class ClassroomDAO implements IClassroomDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public ClassroomEntity findOne(long id) {
		return (ClassroomEntity) sessionFactory.getCurrentSession().get(ClassroomEntity.class, id);
	}

	@Override
	public ClassroomEntity findOneByCode(String code) {
		String sql = "SELECT * FROM classroom WHERE code = ? AND is_deleted = 0";
		return (ClassroomEntity) sessionFactory.getCurrentSession().createQuery(sql).setParameter(0, code);
	}
	
	@Override
	public ClassroomEntity findOneByName(String name) {
		String sql = "SELECT * FROM classroom WHERE name = ? AND is_deleted = 0";
		return (ClassroomEntity) sessionFactory.getCurrentSession().createQuery(sql).setParameter(0, name);
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM classroom WHERE is_deleted = 0";
		return (int) sessionFactory.getCurrentSession().createQuery(sql).uniqueResult();
	}

	@Override
	public Long save(ClassroomEntity classroomEntity) {
		ClassroomEntity classroomModelCheck;
		if (classroomEntity.getId() != null) {
			classroomModelCheck = findOne(classroomEntity.getId());
			if (classroomModelCheck != null && classroomModelCheck.getId() != 0) {
				sessionFactory.getCurrentSession().merge(classroomEntity);
				return classroomEntity.getId();
			}
		}
		sessionFactory.getCurrentSession().save(classroomEntity);
		return classroomEntity.getId();
	}

	@Override
	public List<ClassroomEntity> findAll() {
//		String sql = "SELECT cl FROM classroom cl  WHERE cl.is_deleted=0";
//		List<ClassroomEntity> list = (List<ClassroomEntity>) sessionFactory.getCurrentSession().createQuery(sql).list();
//		return list;
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ClassroomEntity.class);
//		return criteria.list();
		//return (List<ClassroomEntity>) sessionFactory.getCurrentSession().createCriteria(ClassroomEntity.class);
		CriteriaBuilder cb = sessionFactory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<ClassroomEntity> cr = cb.createQuery(ClassroomEntity.class);
		Root<ClassroomEntity> root = cr.from(ClassroomEntity.class);
		cr.select(root);

		Query<ClassroomEntity> query = sessionFactory.getCurrentSession().createQuery(cr);
		return query.getResultList();
	}

	@Override
	public Long delete(ClassroomEntity classroomEntity) {
		String sql = "UPDATE classroom SET modified_by=?, modified_date=?, is_deleted = 1 WHERE id=?";
		sessionFactory.getCurrentSession().createQuery(sql)
		.setParameter(0, classroomEntity.getModifiedBy())
		.setParameter(1, classroomEntity.getModifiedDate())
		.setParameter(2, classroomEntity.getId());
		return classroomEntity.getId();
	}
}
