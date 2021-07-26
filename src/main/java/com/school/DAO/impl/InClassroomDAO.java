package com.school.DAO.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.DAO.IInClassroomDAO;
import com.school.entity.ClassroomEntity;
import com.school.entity.InClassroomEntity;
import com.school.model.UserModel;
import com.school.service.IClassroomService;
import com.school.service.IUserService;

@Repository
@Transactional
public class InClassroomDAO implements IInClassroomDAO{

	@Autowired
	private IUserService userService;
	
	@Autowired 
	private IClassroomService classroomService;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<InClassroomEntity> findAll() {
		String hql = "SELECT c FROM InClassroomEntity c WHERE c.isDeleted=0";
		@SuppressWarnings("unchecked")
		List<InClassroomEntity> list = sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}

	@Override
	public InClassroomEntity findOne(long id) {
		if (id != 0) {
			String hql = "SELECT c FROM InClassroomEntity c WHERE c.id=?0 AND c.isDeleted=0";
			List results = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, id).getResultList();
			if (!results.isEmpty()) {
				return (InClassroomEntity) results.get(0);
			}
		}
		return null;
	}

	@Override
	public InClassroomEntity findOneByUser(String userEmail) {
		Long id = userService.findByEmail(userEmail);
		if (id != null) {
			String hql = "SELECT c FROM InClassroomEntity c WHERE c.studentId=?0 AND c.isDeleted=0";
			return (InClassroomEntity) sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, id).getSingleResult();
		}
		return null;
	}
	
	@Override
	public InClassroomEntity findOneByUserId(Long id) {
		UserModel user = userService.findOne(id);
		if (user != null) {
			String hql = "SELECT c FROM InClassroomEntity c WHERE c.studentId=?0 AND c.isDeleted=0";
			List results = sessionFactory.getCurrentSession().createQuery(hql)
					.setParameter(0, user.getId()).getResultList();
			if (!results.isEmpty()) {
				return (InClassroomEntity) results.get(0);
			}
		}
		return null;
	}
	
	@Override
	public List<InClassroomEntity> findOneByClass(String className) {
		ClassroomEntity classroom = new ClassroomEntity();
		classroom.loadFromDTO(classroomService.findOneByName(className));
		if (classroom != null) {
			String hql = "SELECT c FROM InClassroomEntity WHERE c.classroomId=?0 AND c.isDeleted=0";
			return (List<InClassroomEntity>) sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, classroom.getId()).list();
		}
		return null;
	}

	@Override
	public int getTotalItem() {
		String hql = "SELECT c FROM InClassroomEntity c WHERE c.isDeleted=0";
		return (int) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}

	@Override
	public Long save(InClassroomEntity entity) {
		if (entity.getId() != null) {
			InClassroomEntity inClassroomModelCheck =  findOne(entity.getId());
			if ((inClassroomModelCheck != null && inClassroomModelCheck.getId() != 0)) {
				String hql = "UPDATE InClassroomEntity SET classroomId=?0, modifiedBy=?1, modifiedDate=?2 WHERE id=?3";
				sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, entity.getClassroomId())
				.setParameter(1, entity.getModifiedBy())
				.setParameter(2, entity.getModifiedDate())
				.setParameter(3, entity.getId()).executeUpdate();
				return entity.getId();
			}
		}
		InClassroomEntity inClassroomModelCheck2 = findOneByUserId(entity.getStudentId());
		if (inClassroomModelCheck2 == null || inClassroomModelCheck2.getId() == 0) {
			sessionFactory.getCurrentSession().save(entity);
			return entity.getId();
		}
		return 0L;
	}

	@Override
	public Long delete(InClassroomEntity entity) {
		String hql = "UPDATE InClassroomEntity SET modifiedBy=?0, modifiedDate=?1, isDeleted=1 WHERE id=?2";
		sessionFactory.getCurrentSession().createQuery(hql)
		.setParameter(0, entity.getModifiedBy())
		.setParameter(1, entity.getModifiedDate())
		.setParameter(2, entity.getId()).executeUpdate();
		return entity.getId();
	}

}
