package com.school.DAO.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.DAO.IInClassroomDAO;
import com.school.entity.ClassroomEntity;
import com.school.entity.InClassroomEntity;
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
	SessionFactory sessionFactory;
	
	@Override
	public List<InClassroomEntity> findAll() {
		return (List<InClassroomEntity>) sessionFactory.getCurrentSession().createCriteria(InClassroomEntity.class);
	}

	@Override
	public InClassroomEntity findOne(long id) {
		String sql = "SELECT * FROM in_classroom WHERE id = ? AND is_deleted = 0";
		return (InClassroomEntity) sessionFactory.getCurrentSession().createQuery(sql).setParameter(0, id);
	}

	@Override
	public InClassroomEntity findOneByUser(String userEmail) {
		Long id = userService.findByEmail(userEmail);
		if (id != null) {
			String sql = "SELECT * FROM in_classroom WHERE user_id = ? AND is_deleted = 0";
			return (InClassroomEntity) sessionFactory.getCurrentSession().createQuery(sql).setParameter(0, id);
		}
		return null;
	}
	
	@Override
	public List<InClassroomEntity> findOneByClass(String className) {
		ClassroomEntity classroom = new ClassroomEntity();
		classroom.loadFromDTO(classroomService.findOneByName(className));
		if (classroom != null) {
			String sql = "SELECT * FROM in_classroom WHERE classroom_id = ? AND is_deleted = 0";
			return (List<InClassroomEntity>) sessionFactory.getCurrentSession().createQuery(sql).setParameter(0, classroom.getId());
		}
		return null;
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM in_classroom WHERE is_deleted = 0";
		return (int) sessionFactory.getCurrentSession().createQuery(sql).uniqueResult();
	}

	@Override
	public Long save(InClassroomEntity inClassroomEntity) {
		InClassroomEntity inClassroomModelCheck;
		if (inClassroomEntity.getId() != null) {
			inClassroomModelCheck = findOne(inClassroomEntity.getId());
			if (inClassroomModelCheck != null && inClassroomModelCheck.getId() != 0) {
				sessionFactory.getCurrentSession().merge(inClassroomEntity);
				return inClassroomEntity.getId();
			}
		}
		sessionFactory.getCurrentSession().save(inClassroomEntity);
		return inClassroomEntity.getId();
	}

	@Override
	public Long delete(InClassroomEntity inClassroomEntity) {
		String sql = "UPDATE in_classroom SET modified_by=?, modified_date=?, is_deleted = 1 WHERE id=?";
		sessionFactory.getCurrentSession().createQuery(sql)
		.setParameter(0, inClassroomEntity.getModifiedBy())
		.setParameter(1, inClassroomEntity.getModifiedDate())
		.setParameter(2, inClassroomEntity.getId());
		return inClassroomEntity.getId();
	}

}
