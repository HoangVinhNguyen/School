package com.school.DAO.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.DAO.IClassroomDAO;
import com.school.constant.SystemConstant;
import com.school.entity.ClassroomEntity;
import com.school.entity.GradeEntity;
import com.school.service.IGradeService;

@Repository
@Transactional
public class ClassroomDAO implements IClassroomDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public ClassroomEntity findOne(long id) {
		return (ClassroomEntity) sessionFactory.getCurrentSession().get(ClassroomEntity.class, id);
	}

	@Override
	public ClassroomEntity findOneByCode(String code) {
		String hql = "SELECT c FROM ClassroomEntity c WHERE c.code = ?0 AND c.isDeleted = 0";
		List list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, code).getResultList();
		if (!list.isEmpty()) {
			return (ClassroomEntity) list.get(0);
		}
		return null;
	}
	
	@Override
	public ClassroomEntity findOneByName(String name) {
		String hql = "SELECT c FROM ClassroomEntity c WHERE c.name = ?0 AND c.isDeleted = 0";
		List list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, name).getResultList();
		if (!list.isEmpty()) {
			return (ClassroomEntity) list.get(0);
		}
		return null;
	}
	
	@Override
	public ClassroomEntity findOneByNameAndCodeAndGreadId(ClassroomEntity entity) {
		String hql = "SELECT c FROM ClassroomEntity c WHERE c.code=?0 AND c.name=?1 AND g.isDeleted = 0";
		List list = sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, entity.getCode())
				.setParameter(1, entity.getName()).getResultList();
		if (!list.isEmpty()) {
			return (ClassroomEntity) list.get(0);
		}
		return null;
	}

	@Override
	public int getTotalItem() {
		String hql = "SELECT c FROM ClassroomEntity c WHERE c.isDeleted = 0";
		return (int) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}

	@Override
	public Long save(ClassroomEntity entity) {
		ClassroomEntity classroomModelCheck;
		ClassroomEntity classroomEntityCheckName;
		ClassroomEntity classroomEntityCheckCode;
		if (entity.getId() != null) {
			classroomModelCheck = findOne(entity.getId());
			if (classroomModelCheck != null && classroomModelCheck.getId() != 0) {
				classroomEntityCheckCode = findOneByCode(entity.getCode());
				classroomEntityCheckName = findOneByName(entity.getName());
				if (classroomEntityCheckCode == null || classroomEntityCheckName == null) {
					String hql = "UPDATE ClassroomEntity SET name=?0, code=?1, modifiedBy=?2, modifiedDate=?3 WHERE id=?4";
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
			classroomEntityCheckCode = findOneByCode(entity.getCode());
			classroomEntityCheckName = findOneByName(entity.getName());
			if (classroomEntityCheckCode == null && classroomEntityCheckName == null) {
				sessionFactory.getCurrentSession().save(entity);
				return entity.getId();
			} else {
				return SystemConstant.DUPLICATE;
			}
		}
		return SystemConstant.ERROR;
	}

	@Override
	public List<ClassroomEntity> findAll() {
		String hql = "SELECT cr FROM ClassroomEntity cr WHERE cr.isDeleted=0";
		@SuppressWarnings("unchecked")
		List<ClassroomEntity> list = sessionFactory.getCurrentSession().createQuery(hql).list();
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
	public Long delete(ClassroomEntity entity) {
		String hql = "UPDATE ClassroomEntity SET modifiedBy=?0, modifiedDate=?1, isDeleted=1 WHERE id=?2";
		sessionFactory.getCurrentSession().createQuery(hql)
		.setParameter(0, entity.getModifiedBy())
		.setParameter(1, entity.getModifiedDate())
		.setParameter(2, entity.getId()).executeUpdate();
		return entity.getId();
	}

	@Override
	public List<ClassroomEntity> findByGrade(GradeEntity gradeEntity) {
		String hql = "SELECT cr FROM ClassroomEntity cr WHERE cr.grade=?0 AND cr.isDeleted=0";
		List list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, gradeEntity).getResultList();
		if (!list.isEmpty()) {
			return list;
		}
		return null;
	}

}
