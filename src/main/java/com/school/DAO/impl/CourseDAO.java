package com.school.DAO.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.DAO.ICourseDAO;
import com.school.entity.CourseEntity;

@Repository
@Transactional
public class CourseDAO implements ICourseDAO{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<CourseEntity> findAll() {
		return (List<CourseEntity>) sessionFactory.getCurrentSession().createCriteria(CourseEntity.class);
	}

	@Override
	public CourseEntity findOne(long id) {
		String sql = "SELECT * FROM course WHERE id = ? AND is_deleted = 0";
		return (CourseEntity) sessionFactory.getCurrentSession().createQuery(sql).setParameter(0, id);
	}

	@Override
	public CourseEntity findOneByCode(String code) {
		String sql = "SELECT * FROM course WHERE code = ? AND is_deleted = 0";
		return (CourseEntity) sessionFactory.getCurrentSession().createQuery(sql).setParameter(0, code);
	}
	
	@Override
	public CourseEntity findOneByName(String name) {
		String sql = "SELECT * FROM course WHERE name = ? AND is_deleted = 0";
		return (CourseEntity) sessionFactory.getCurrentSession().createQuery(sql).setParameter(0, name);
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM course WHERE is_deleted = 0";
		return (int) sessionFactory.getCurrentSession().createQuery(sql).uniqueResult();
	}

	@Override
	public Long save(CourseEntity courseEntity) {
		CourseEntity courseModelCheck;
		if (courseEntity.getId() != null) {
			courseModelCheck = findOne(courseEntity.getId());
			if (courseModelCheck != null && courseModelCheck.getId() != 0) {
				sessionFactory.getCurrentSession().merge(courseEntity);
				return courseEntity.getId();
			}
		}
		sessionFactory.getCurrentSession().save(courseEntity);
		return courseEntity.getId();
	}

	@Override
	public Long delete(CourseEntity courseEntity) {
		String sql = "UPDATE course SET modified_by=?, modified_date=?, is_deleted = 1 WHERE id=?";
		sessionFactory.getCurrentSession().createQuery(sql)
		.setParameter(0, courseEntity.getModifiedBy())
		.setParameter(1, courseEntity.getModifiedDate())
		.setParameter(2, courseEntity.getId());
		return courseEntity.getId();
	}
}
