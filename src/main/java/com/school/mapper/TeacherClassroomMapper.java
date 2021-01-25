package com.school.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.model.TeacherClassroomModel;

public class TeacherClassroomMapper implements RowMapper<TeacherClassroomModel>{

	
	@Override
	public TeacherClassroomModel mapRow(ResultSet rs) {
		try {
			TeacherClassroomModel teacherClassroomModel = new TeacherClassroomModel();
			teacherClassroomModel.setId(rs.getLong("id"));
			teacherClassroomModel.setTeacherId(rs.getLong("teacher_id"));
			teacherClassroomModel.setStudentId(rs.getLong("student_id"));
			teacherClassroomModel.setClassroomId(rs.getLong("classroom_id"));
			teacherClassroomModel.setCourseId(rs.getLong("course_id"));
			teacherClassroomModel.setPoint(rs.getDouble("point"));
			teacherClassroomModel.setCreatedBy(rs.getString("created_by"));
			teacherClassroomModel.setCreatedDate(rs.getTimestamp("created_date"));
			teacherClassroomModel.setModifiedBy(rs.getString("modified_by"));
			teacherClassroomModel.setModifiedDate(rs.getTimestamp("modified_date"));
			return teacherClassroomModel;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
