package com.school.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.model.PostStatusModel;

public class PostStatusMapper implements RowMapper<PostStatusModel>{

	@Override
	public PostStatusModel mapRow(ResultSet rs) {
		try {
			PostStatusModel postStatus = new PostStatusModel();
			postStatus.setId(rs.getLong("id"));
			postStatus.setUserId(rs.getLong("user_id"));
			postStatus.setClassroomId(rs.getLong("classroom_id"));
			postStatus.setContent(rs.getString("content"));
			postStatus.setImage(rs.getString("image"));
			postStatus.setCreatedBy(rs.getString("created_by"));
			postStatus.setCreatedDate(rs.getTimestamp("created_date"));
			postStatus.setModifiedBy(rs.getString("modified_by"));
			postStatus.setModifiedDate(rs.getTimestamp("modified_date"));
			return postStatus;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
