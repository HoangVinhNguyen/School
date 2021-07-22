package com.school.DAO.impl;

import java.sql.Timestamp;
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
	private SessionFactory sessionFactory;
	
	@Override
	public ClassroomEntity findOne(long id) {
		return (ClassroomEntity) sessionFactory.getCurrentSession().get(ClassroomEntity.class, id);
	}

	@Override
	public ClassroomEntity findOneByCode(String code) {
		String sql = "SELECT * FROM ClassroomEntity WHERE code = ? AND is_deleted = 0";
		return (ClassroomEntity) sessionFactory.getCurrentSession().createQuery(sql).setParameter(0, code);
	}
	
	@Override
	public ClassroomEntity findOneByName(String name) {
		String sql = "SELECT * FROM ClassroomEntity WHERE name = ? AND is_deleted = 0";
		return (ClassroomEntity) sessionFactory.getCurrentSession().createQuery(sql).setParameter(0, name);
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM ClassroomEntity WHERE is_deleted = 0";
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
		String sql = "SELECT cr FROM ClassroomEntity cr WHERE cr.isDeleted=0";
		@SuppressWarnings("unchecked")
		List<ClassroomEntity> list = sessionFactory.getCurrentSession().createQuery(sql).list();
		return list;
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ClassroomEntity.class);
//		return criteria.list();
		//return (List<ClassroomEntity>) sessionFactory.getCurrentSession().createCriteria(ClassroomEntity.class);
//		CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
//		CriteriaQuery<ClassroomEntity> criteriaQuery = criteriaBuilder.createQuery(ClassroomEntity.class);
//		Root<ClassroomEntity> root = criteriaQuery.from(ClassroomEntity.class);
//		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("isDeleted"), 0));
//		Query<ClassroomEntity> query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
//		return query.getResultList();
	}

	@Override
	public Long delete(ClassroomEntity classroomEntity) {
		String sql = "UPDATE ClassroomEntity SET modifiedBy=?0, modifiedDate=?1, isDeleted=1 WHERE id=?2";
		sessionFactory.getCurrentSession().createQuery(sql)
		.setParameter(0, classroomEntity.getModifiedBy())
		.setParameter(1, classroomEntity.getModifiedDate())
		.setParameter(2, classroomEntity.getId());
		return classroomEntity.getId();
	}
}
