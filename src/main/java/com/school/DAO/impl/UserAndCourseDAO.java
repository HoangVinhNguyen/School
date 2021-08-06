package com.school.DAO.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.DAO.IUserAndCourseDAO;
import com.school.constant.SystemConstant;
import com.school.entity.CourseEntity;
import com.school.entity.UserAndCourseEntity;
import com.school.entity.UserAndCourseEntity;
import com.school.entity.UserEntity;
import com.school.model.CourseModel;
import com.school.model.UserModel;
import com.school.service.ICourseService;
import com.school.service.IUserService;

@Repository
@Transactional
public class UserAndCourseDAO implements IUserAndCourseDAO{

	@Autowired
	private IUserService userService;
	
	@Autowired 
	private ICourseService courseService;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<UserAndCourseEntity> findAll() {
		String hql = "SELECT c FROM UserAndCourseEntity c WHERE c.isDeleted=0";
		@SuppressWarnings("unchecked")
		List<UserAndCourseEntity> list = sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}

	@Override
	public UserAndCourseEntity findOne(UserEntity user, CourseEntity course) {
		if (user != null && course != null) {
			String hql = "SELECT c FROM UserAndCourseEntity c WHERE c.user=?0 AND c.course=?1 AND c.isDeleted=0";
			List results = sessionFactory.getCurrentSession().createQuery(hql)
					.setParameter(0, user)
					.setParameter(1, course).getResultList();
			if (!results.isEmpty()) {
				return (UserAndCourseEntity) results.get(0);
			}
		}
		return null;
	}

	@Override
	public UserAndCourseEntity findOneByUser(String userEmail) {
		UserModel userCheck = userService.findByEmail(userEmail);
		if (userCheck != null) {
			UserEntity userEntity = new UserEntity();
			userEntity.loadFromDTO(userCheck);
			String hql = "SELECT c FROM UserAndCourseEntity c WHERE c.student=?0 AND c.isDeleted=0";
			return (UserAndCourseEntity) sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, userEntity).getSingleResult();
		}
		return null;
	}
	
	@Override
	public UserAndCourseEntity findOneByUserId(Long id) {
		UserModel model = userService.findOne(id);
		if (model != null) {
			UserEntity userEntity = new UserEntity();
			userEntity.loadFromDTO(model);
			String hql = "SELECT c FROM UserAndCourseEntity c WHERE c.student=?0 AND c.isDeleted=0";
			List results = sessionFactory.getCurrentSession().createQuery(hql)
					.setParameter(0, userEntity).getResultList();
			if (!results.isEmpty()) {
				return (UserAndCourseEntity) results.get(0);
			}
		}
		return null;
	}
	
	@Override
	public List<UserAndCourseEntity> findOneByCourseId(long id) {
		CourseModel model = courseService.findOne(id);
		if (model != null) {
			CourseEntity entity = new CourseEntity();
			entity.loadFromDTO(model);
			String hql = "SELECT c FROM UserAndCourseEntity WHERE c.course=?0 AND c.isDeleted=0";
			List list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, entity).getResultList();
			if (!list.isEmpty()) {
				return list;
			}
		}
		return null;
	}

	@Override
	public int getTotalItem() {
		String hql = "SELECT c FROM UserAndCourseEntity c WHERE c.isDeleted=0";
		return (int) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}

	@Override
	public Long save(UserAndCourseEntity entity) {
		if (entity.getUser().getId() != null && entity.getCourse().getId() != null) {
			if (entity.getOldCourse().getId() != null) {
				UserAndCourseEntity inClassroomModelCheck =  findOne(entity.getUser(), entity.getOldCourse());
				if (inClassroomModelCheck != null ) {
					UserModel user = userService.findOne(entity.getUser().getId());
					CourseModel course = courseService.findOne(entity.getCourse().getId());
					CourseModel oldCourse = courseService.findOne(entity.getOldCourse().getId());
					if (user != null && course != null && oldCourse != null) {
						String hql = "UPDATE UserAndCourseEntity SET course=?0, modifiedBy=?1, modifiedDate=?2 WHERE user=?3 AND course=?4";
						sessionFactory.getCurrentSession().createQuery(hql)
						.setParameter(0, entity.getCourse())
						.setParameter(1, entity.getModifiedBy())
						.setParameter(2, entity.getModifiedDate())
						.setParameter(3, entity.getUser())
						.setParameter(4, entity.getOldCourse()).executeUpdate();
						return entity.getUser().getId();
					}
					return SystemConstant.ERROR;
				}
			}
			else {
				UserModel user = userService.findOne(entity.getUser().getId());
				CourseModel course = courseService.findOne(entity.getCourse().getId());
				if (user != null && course != null) {
					sessionFactory.getCurrentSession().save(entity);
					return entity.getUser().getId();
				}
			}
		}
		return SystemConstant.ERROR;
	}

	@Override
	public Long delete(UserAndCourseEntity entity) {
		UserModel user = userService.findOne(entity.getUser().getId());
		CourseModel course = courseService.findOne(entity.getCourse().getId());
		if (user != null && course != null) {
			String hql = "UPDATE UserAndCourseEntity SET modifiedBy=?0, modifiedDate=?1, isDeleted=1 WHERE user=?2 AND course=?3";
			sessionFactory.getCurrentSession().createQuery(hql)
			.setParameter(0, entity.getModifiedBy())
			.setParameter(1, entity.getModifiedDate())
			.setParameter(2, entity.getUser())
			.setParameter(3, entity.getCourse()).executeUpdate();
			return entity.getId();
		}
		return SystemConstant.ERROR;
	}

}
