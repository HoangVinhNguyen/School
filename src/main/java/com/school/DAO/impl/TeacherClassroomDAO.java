package com.school.DAO.impl;

import java.util.List;

import javax.inject.Inject;

import com.school.DAO.ITeacherClassroomDAO;
import com.school.mapper.InClassroomMapper;
import com.school.mapper.TeacherClassroomMapper;
import com.school.model.ClassroomModel;
import com.school.model.InClassroomModel;
import com.school.model.TeacherClassroomModel;
import com.school.paging.Pageble;
import com.school.service.IClassroomService;
import com.school.service.IUserService;

public class TeacherClassroomDAO extends AbstractDAO<TeacherClassroomModel> implements ITeacherClassroomDAO{

	@Inject
	private IUserService userService;
	
	@Inject
	private IClassroomService classroomService;
	
	@Override
	public List<TeacherClassroomModel> findAll(Pageble pageble) {
		String sql = "SELECT * FROM teacher_classroom";
		return query(sql, new TeacherClassroomMapper());
	}

	@Override
	public TeacherClassroomModel findOne(long id) {
		String sql = "SELECT * FROM teacher_classroom WHERE id = ? AND is_deleted = 0";
		List<TeacherClassroomModel> teacherClassroomModel = query(sql, new TeacherClassroomMapper(), id);
		return teacherClassroomModel.isEmpty() ? null : teacherClassroomModel.get(0);
	}

	@Override
	public TeacherClassroomModel findOneByClassroom(String classroomId) {
		String sql = "SELECT * FROM teacher_classroom WHERE code = ? AND is_deleted = 0";
		List<TeacherClassroomModel> TeacherClassroomModel = query(sql, new TeacherClassroomMapper(), classroomId);
		return TeacherClassroomModel.isEmpty() ? null : TeacherClassroomModel.get(0);
	}
	
	@Override
	public List<TeacherClassroomModel> findAllByTeacherEmail(String userEmail) {
		Long id = userService.findByEmail(userEmail);
		if (id != null) {
			String sql = "SELECT * FROM teacher_classroom WHERE teacher_id = ? AND is_deleted = 0";
			List<TeacherClassroomModel> teacherClassroomModel = query(sql, new TeacherClassroomMapper(), id);
			return teacherClassroomModel.isEmpty() ? null : teacherClassroomModel;
		}
		return null;
	}
	
	@Override
	public List<TeacherClassroomModel> findAllByStudentEmail(String userEmail) {
		Long id = userService.findByEmail(userEmail);
		if (id != null) {
			String sql = "SELECT * FROM teacher_classroom WHERE student_id = ? AND is_deleted = 0";
			List<TeacherClassroomModel> teacherClassroomModel = query(sql, new TeacherClassroomMapper(), id);
			return teacherClassroomModel.isEmpty() ? null : teacherClassroomModel;
		}
		return null;
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM teacher_classroom WHERE is_deleted = 0";
		return count(sql);
	}

	@Override
	public Long save(TeacherClassroomModel teacherClassroomModel) {
		TeacherClassroomModel teacherClassroomModelCheck;
		if (teacherClassroomModel.getId() != null) {
			teacherClassroomModelCheck = findOne(teacherClassroomModel.getId());
			if (teacherClassroomModelCheck != null && teacherClassroomModelCheck.getId() != 0) {
				String sql = "UPDATE teacher_classroom SET teacher_id=?, student_id=?, classroom_id=?, course_id=?, modified_by=?, modified_date=? WHERE id=?";
				return update(sql, teacherClassroomModel.getTeacherId(), teacherClassroomModel.getStudentId(), teacherClassroomModel.getClassroomId(), teacherClassroomModel.getCourseId(),
						teacherClassroomModel.getModifiedBy(), teacherClassroomModel.getModifiedDate(), teacherClassroomModel.getId());
			}
		}
		String sql = "INSERT INTO teacher_classroom (teacher_id, student_id, classroom_id, course_id, created_by, created_date, modified_date) values (?, ?, ?, ?, ?, ?, ?)";
		return insert(sql, teacherClassroomModel.getTeacherId(), teacherClassroomModel.getStudentId(), teacherClassroomModel.getClassroomId(), teacherClassroomModel.getCourseId(),
				teacherClassroomModel.getCreatedBy(), teacherClassroomModel.getCreatedDate(), teacherClassroomModel.getModifiedDate());
	}

	@Override
	public List<TeacherClassroomModel> findAll() {
		String sql = "SELECT * FROM teacher_classroom  WHERE is_deleted = 0";
		return query(sql, new TeacherClassroomMapper());
	}

	@Override
	public Long delete(TeacherClassroomModel teacherClassroomModel) {
		String sql = "UPDATE classroom SET modified_by=?, modified_date=?, is_deleted = 1 WHERE id=?";
		return update(sql, teacherClassroomModel.getModifiedBy(), teacherClassroomModel.getModifiedDate(), teacherClassroomModel.getId());
	}

	@Override
	public Long savePoint(TeacherClassroomModel teacherClassroomModel) {
		String sql = "UPDATE teacher_classroom SET point=?, modified_by=?, modified_date=? WHERE id=?";
		return update(sql, teacherClassroomModel.getPoint(), teacherClassroomModel.getModifiedBy(), teacherClassroomModel.getModifiedDate(), teacherClassroomModel.getId());
	}

	@Override
	public List<TeacherClassroomModel> findAllByClassroom(String className) {
		ClassroomModel classroom = classroomService.findOneByName(className);
		if (classroom != null) {
			String sql = "SELECT * FROM teacher_classroom WHERE classroom_id = ? AND is_deleted = 0";
			List<TeacherClassroomModel> teacherClassroomModel = query(sql, new TeacherClassroomMapper(), classroom.getId());
			return teacherClassroomModel.isEmpty() ? null : teacherClassroomModel;
		}
		return null;
	}

}
