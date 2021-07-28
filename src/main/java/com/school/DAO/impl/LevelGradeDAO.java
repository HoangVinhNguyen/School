package com.school.DAO.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.DAO.ILevelGradeDAO;
import com.school.entity.LevelGradeEntity;

@Repository
@Transactional
public class LevelGradeDAO implements ILevelGradeDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public LevelGradeEntity findOne(long id) {
		return (LevelGradeEntity) sessionFactory.getCurrentSession().get(LevelGradeEntity.class, id);
	}

	@Override
	public LevelGradeEntity findOneByCode(String code) {
		String hql = "SELECT lg FROM LevelGradeEntity lg WHERE lg.code = ?0 AND lg.isDeleted = 0";
		List list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, code).getResultList();
		if (!list.isEmpty()) {
			return (LevelGradeEntity) list.get(0);
		}
		return null;
	}
	
	@Override
	public LevelGradeEntity findOneByName(String name) {
		String hql = "SELECT lg FROM LevelGradeEntity lg WHERE lg.name = ?0 AND lg.isDeleted = 0";
		List list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, name).getResultList();
		if (!list.isEmpty()) {
			return (LevelGradeEntity) list.get(0);
		}
		return null;
	}

	@Override
	public int getTotalItem() {
		String hql = "SELECT lg FROM LevelGradeEntity lg WHERE lg.isDeleted = 0";
		return (int) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}

	@Override
	public Long save(LevelGradeEntity entity) {
		LevelGradeEntity levelGradeModelCheck;
		if (entity.getId() != null) {
			levelGradeModelCheck = findOne(entity.getId());
			if (levelGradeModelCheck != null && levelGradeModelCheck.getId() != 0) {
				String hql = "UPDATE LevelGradeEntity SET name=?0, code=?1, modifiedBy=?2, modifiedDate=?3 WHERE id=?4";
				sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, entity.getName())
				.setParameter(1, entity.getCode())
				.setParameter(2, entity.getModifiedBy())
				.setParameter(3, entity.getModifiedDate())
				.setParameter(4, entity.getId()).executeUpdate();
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
	public List<LevelGradeEntity> findAll() {
		String hql = "SELECT lg FROM LevelGradeEntity lg WHERE lg.isDeleted=0";
		@SuppressWarnings("unchecked")
		List<LevelGradeEntity> list = sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}

	@Override
	public Long delete(LevelGradeEntity entity) {
		String hql = "UPDATE LevelGradeEntity SET modifiedBy=?0, modifiedDate=?1, isDeleted=1 WHERE id=?2";
		sessionFactory.getCurrentSession().createQuery(hql)
		.setParameter(0, entity.getModifiedBy())
		.setParameter(1, entity.getModifiedDate())
		.setParameter(2, entity.getId()).executeUpdate();
		return entity.getId();
	}

}
