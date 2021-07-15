package com.school.DAO.impl;

import java.util.List;

import org.hibernate.SessionFactory;
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
		return (List<ClassroomEntity>) sessionFactory.getCurrentSession().createCriteria(ClassroomEntity.class);
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
