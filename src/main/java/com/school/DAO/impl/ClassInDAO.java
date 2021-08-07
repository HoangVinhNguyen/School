package com.school.DAO.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.DAO.IClassInDAO;
import com.school.constant.SystemConstant;
import com.school.entity.ClazzEntity;
import com.school.entity.GradeEntity;

@Repository
@Transactional
public class ClassInDAO implements IClassInDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public ClazzEntity findOne(long id) {
		String hql = "SELECT g FROM ClazzEntity g WHERE g.id=?0 AND g.isDeleted=0";
		List list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, id).getResultList();
		if (!list.isEmpty()) {
			return (ClazzEntity) list.get(0);
		}
		return null;
	}

	@Override
	public ClazzEntity findOneByCode(String code) {
		String hql = "SELECT g FROM ClazzEntity g WHERE g.code = ?0 AND g.isDeleted = 0";
		List list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, code).getResultList();
		if (!list.isEmpty()) {
			return (ClazzEntity) list.get(0);
		}
		return null;
	}
	
	@Override
	public List<ClazzEntity> findAllByGreadId(GradeEntity gradeEntity) {
		String hql = "SELECT g FROM ClazzEntity g WHERE g.grade=?0 AND g.isDeleted=0";
		List list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, gradeEntity).getResultList();
		if (!list.isEmpty()) {
			return (List<ClazzEntity>) list;
		}
		return null;
	}
	
	@Override
	public ClazzEntity findOneByNameAndCodeAndGreadId(ClazzEntity entity) {
		String hql = "SELECT g FROM ClazzEntity g WHERE g.code=?0 AND g.name=?1 AND g.grade=?2 AND g.isDeleted = 0";
		List list = sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, entity.getCode())
				.setParameter(1, entity.getName())
				.setParameter(2, entity.getGrade()).getResultList();
		if (!list.isEmpty()) {
			return (ClazzEntity) list.get(0);
		}
		return null;
	}
	
	@Override
	public ClazzEntity findOneByName(String name) {
		String hql = "SELECT g FROM ClazzEntity g WHERE g.name = ?0 AND g.isDeleted = 0";
		List list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, name).getResultList();
		if (!list.isEmpty()) {
			return (ClazzEntity) list.get(0);
		}
		return null;
	}

	@Override
	public int getTotalItem() {
		String hql = "SELECT g FROM ClazzEntity g WHERE g.isDeleted = 0";
		return (int) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}

	@Override
	public Long save(ClazzEntity entity) {
		ClazzEntity gradeModelCheck;
		ClazzEntity gradeModelCheckName;
		ClazzEntity gradeModelCheckCode;
		if (entity.getId() != null) {
			gradeModelCheck = findOne(entity.getId());
			if (gradeModelCheck != null && gradeModelCheck.getId() != 0) {
				gradeModelCheckCode = findOneByCode(entity.getCode());
				gradeModelCheckName = findOneByName(entity.getName());
				if (gradeModelCheckCode == null || gradeModelCheckName == null) {
					String hql = "UPDATE ClazzEntity SET name=?0, code=?1, grade=?2, modifiedBy=?3, modifiedDate=?4 WHERE id=?5";
					sessionFactory.getCurrentSession().createQuery(hql)
					.setParameter(0, entity.getName())
					.setParameter(1, entity.getCode())
					.setParameter(2, entity.getGrade())
					.setParameter(3, entity.getModifiedBy())
					.setParameter(4, entity.getModifiedDate())
					.setParameter(5, entity.getId()).executeUpdate();
					return entity.getId();
				}
				else {
					ClazzEntity gradeModelCheckExist = findOneByNameAndCodeAndGreadId(entity);
					if (gradeModelCheckExist == null && (gradeModelCheckCode.getId() == gradeModelCheckName.getId())) {
						String hql = "UPDATE ClazzEntity SET grade=?0, modifiedBy=?1, modifiedDate=?2 WHERE id=?3";
						sessionFactory.getCurrentSession().createQuery(hql)
						.setParameter(0, entity.getGrade())
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
	public List<ClazzEntity> findAll() {
		String hql = "SELECT g FROM ClazzEntity g WHERE g.isDeleted=0";
		@SuppressWarnings("unchecked")
		List<ClazzEntity> list = sessionFactory.getCurrentSession().createQuery(hql).list();
		//list.forEach(c -> c.getListUser().forEach(u -> System.out.println("Email: " + u.getEmail())));
		return list;
	}

	@Override
	public Long delete(ClazzEntity entity) {
		String hql = "UPDATE ClazzEntity SET modifiedBy=?0, modifiedDate=?1, isDeleted=1 WHERE id=?2";
		sessionFactory.getCurrentSession().createQuery(hql)
		.setParameter(0, entity.getModifiedBy())
		.setParameter(1, entity.getModifiedDate())
		.setParameter(2, entity.getId()).executeUpdate();
		return entity.getId();
	}
}
