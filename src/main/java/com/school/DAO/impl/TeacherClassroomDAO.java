package com.school.DAO.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.school.DAO.ITeacherClassroomDAO;
import com.school.entity.ClassroomEntity;
import com.school.entity.TeacherClassroomEntity;
import com.school.service.IClassroomService;
import com.school.service.IUserService;

@Repository
@Transactional
public class TeacherClassroomDAO implements ITeacherClassroomDAO{

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IClassroomService classroomService;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<TeacherClassroomEntity> findAll() {
		return (List<TeacherClassroomEntity>) sessionFactory.getCurrentSession().createCriteria(TeacherClassroomEntity.class);
	}

	@Override
	public TeacherClassroomEntity findOne(long id) {
		String sql = "SELECT * FROM teacher_classroom WHERE id = ? AND is_deleted = 0";
		return (TeacherClassroomEntity) sessionFactory.getCurrentSession().createQuery(sql).setParameter(0, id);
	}

	@Override
	public TeacherClassroomEntity findOneByClassroom(String classroomId) {
		String sql = "SELECT * FROM teacher_classroom WHERE code = ? AND is_deleted = 0";
		return (TeacherClassroomEntity) sessionFactory.getCurrentSession().createQuery(sql).setParameter(0, classroomId);
	}
	
	@Override
	public List<TeacherClassroomEntity> findAllByTeacherEmail(String userEmail) {
		Long id = userService.findByEmail(userEmail);
		if (id != null) {
			String sql = "SELECT * FROM teacher_classroom WHERE teacher_id = ? AND is_deleted = 0";
			return (List<TeacherClassroomEntity>) sessionFactory.getCurrentSession().createQuery(sql).setParameter(0, userEmail);
		}
		return null;
	}
	
	@Override
	public List<TeacherClassroomEntity> findAllByStudentEmail(String userEmail) {
		Long id = userService.findByEmail(userEmail);
		if (id != null) {
			String sql = "SELECT * FROM teacher_classroom WHERE student_id = ? AND is_deleted = 0";
			return (List<TeacherClassroomEntity>) sessionFactory.getCurrentSession().createQuery(sql).setParameter(0, userEmail);
		}
		return null;
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM teacher_classroom WHERE is_deleted = 0";
		return (int) sessionFactory.getCurrentSession().createQuery(sql).uniqueResult();
	}

	@Override
	public Long save(TeacherClassroomEntity teacherClassroomEntity) {
		TeacherClassroomEntity teacherClassroomModelCheck;
		if (teacherClassroomEntity.getId() != null) {
			teacherClassroomModelCheck = findOne(teacherClassroomEntity.getId());
			if (teacherClassroomModelCheck != null && teacherClassroomModelCheck.getId() != 0) {
				sessionFactory.getCurrentSession().merge(teacherClassroomEntity);
				return teacherClassroomEntity.getId();
			}
		}
		sessionFactory.getCurrentSession().save(teacherClassroomEntity);
		return teacherClassroomEntity.getId();	}

	@Override
	public Long delete(TeacherClassroomEntity teacherClassroomEntity) {
		String sql = "UPDATE classroom SET modified_by=?, modified_date=?, is_deleted = 1 WHERE id=?";
		sessionFactory.getCurrentSession().createQuery(sql)
		.setParameter(0, teacherClassroomEntity.getModifiedBy())
		.setParameter(1, teacherClassroomEntity.getModifiedDate())
		.setParameter(2, teacherClassroomEntity.getId());
		return teacherClassroomEntity.getId();
	}

	@Override
	public Long savePoint(TeacherClassroomEntity teacherClassroomEntity) {
		String sql = "UPDATE teacher_classroom SET point=?, modified_by=?, modified_date=? WHERE id=?";
		sessionFactory.getCurrentSession().createQuery(sql)
		.setParameter(0, teacherClassroomEntity.getPoint())
		.setParameter(1, teacherClassroomEntity.getModifiedBy())
		.setParameter(2, teacherClassroomEntity.getModifiedDate())
		.setParameter(3, teacherClassroomEntity.getId());
		return teacherClassroomEntity.getId();
	}

	@Override
	public List<TeacherClassroomEntity> findAllByClassroom(String className) {
		ClassroomEntity classroom = new ClassroomEntity();
		classroom.loadFromDTO(classroomService.findOneByName(className));
		if (classroom != null) {
			String sql = "SELECT * FROM teacher_classroom WHERE classroom_id = ? AND is_deleted = 0";
			return (List<TeacherClassroomEntity>) sessionFactory.getCurrentSession().createQuery(sql).setParameter(0, classroom.getId());
		}
		return null;
	}

}
