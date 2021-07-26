package com.school.DAO.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.DAO.ITeacherClassroomDAO;
import com.school.entity.ClassroomEntity;
import com.school.entity.CourseEntity;
import com.school.entity.TeacherClassroomEntity;
import com.school.entity.UserEntity;
import com.school.service.IClassroomService;
import com.school.service.ICourseService;
import com.school.service.IUserService;

@Repository
@Transactional
public class TeacherClassroomDAO implements ITeacherClassroomDAO{

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IClassroomService classroomService;
	
	@Autowired
	private ICourseService courseService;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<TeacherClassroomEntity> findAll() {
		String hql = "SELECT tc FROM TeacherClassroomEntity tc WHERE tc.isDeleted=0";
		@SuppressWarnings("unchecked")
		List<TeacherClassroomEntity> list = sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;
	}

	@Override
	public TeacherClassroomEntity findOne(long id) {
		String hql = "SELECT tc FROM TeacherClassroomEntity tc WHERE tc.id=?0 AND tc.isDeleted=0";
		List results = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, id).getResultList();
		if (!results.isEmpty()) {
			return (TeacherClassroomEntity) results.get(0);
		}
		return null;
	}

	@Override
	public TeacherClassroomEntity findOneByClassroom(String classroomId) {
		String hql = "SELECT tc FROM TeacherClassroomEntity tc WHERE code = ? AND tc.isDeleted = 0";
		List results = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, classroomId).getResultList();
		if (!results.isEmpty()) {
			return (TeacherClassroomEntity) results.get(0);
		}
		return null;
	}
	
	@Override
	public List<TeacherClassroomEntity> findAllByTeacherEmail(String userEmail) {
		Long id = userService.findByEmail(userEmail);
		if (id != null) {
			String hql = "SELECT tc FROM TeacherClassroomEntity tc WHERE tc.teacherId=? AND tc.isDeleted=0";
			List results = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, userEmail).getResultList();
			if (!results.isEmpty()) {
				return results;
			}
			return null;
		}
		return null;
	}
	
	@Override
	public List<TeacherClassroomEntity> findAllByStudentEmail(String userEmail) {
		Long id = userService.findByEmail(userEmail);
		if (id != null) {
			String hql = "SELECT tc FROM TeacherClassroomEntity tc WHERE tc.studentId=? AND tc.isDeleted=0";
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
		String hql = "SELECT tc FROM TeacherClassroomEntity tc WHERE tc.isDeleted=0";
		return (int) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}

	@Override
	public Long save(TeacherClassroomEntity entity) {
		TeacherClassroomEntity teacherClassroomModelCheck;
		ClassroomEntity classroomEntityCheck = new ClassroomEntity();
		UserEntity userTeacherCheck = new UserEntity();
		UserEntity userStudentCheck = new UserEntity();
		CourseEntity courseCheck = new CourseEntity();
		
		classroomEntityCheck.loadFromDTO(classroomService.findOne(entity.getClassroomId()));
		userTeacherCheck.loadFromDTO(userService.findOne(entity.getTeacherId()));
		userStudentCheck.loadFromDTO(userService.findOne(entity.getStudentId()));
		courseCheck.loadFromDTO(courseService.findOne(entity.getCourseId()));
		if (entity.getId() != null) {
			teacherClassroomModelCheck = findOne(entity.getId());
			
			if ((teacherClassroomModelCheck != null && teacherClassroomModelCheck.getId() != 0)
					&& userTeacherCheck != null && userStudentCheck != null && courseCheck != null) {
				String hql = "UPDATE TeacherClassroomEntity "
						+ "SET teacherId=?0, studentId=?1, classroomId=?2, courseId=?3, modifiedBy=?4, modifiedDate=?5 WHERE id=?6";
				sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter(0, entity.getTeacherId())
				.setParameter(1, entity.getStudentId())
				.setParameter(2, entity.getClassroomId())
				.setParameter(3, entity.getCourseId())
				.setParameter(4, entity.getModifiedBy())
				.setParameter(5, entity.getModifiedDate())
				.setParameter(6, entity.getId()).executeUpdate();
				return entity.getId();
			}
		}
		if (userTeacherCheck != null && userStudentCheck != null && courseCheck != null) {
			sessionFactory.getCurrentSession().save(entity);
			return entity.getId();
		}
		return 0L;
	}

	@Override
	public Long delete(TeacherClassroomEntity entity) {
		String hql = "UPDATE TeacherClassroomEntity SET modifiedBy=?0, modifiedDate=?1, isDeleted=1 WHERE id=?2";
		sessionFactory.getCurrentSession().createQuery(hql)
		.setParameter(0, entity.getModifiedBy())
		.setParameter(1, entity.getModifiedDate())
		.setParameter(2, entity.getId()).executeUpdate();
		return entity.getId();
	}

	@Override
	public Long savePoint(TeacherClassroomEntity entity) {
		String hql = "UPDATE TeacherClassroomEntity SET point=?0, modifiedBy=?1, modifiedDate=?2 WHERE id=?3";
		sessionFactory.getCurrentSession().createQuery(hql)
		.setParameter(0, entity.getPoint())
		.setParameter(1, entity.getModifiedBy())
		.setParameter(2, entity.getModifiedDate())
		.setParameter(3, entity.getId());
		return entity.getId();
	}

	@Override
	public List<TeacherClassroomEntity> findAllByClassroom(String className) {
		ClassroomEntity classroom = new ClassroomEntity();
		classroom.loadFromDTO(classroomService.findOneByName(className));
		if (classroom != null) {
			String hql = "SELECT tc FROM TeacherClassroomEntity tc WHERE tc.classroomId=?0 AND tc.isDeleted=0";
			List results = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, classroom.getId()).getResultList();
			if (!results.isEmpty()) {
				return results;
			}
			return null;
		}
		return null;
	}

}
