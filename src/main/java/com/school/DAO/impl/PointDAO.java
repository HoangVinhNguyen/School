package com.school.DAO.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.DAO.IPointDAO;
import com.school.entity.ClassroomEntity;
import com.school.entity.CourseEntity;
import com.school.entity.InClassroomEntity;
import com.school.entity.PointEntity;
import com.school.entity.UserEntity;
import com.school.service.IClassroomService;
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
	private ICourseService courseService;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<PointEntity> findAll() {
		String hql = "SELECT tc FROM PointEntity tc WHERE tc.isDeleted=0";
		@SuppressWarnings("unchecked")
		List<PointEntity> list = sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
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
	public PointEntity findOneByClassroom(String classroomId) {
		String hql = "SELECT tc FROM PointEntity tc WHERE code = ? AND tc.isDeleted = 0";
		List results = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, classroomId).getResultList();
		if (!results.isEmpty()) {
			return (PointEntity) results.get(0);
		}
		return null;
	}
	
	@Override
	public List<PointEntity> findAllByTeacherEmail(String userEmail) {
		Long id = userService.findByEmail(userEmail);
		if (id != null) {
			String hql = "SELECT tc FROM PointEntity tc WHERE tc.teacherId=? AND tc.isDeleted=0";
			List results = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, userEmail).getResultList();
			if (!results.isEmpty()) {
				return results;
			}
			return null;
		}
		return null;
	}
	
	@Override
	public List<PointEntity> findAllByStudentEmail(String userEmail) {
		Long id = userService.findByEmail(userEmail);
		if (id != null) {
			String hql = "SELECT tc FROM PointEntity tc WHERE tc.studentId=? AND tc.isDeleted=0";
			List results = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, userEmail).getResultList();
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
		PointEntity pointCheck;
		ClassroomEntity classroomEntityCheck = new ClassroomEntity();
		UserEntity userTeacherCheck = new UserEntity();
		UserEntity userStudentCheck = new UserEntity();
		CourseEntity courseCheck = new CourseEntity();
		InClassroomEntity teacherInClass = new InClassroomEntity();
		InClassroomEntity studentInClass = new InClassroomEntity();
		
		classroomEntityCheck.loadFromDTO(classroomService.findOne(entity.getClassroomEntity().getId()));
		userTeacherCheck.loadFromDTO(userService.findOne(entity.getTeacherEntity().getId()));
		userStudentCheck.loadFromDTO(userService.findOne(entity.getStudentEntity().getId()));
		courseCheck.loadFromDTO(courseService.findOne(entity.getCourseEntity().getId()));
		if (entity.getId() != null) {
			pointCheck = findOne(entity.getId());
			if ((pointCheck != null && pointCheck.getId() != 0)
					&& userTeacherCheck != null && userStudentCheck != null && courseCheck != null) {
				teacherInClass.loadFromDTO(inClassroomService.findOneByUser(userTeacherCheck.getEmail()));
				studentInClass.loadFromDTO(inClassroomService.findOneByUser(userStudentCheck.getEmail()));
				if (teacherInClass != null && studentInClass != null) {
					if (teacherInClass.getClassroom().getId() == studentInClass.getClassroom().getId()) {
						String hql = "UPDATE PointEntity "
								+ "SET teacherEntity=?0, studentEntity=?1, classroomEntity=?2, courseEntity=?3, modifiedBy=?4, modifiedDate=?5 WHERE id=?6";
						sessionFactory.getCurrentSession().createQuery(hql)
						.setParameter(0, entity.getTeacherEntity())
						.setParameter(1, entity.getStudentEntity())
						.setParameter(2, entity.getClassroomEntity())
						.setParameter(3, entity.getCourseEntity())
						.setParameter(4, entity.getModifiedBy())
						.setParameter(5, entity.getModifiedDate())
						.setParameter(6, entity.getId()).executeUpdate();
						return entity.getId();
					}
					else {
						sessionFactory.getCurrentSession().beginTransaction().rollback();
						return 0L;
					}
				}
				else {
					sessionFactory.getCurrentSession().beginTransaction().rollback();
					return 0L;
				}
				
			}
			else {
				sessionFactory.getCurrentSession().beginTransaction().rollback();
				return 0L;
			}
		}
		if (userTeacherCheck != null && userStudentCheck != null && courseCheck != null) {
			sessionFactory.getCurrentSession().save(entity);
			return entity.getId();
		}
		sessionFactory.getCurrentSession().beginTransaction().rollback();
		return 0L;
	}

	@Override
	public Long delete(PointEntity entity) {
		String hql = "UPDATE PointEntity SET modifiedBy=?0, modifiedDate=?1, isDeleted=1 WHERE id=?2";
		sessionFactory.getCurrentSession().createQuery(hql)
		.setParameter(0, entity.getModifiedBy())
		.setParameter(1, entity.getModifiedDate())
		.setParameter(2, entity.getId()).executeUpdate();
		return entity.getId();
	}

	@Override
	public Long savePoint(PointEntity entity) {
		String hql = "UPDATE PointEntity SET point=?0, modifiedBy=?1, modifiedDate=?2 WHERE id=?3";
		sessionFactory.getCurrentSession().createQuery(hql)
		.setParameter(0, entity.getPoint())
		.setParameter(1, entity.getModifiedBy())
		.setParameter(2, entity.getModifiedDate())
		.setParameter(3, entity.getId());
		return entity.getId();
	}

	@Override
	public List<PointEntity> findAllByClassroom(String className) {
		ClassroomEntity classroom = new ClassroomEntity();
		classroom.loadFromDTO(classroomService.findOneByName(className));
		if (classroom != null) {
			String hql = "SELECT tc FROM PointEntity tc WHERE tc.classroomId=?0 AND tc.isDeleted=0";
			List results = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, classroom.getId()).getResultList();
			if (!results.isEmpty()) {
				return results;
			}
			return null;
		}
		return null;
	}

}
