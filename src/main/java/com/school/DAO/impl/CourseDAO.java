package com.school.DAO.impl;

import java.util.List;

import com.school.DAO.ICourseDAO;
import com.school.mapper.ClassroomMapper;
import com.school.mapper.CourseMapper;
import com.school.model.CourseModel;
import com.school.paging.Pageble;

public class CourseDAO extends AbstractDAO<CourseModel> implements ICourseDAO{

	@Override
	public List<CourseModel> findAll(Pageble pageble) {
		String sql = "SELECT * FROM course";
		return query(sql, new CourseMapper());
	}

	@Override
	public CourseModel findOne(long id) {
		String sql = "SELECT * FROM course WHERE id = ? AND is_deleted = 0";
		List<CourseModel> courseModel = query(sql, new CourseMapper(), id);
		return courseModel.isEmpty() ? null : courseModel.get(0);
	}

	@Override
	public CourseModel findOneByCode(String code) {
		String sql = "SELECT * FROM course WHERE code = ? AND is_deleted = 0";
		List<CourseModel> courseModel = query(sql, new CourseMapper(), code);
		return courseModel.isEmpty() ? null : courseModel.get(0);
	}
	
	@Override
	public CourseModel findOneByName(String name) {
		String sql = "SELECT * FROM course WHERE name = ? AND is_deleted = 0";
		List<CourseModel> courseModel = query(sql, new CourseMapper(), name);
		return courseModel.isEmpty() ? null : courseModel.get(0);
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM course WHERE is_deleted = 0";
		return count(sql);
	}

	@Override
	public Long save(CourseModel courseModel) {
		CourseModel courseModelCheck;
		if (courseModel.getId() != null) {
			courseModelCheck = findOne(courseModel.getId());
			if (courseModelCheck != null && courseModelCheck.getId() != 0) {
				String sql = "UPDATE course SET name=?, code=?, modified_by=?, modified_date=? WHERE id=?";
				return update(sql, courseModel.getName(), courseModel.getCode(),
						courseModel.getModifiedBy(), courseModel.getModifiedDate(), courseModel.getId());
			}
		}
		String sql = "INSERT INTO course (name, code, created_by, created_date, modified_date) values (?, ?, ?, ?, ?)";
		return insert(sql, courseModel.getName(), courseModel.getCode(), courseModel.getCreatedBy(), courseModel.getCreatedDate(), courseModel.getModifiedDate());
	}

	@Override
	public List<CourseModel> findAll() {
		String sql = "SELECT * FROM course  WHERE is_deleted = 0";
		return query(sql, new CourseMapper());
	}

	@Override
	public Long delete(CourseModel courseModel) {
		String sql = "UPDATE course SET modified_by=?, modified_date=?, is_deleted = 1 WHERE id=?";
		return update(sql, courseModel.getModifiedBy(), courseModel.getModifiedDate(), courseModel.getId());
	}
}
