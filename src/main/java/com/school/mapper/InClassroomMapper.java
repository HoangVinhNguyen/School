package com.school.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.model.InClassroomModel;

public class InClassroomMapper implements RowMapper<InClassroomModel>{

	@Override
	public InClassroomModel mapRow(ResultSet rs) {
		try {
			InClassroomModel inClassroom = new InClassroomModel();
			inClassroom.setId(rs.getLong("id"));
			inClassroom.setStudentId(rs.getLong("user_id"));
			inClassroom.setClassroomId(rs.getLong("classroom_id"));
			inClassroom.setCreatedBy(rs.getString("created_by"));
			inClassroom.setCreatedDate(rs.getTimestamp("created_date"));
			inClassroom.setModifiedBy(rs.getString("modified_by"));
			inClassroom.setModifiedDate(rs.getTimestamp("modified_date"));
			return inClassroom;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
