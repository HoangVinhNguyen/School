package com.school.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.model.ClassroomModel;

public class ClassroomMapper implements RowMapper<ClassroomModel>{

	@Override
	public ClassroomModel mapRow(ResultSet rs) {
		try {
			ClassroomModel classroom = new ClassroomModel();
			classroom.setId(rs.getLong("id"));
			classroom.setCode(rs.getString("code"));
			classroom.setName(rs.getString("name"));
			classroom.setCreatedBy(rs.getString("created_by"));
			classroom.setCreatedDate(rs.getTimestamp("created_date"));
			classroom.setModifiedBy(rs.getString("modified_by"));
			classroom.setModifiedDate(rs.getTimestamp("modified_date"));
			return classroom;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
