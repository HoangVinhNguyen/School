package com.school.DAO.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.DAO.IPointDAO;
import com.school.constant.SystemConstant;
import com.school.entity.ClazzEntity;
import com.school.entity.CourseEntity;
import com.school.entity.PointEntity;
import com.school.entity.UserEntity;
import com.school.service.IClassroomService;
import com.school.service.IClazzService;
import com.school.service.ICourseService;
import com.school.service.IInClassroomService;
import com.school.service.IUserService;

@Repository
@Transactional
public class PointDAO implements IPointDAO{

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IClassroomService classroomService;
	
	@Autowired
	private IInClassroomService inClassroomService;
	
	@Autowired
	private IClazzService clazzService;
	
	@Autowired
	private ICourseService courseService;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<PointEntity> findAll() {
		String hql = "SELECT tc FROM PointEntity tc WHERE tc.isDeleted=0";
		@SuppressWarnings("unchecked")
		List<PointEntity> list = sessionFactory.getCurrentSession().createQuery(hql).getResultList();
		if (!list.isEmpty()) {
			return list;
		}
		return null;
	}

	@Override
	public PointEntity findOne(long id) {
		String hql = "SELECT tc FROM PointEntity tc WHERE tc.id=?0 AND tc.isDeleted=0";
		List results = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, id).getResultList();
		if (!results.isEmpty()) {
			return (PointEntity) results.get(0);
		}
		return null;
	}
	
	@Override
	public PointEntity findOneByUserCourseClazz(UserEntity teacher, UserEntity student, CourseEntity course,
			ClazzEntity clazz) {
		String hql = "SELECT p FROM PointEntity p WHERE p.teacher=?0 AND p.student=?1 "
				+ "AND p.course=?2 AND p.clazz=?3 AND p.isDeleted = 0";
		List list = sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, teacher)
				.setParameter(1, student)
				.setParameter(2, course)
				.setParameter(3, clazz).getResultList();
		if (!list.isEmpty()) {
			return (PointEntity) list.get(0);
		}
		return null;
	}

	@Override
	public List<PointEntity> findAllByCourse(CourseEntity course) {
		if (course != null) {
			String hql = "SELECT tc FROM PointEntity tc WHERE tc.course=?0 AND tc.isDeleted = 0";
			List list = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, course).getResultList();
			if (!list.isEmpty()) {
				return list;
			}
		}
		return null;
	}
	
	@Override
	public List<PointEntity> findAllByClazz(ClazzEntity clazz) {
		if (clazz != null) {
			String hql = "SELECT tc FROM PointEntity tc WHERE tc.clazz=?0 AND tc.isDeleted=0";
			List results = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, clazz).getResultList();
			if (!results.isEmpty()) {
				return results;
			}
			return null;
		}
		return null;
	}
	
	@Override
	public List<PointEntity> findAllByTeacher(UserEntity teacher) {
		if (teacher != null) {
			String hql = "SELECT tc FROM PointEntity tc WHERE tc.teacher=? AND tc.isDeleted=0";
			List results = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, teacher).getResultList();
			if (!results.isEmpty()) {
				return results;
			}
			return null;
		}
		return null;
	}
	
	@Override
	public List<PointEntity> findAllByStudent(UserEntity student) {
		if (student != null) {
			String hql = "SELECT tc FROM PointEntity tc WHERE tc.student=? AND tc.isDeleted=0";
			List results = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, student).getResultList();
			if (!results.isEmpty()) {
				return results;
			}
			return null;
		}
		return null;
	}

	@Override
	public int getTotalItem() {
		String hql = "SELECT tc FROM PointEntity tc WHERE tc.isDeleted=0";
		return (int) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}

	@Override
	public Long save(PointEntity entity) {
		if (entity.getId() != null) {
			PointEntity updateEntity = findOne(entity.getId());
			updateEntity.setPoint(entity.getPoint());
			updateEntity.setTeacher(entity.getTeacher());
			updateEntity.setStudent(entity.getStudent());
			updateEntity.setClazz(entity.getClazz());
			updateEntity.setCourse(entity.getCourse());
			updateEntity.setModifiedBy(entity.getModifiedBy());
			updateEntity.setModifiedDate(entity.getModifiedDate());
			PointEntity result = (PointEntity) sessionFactory.getCurrentSession().merge(updateEntity);
			return result.getId();
		}
		sessionFactory.getCurrentSession().persist(entity);
		return entity.getId();
	}

	@Override
	public Long delete(PointEntity entity) {
		if (entity != null && entity.getId() != null) {
			PointEntity updateEntity = findOne(entity.getId());
			updateEntity.setIsDeleted((byte) 1);
			updateEntity.setModifiedBy(entity.getModifiedBy());
			updateEntity.setModifiedDate(entity.getModifiedDate());
			PointEntity result = (PointEntity) sessionFactory.getCurrentSession().merge(updateEntity);
			return result.getId();
		}
		return SystemConstant.ERROR;
	}

	@Override
	public Long savePoint(PointEntity entity) {
		if (entity != null) {
			PointEntity updateEntity = findOne(entity.getId());
			updateEntity.setPoint(entity.getPoint());
			updateEntity.setModifiedBy(entity.getModifiedBy());
			updateEntity.setModifiedDate(entity.getModifiedDate());
			PointEntity result = (PointEntity) sessionFactory.getCurrentSession().merge(updateEntity);
			return result.getId();
		}
		return SystemConstant.ERROR;
	}

}
