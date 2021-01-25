package com.school.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.model.RoleModel;
import com.school.model.UserModel;

public class UserMapper implements RowMapper<UserModel> {
	
	@Override
	public UserModel mapRow(ResultSet rs) {
		try {
			UserModel user = new UserModel();
			user.setId(rs.getLong("id"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setFullname(rs.getString("fullname"));
			user.setDob(rs.getTimestamp("dob"));
			user.setAddress(rs.getString("address"));
			user.setRoledId(rs.getLong("role_id"));
			user.setCreatedBy(rs.getString("created_by"));
			user.setCreatedDate(rs.getTimestamp("created_date"));
			user.setModifiedBy(rs.getString("modified_by"));
			user.setModifiedDate(rs.getTimestamp("modified_date"));
			try {
				RoleModel role = new RoleModel();
				role.setCode(rs.getString("code"));
				role.setName(rs.getString("name"));
				user.setRole(role);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
