package com.school.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.model.LiteratureCourseModel;

public class LiteratureCourseMapper implements RowMapper<LiteratureCourseModel>{

	@Override
	public LiteratureCourseModel mapRow(ResultSet rs) {
		try {
			LiteratureCourseModel literatureCourseModel = new LiteratureCourseModel();
			literatureCourseModel.setId(rs.getLong("id"));
			literatureCourseModel.setUserId(rs.getLong("user_id"));
			literatureCourseModel.setPoint(rs.getDouble("point"));
			literatureCourseModel.setCreatedBy(rs.getString("created_by"));
			literatureCourseModel.setCreatedDate(rs.getTimestamp("created_date"));
			literatureCourseModel.setModifiedBy(rs.getString("modified_by"));
			literatureCourseModel.setModifiedDate(rs.getTimestamp("modified_date"));
			return literatureCourseModel;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
