package com.school.DAO.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.DAO.IInCourseDAO;
import com.school.entity.CourseEntity;
import com.school.entity.InCourseEntity;
import com.school.entity.UserEntity;
import com.school.model.CourseModel;
import com.school.model.UserModel;
import com.school.service.ICourseService;
import com.school.service.IUserService;

@Repository
@Transactional
public class InCourseDAO implements IInCourseDAO{

	@Autowired
	private IUserService userService;
	
	@Autowired 
	private ICourseService courseService;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<InCourseEntity> findAll() {
		String hql = "SELECT c FROM InCourseEntity c WHERE c.isDeleted=0";
		@SuppressWarnings("unchecked")
		List<InCourseEntity> list = sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}

	@Override
	public InCourseEntity findOne(long id) {
		if (id != 0) {
			String hql = "SELECT c FROM InCourseEntity c WHERE c.id=?0 AND c.isDeleted=0";
			List results = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, id).getResultList();
			if (!results.isEmpty()) {
				return (InCourseEntity) results.get(0);
			}
		}
		return null;
	}

	@Override
	public InCourseEntity findOneByUser(String userEmail) {
		UserModel userCheck = userService.findByEmail(userEmail);
		if (userCheck != null) {
			UserEntity userEntity = new UserEntity();
			userEntity.loadFromDTO(userCheck);
			String hql = "SELECT c FROM InCourseEntity c WHERE c.student=?0 AND c.isDeleted=0";
			return (InCourseEntity) sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, userEntity).getSingleResult();
		}
		return null;
	}
	
	@Override
	public InCourseEntity findOneByUserId(Long id) {
		UserModel model = userService.findOne(id);
		if (model != null) {
			UserEntity userEntity = new UserEntity();
			userEntity.loadFromDTO(model);
			String hql = "SELECT c FROM InCourseEntity c WHERE c.student=?0 AND c.isDeleted=0";
			List results = sessionFactory.getCurrentSession().createQuery(hql)
					.setParameter(0, userEntity).getResultList();
			if (!results.isEmpty()) {
				return (InCourseEntity) results.get(0);
			}
		}
		return null;
	}
	
	@Override
	public List<InCourseEntity> findOneByCourse(String courseName) {
		CourseModel model = courseService.findOneByName(courseName);
		if (model != null) {
			CourseEntity entity = new CourseEntity();
			entity.loadFromDTO(model);
			String hql = "SELECT c FROM InCourseEntity WHERE c.course=?0 AND c.isDeleted=0";
			return (List<InCourseEntity>) sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, entity).list();
		}
		return null;
	}

	@Override
	public int getTotalItem() {
		String hql = "SELECT c FROM InCourseEntity c WHERE c.isDeleted=0";
		return (int) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}

	@Override
	public Long save(InCourseEntity entity) {
		if (entity.getId() != null) {
			InCourseEntity inClassroomModelCheck =  findOne(entity.getId());
			if ((inClassroomModelCheck != null && inClassroomModelCheck.getId() != 0)) {
				String hql = "UPDATE InCourseEntity SET course=?0, modifiedBy=?1, modifiedDate=?2 WHERE id=?3";
				sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, entity.getCourse())
				.setParameter(1, entity.getModifiedBy())
				.setParameter(2, entity.getModifiedDate())
				.setParameter(3, entity.getId()).executeUpdate();
				return entity.getId();
			}
		}
		InCourseEntity inClassroomModelCheck2 = findOneByUserId(entity.getUser().getId());
		if (inClassroomModelCheck2 == null || inClassroomModelCheck2.getId() == 0) {
			sessionFactory.getCurrentSession().save(entity);
			return entity.getId();
		}
		return 0L;
	}

	@Override
	public Long delete(InCourseEntity entity) {
		String hql = "UPDATE InCourseEntity SET modifiedBy=?0, modifiedDate=?1, isDeleted=1 WHERE id=?2";
		sessionFactory.getCurrentSession().createQuery(hql)
		.setParameter(0, entity.getModifiedBy())
		.setParameter(1, entity.getModifiedDate())
		.setParameter(2, entity.getId()).executeUpdate();
		return entity.getId();
	}

}
