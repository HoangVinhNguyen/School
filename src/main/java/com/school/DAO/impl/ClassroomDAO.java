package com.school.DAO.impl;

import java.util.List;

import com.school.DAO.IClassroomDAO;
import com.school.mapper.ClassroomMapper;
import com.school.model.ClassroomModel;
import com.school.paging.Pageble;

public class ClassroomDAO extends AbstractDAO<ClassroomModel> implements IClassroomDAO{

	@Override
	public List<ClassroomModel> findAll(Pageble pageble) {
		String sql = "SELECT * FROM classroom";
		return query(sql, new ClassroomMapper());
	}

	@Override
	public ClassroomModel findOne(long id) {
		String sql = "SELECT * FROM classroom WHERE id = ? AND is_deleted = 0";
		List<ClassroomModel> classroom = query(sql, new ClassroomMapper(), id);
		return classroom.isEmpty() ? null : classroom.get(0);
	}

	@Override
	public ClassroomModel findOneByCode(String code) {
		String sql = "SELECT * FROM classroom WHERE code = ? AND is_deleted = 0";
		List<ClassroomModel> classroom = query(sql, new ClassroomMapper(), code);
		return classroom.isEmpty() ? null : classroom.get(0);
	}
	
	@Override
	public ClassroomModel findOneByName(String name) {
		String sql = "SELECT * FROM classroom WHERE name = ? AND is_deleted = 0";
		List<ClassroomModel> classroom = query(sql, new ClassroomMapper(), name);
		return classroom.isEmpty() ? null : classroom.get(0);
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM classroom WHERE is_deleted = 0";
		return count(sql);
	}

	@Override
	public Long save(ClassroomModel classroomModel) {
		ClassroomModel classroomModelCheck;
		if (classroomModel.getId() != null) {
			classroomModelCheck = findOne(classroomModel.getId());
			if (classroomModelCheck != null && classroomModelCheck.getId() != 0) {
				String sql = "UPDATE classroom SET name=?, code=?, modified_by=?, modified_date=? WHERE id=?";
				return update(sql, classroomModel.getName(), classroomModel.getCode(),
						classroomModel.getModifiedBy(), classroomModel.getModifiedDate(), classroomModel.getId());
			}
		}
		String sql = "INSERT INTO classroom (name, code, created_by, created_date, modified_date) values (?, ?, ?, ?, ?)";
		return insert(sql, classroomModel.getName(), classroomModel.getCode(), classroomModel.getCreatedBy(), classroomModel.getCreatedDate(), classroomModel.getModifiedDate());
	}

	@Override
	public List<ClassroomModel> findAll() {
		String sql = "SELECT * FROM classroom  WHERE is_deleted = 0";
		return query(sql, new ClassroomMapper());
	}

	@Override
	public Long delete(ClassroomModel classroomModel) {
		String sql = "UPDATE classroom SET modified_by=?, modified_date=?, is_deleted = 1 WHERE id=?";
		return update(sql, classroomModel.getModifiedBy(), classroomModel.getModifiedDate(), classroomModel.getId());
	}
}
