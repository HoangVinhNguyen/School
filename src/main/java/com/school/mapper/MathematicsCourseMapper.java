package com.school.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.model.LiteratureCourseModel;
import com.school.model.MathematicsCourseModel;

public class MathematicsCourseMapper implements RowMapper<MathematicsCourseModel>{

	@Override
	public MathematicsCourseModel mapRow(ResultSet rs) {
		try {
			MathematicsCourseModel mathematicsCourseModel = new MathematicsCourseModel();
			mathematicsCourseModel.setId(rs.getLong("id"));
			mathematicsCourseModel.setUserId(rs.getLong("user_id"));
			mathematicsCourseModel.setPoint(rs.getDouble("point"));
			mathematicsCourseModel.setCreatedBy(rs.getString("created_by"));
			mathematicsCourseModel.setCreatedDate(rs.getTimestamp("created_date"));
			mathematicsCourseModel.setModifiedBy(rs.getString("modified_by"));
			mathematicsCourseModel.setModifiedDate(rs.getTimestamp("modified_date"));
			return mathematicsCourseModel;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
