package com.school.DAO.impl;

import java.util.List;

import javax.inject.Inject;

import com.school.DAO.IInClassroomDAO;
import com.school.mapper.InClassroomMapper;
import com.school.model.ClassroomModel;
import com.school.model.InClassroomModel;
import com.school.paging.Pageble;
import com.school.service.IClassroomService;
import com.school.service.IUserService;

public class InClassroomDAO extends AbstractDAO<InClassroomModel> implements IInClassroomDAO{

	@Inject
	private IUserService userService;
	
	@Inject 
	private IClassroomService classroomService;
	
	@Override
	public List<InClassroomModel> findAll(Pageble pageble) {
		String sql = "SELECT * FROM in_classroom";
		return query(sql, new InClassroomMapper());
	}

	@Override
	public InClassroomModel findOne(long id) {
		String sql = "SELECT * FROM in_classroom WHERE id = ? AND is_deleted = 0";
		List<InClassroomModel> inClassroom = query(sql, new InClassroomMapper(), id);
		return inClassroom.isEmpty() ? null : inClassroom.get(0);
	}

	@Override
	public InClassroomModel findOneByUser(String userEmail) {
		Long id = userService.findByEmail(userEmail);
		if (id != null) {
			String sql = "SELECT * FROM in_classroom WHERE user_id = ? AND is_deleted = 0";
			List<InClassroomModel> inClassroom = query(sql, new InClassroomMapper(), id);
			return inClassroom.isEmpty() ? null : inClassroom.get(0);
		}
		return null;
	}
	
	@Override
	public List<InClassroomModel> findOneByClass(String className) {
		ClassroomModel classroom = classroomService.findOneByName(className);
		if (classroom != null) {
			String sql = "SELECT * FROM in_classroom WHERE classroom_id = ? AND is_deleted = 0";
			List<InClassroomModel> inClassroom = query(sql, new InClassroomMapper(), classroom.getId());
			return inClassroom.isEmpty() ? null : inClassroom;
		}
		return null;
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM in_classroom WHERE is_deleted = 0";
		return count(sql);
	}

	@Override
	public Long save(InClassroomModel inClassroomModel) {
		InClassroomModel inClassroomModelCheck;
		if (inClassroomModel.getId() != null) {
			inClassroomModelCheck = findOne(inClassroomModel.getId());
			if (inClassroomModelCheck != null && inClassroomModelCheck.getId() != 0) {
				String sql = "UPDATE in_classroom SET user_id=?, classroom_id=?, modified_by=?, modified_date=? WHERE id=?";
				return update(sql, inClassroomModel.getStudentId(), inClassroomModel.getClassroomId(),
						inClassroomModel.getModifiedBy(), inClassroomModel.getModifiedDate(), inClassroomModel.getId());
			}
		}
		String sql = "INSERT INTO in_classroom (user_id, classroom_id, created_by, created_date, modified_date) values (?, ?, ?, ?, ?)";
		return insert(sql, inClassroomModel.getStudentId(), inClassroomModel.getClassroomId(), inClassroomModel.getCreatedBy(), inClassroomModel.getCreatedDate(), inClassroomModel.getModifiedDate());
	}

	@Override
	public List<InClassroomModel> findAll() {
		String sql = "SELECT * FROM in_classroom  WHERE is_deleted = 0";
		return query(sql, new InClassroomMapper());
	}

	@Override
	public Long delete(InClassroomModel inClassroomModel) {
		String sql = "UPDATE in_classroom SET modified_by=?, modified_date=?, is_deleted = 1 WHERE id=?";
		return update(sql, inClassroomModel.getModifiedBy(), inClassroomModel.getModifiedDate(), inClassroomModel.getId());
	}

}
