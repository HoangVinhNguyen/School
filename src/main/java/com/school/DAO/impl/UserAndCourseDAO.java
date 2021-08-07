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
	public UserAndCourseEntity findOneByID(long id) {
		String hql = "SELECT c FROM UserAndCourseEntity c WHERE c.id=?0 AND c.isDeleted=0";
		List list = sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, id).getResultList();
		if (!list.isEmpty()) {
			return (UserAndCourseEntity) list.get(0);
		}
		return null;
	}

	@Override
	public List<UserAndCourseEntity> findOneByUserEmail(String userEmail) {
		UserModel userCheck = userService.findByEmail(userEmail);
		if (userCheck != null) {
			UserEntity userEntity = new UserEntity();
			userEntity.loadFromDTO(userCheck);
			String hql = "SELECT c FROM UserAndCourseEntity c WHERE c.user=?0 AND c.isDeleted=0";
			List list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, userEntity).getResultList();
			if (!list.isEmpty()) {
				return list;
			}
		}
		return null;
	}
	
	@Override
	public List<UserAndCourseEntity> findOneByCourseName(String name) {
		CourseModel model = courseService.findOneByName(name);
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
		if (entity.getId() != null) {
			UserAndCourseEntity userAndCourseEntity = findOneByID(entity.getId());
			if (userAndCourseEntity != null) {
				UserModel user = userService.findOne(entity.getUser().getId());
				CourseModel course = courseService.findOne(entity.getCourse().getId());
				if (user != null && course != null) {
					userAndCourseEntity.setCourse(entity.getCourse());
					userAndCourseEntity.setUser(entity.getUser());
					userAndCourseEntity.setModifiedBy(entity.getModifiedBy());
					userAndCourseEntity.setModifiedDate(entity.getModifiedDate());
					UserAndCourseEntity result = (UserAndCourseEntity) sessionFactory.getCurrentSession().merge(userAndCourseEntity);
					return result.getId();
				}
				return SystemConstant.ERROR;
			}
		}
		else {
			UserModel user = userService.findOne(entity.getUser().getId());
			CourseModel course = courseService.findOne(entity.getCourse().getId());
			if (user != null && course != null) {
				UserEntity userEntity = new UserEntity();
				CourseEntity courseEntity = new CourseEntity();
				userEntity.loadFromDTO(user);
				courseEntity.loadFromDTO(course);
				UserAndCourseEntity userAndCourseEntity = findOne(userEntity, courseEntity); // Check duplicate.
				if (userAndCourseEntity == null) {
					sessionFactory.getCurrentSession().persist(entity);
					return entity.getUser().getId();
				}
				return SystemConstant.DUPLICATE;
			}
		}
		return SystemConstant.ERROR;
	}

	@Override
	public Long delete(UserAndCourseEntity entity) {
		if (entity.getId() != null) {
			UserAndCourseEntity userAndCourseEntity = findOneByID(entity.getId());
			if (userAndCourseEntity != null) {
				userAndCourseEntity.setIsDeleted((byte) 1);
				userAndCourseEntity.setModifiedBy(entity.getModifiedBy());
				userAndCourseEntity.setModifiedDate(entity.getModifiedDate());
				UserAndCourseEntity result = (UserAndCourseEntity) sessionFactory.getCurrentSession().merge(userAndCourseEntity);
				return result.getId();
			}
		}
		return SystemConstant.ERROR;
	}
}
