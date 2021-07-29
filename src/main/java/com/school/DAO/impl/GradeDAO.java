package com.school.DAO.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.DAO.IGradeDAO;
import com.school.constant.SystemConstant;
import com.school.entity.GradeEntity;
import com.school.entity.LevelGradeEntity;

@Repository
@Transactional
public class GradeDAO implements IGradeDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public GradeEntity findOne(long id) {
		String hql = "SELECT g FROM GradeEntity g WHERE g.id=?0 AND g.isDeleted=0";
		List list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, id).getResultList();
		if (!list.isEmpty()) {
			return (GradeEntity) list.get(0);
		}
		return null;
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
	public List<GradeEntity> findAllByLevelGreadId(LevelGradeEntity levelGradeEntity) {
		String hql = "SELECT g FROM GradeEntity g WHERE g.levelGradeEntity=?0 AND g.isDeleted=0";
		List list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, levelGradeEntity).getResultList();
		if (!list.isEmpty()) {
			return (List<GradeEntity>) list;
		}
		return null;
	}
	
	@Override
	public GradeEntity findOneByNameAndCodeAndLevelGreadId(GradeEntity entity) {
		String hql = "SELECT g FROM GradeEntity g WHERE g.code=?0 AND g.name=?1 AND g.levelGradeEntity=?2 AND g.isDeleted = 0";
		List list = sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, entity.getCode())
				.setParameter(1, entity.getName())
				.setParameter(2, entity.getLevelGradeEntity()).getResultList();
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
		GradeEntity gradeModelCheckName;
		GradeEntity gradeModelCheckCode;
		if (entity.getId() != null) {
			gradeModelCheck = findOne(entity.getId());
			if (gradeModelCheck != null && gradeModelCheck.getId() != 0) {
				gradeModelCheckCode = findOneByCode(entity.getCode());
				gradeModelCheckName = findOneByName(entity.getName());
				if (gradeModelCheckCode == null || gradeModelCheckName == null) {
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
				else {
					GradeEntity gradeModelCheckExist = findOneByNameAndCodeAndLevelGreadId(entity);
					if (gradeModelCheckExist == null) {
						String hql = "UPDATE GradeEntity SET levelGradeEntity=?0, modifiedBy=?1, modifiedDate=?2 WHERE id=?3";
						sessionFactory.getCurrentSession().createQuery(hql)
						.setParameter(0, entity.getLevelGradeEntity())
						.setParameter(1, entity.getModifiedBy())
						.setParameter(2, entity.getModifiedDate())
						.setParameter(3, entity.getId()).executeUpdate();
						return entity.getId();
					}
				}
				return SystemConstant.DUPLICATE;
			}
		}
		else {
			gradeModelCheckCode = findOneByCode(entity.getCode());
			gradeModelCheckName = findOneByName(entity.getName());
			if (gradeModelCheckCode == null && gradeModelCheckName == null) {
				sessionFactory.getCurrentSession().save(entity);
				return entity.getId();
			}
			else {
				return SystemConstant.DUPLICATE;
			}
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
