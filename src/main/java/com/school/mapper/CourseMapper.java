package com.school.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.model.CourseModel;

public class CourseMapper implements RowMapper<CourseModel>{

	@Override
	public CourseModel mapRow(ResultSet rs) {
		try {
			CourseModel courseModel = new CourseModel();
			courseModel.setId(rs.getLong("id"));
			courseModel.setCode(rs.getString("code"));
			courseModel.setName(rs.getString("name"));
			courseModel.setCreatedBy(rs.getString("created_by"));
			courseModel.setCreatedDate(rs.getTimestamp("created_date"));
			courseModel.setModifiedBy(rs.getString("modified_by"));
			courseModel.setModifiedDate(rs.getTimestamp("modified_date"));
			return courseModel;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
