package com.school.DAO.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.DAO.ICourseDAO;
import com.school.constant.SystemConstant;
import com.school.entity.CourseEntity;

@Repository
@Transactional
public class CourseDAO implements ICourseDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<CourseEntity> findAll() {
		String hql = "SELECT c FROM CourseEntity c WHERE c.isDeleted=0";
		@SuppressWarnings("unchecked")
		List<CourseEntity> list = sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}

	@Override
	public CourseEntity findOne(long id) {
		String hql = "SELECT c FROM CourseEntity c WHERE c.id=?0 AND c.isDeleted=0";
		List list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, id).getResultList();
		if (!list.isEmpty()) {
			return (CourseEntity) list.get(0);
		}
		return null;
	}

	@Override
	public CourseEntity findOneByCode(String code) {
		String hql = "SELECT c FROM CourseEntity c WHERE c.code=?0 AND c.isDeleted=0";
		List list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, code).getResultList();
		if (!list.isEmpty()) {
			return (CourseEntity) list.get(0);
		}
		return null;
	}
	
	@Override
	public CourseEntity findOneByName(String name) {
		String hql = "SELECT c FROM CourseEntity c WHERE c.name=?0 AND c.isDeleted=0";
		List list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, name).getResultList();
		if (!list.isEmpty()) {
			return (CourseEntity) list.get(0);
		}
		return null;
	}

	@Override
	public int getTotalItem() {
		String hql = "SELECT c FROM CourseEntity c WHERE c.isDeleted=0";
		return (int) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}

	@Override
	public Long save(CourseEntity entity) {
		if (entity != null) {
			CourseEntity courseModelCheck;
			CourseEntity courseModelCheckName;
			CourseEntity courseModelCheckCode;
			if (entity.getId() != null) {
				courseModelCheck = findOne(entity.getId());
				if (courseModelCheck != null && courseModelCheck.getId() != 0) {
					courseModelCheckName = findOneByName(entity.getName());
					courseModelCheckCode = findOneByCode(entity.getCode());
					if (courseModelCheckCode == null || courseModelCheckName == null) {
						String hql = "UPDATE CourseEntity SET name=?0, code=?1, modifiedBy=?2, modifiedDate=?3 WHERE id=?4";
						sessionFactory.getCurrentSession().createQuery(hql)
						.setParameter(0, entity.getName())
						.setParameter(1, entity.getCode())
						.setParameter(2, entity.getModifiedBy())
						.setParameter(3, entity.getModifiedDate())
						.setParameter(4, entity.getId()).executeUpdate();
						return entity.getId();
					}
					return SystemConstant.DUPLICATE;
				}
			}
			else {
				courseModelCheckName = findOneByName(entity.getName());
				courseModelCheckCode = findOneByCode(entity.getCode());
				if (courseModelCheckCode == null && courseModelCheckName == null) {
					sessionFactory.getCurrentSession().save(entity);
					return entity.getId();
				} else {
					return SystemConstant.DUPLICATE;
				}
			}
		}
		return SystemConstant.ERROR;
	}

	@Override
	public Long delete(CourseEntity entity) {
		String hql = "UPDATE CourseEntity SET modifiedBy=?0, modifiedDate=?1, isDeleted=1 WHERE id=?2";
		sessionFactory.getCurrentSession().createQuery(hql)
		.setParameter(0, entity.getModifiedBy())
		.setParameter(1, entity.getModifiedDate())
		.setParameter(2, entity.getId()).executeUpdate();
		return entity.getId();
	}
}
