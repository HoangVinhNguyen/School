package com.school.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.school.model.RoleModel;

public class RoleMapper implements RowMapper<RoleModel>{

	@Override
	public RoleModel mapRow(ResultSet rs) {
		try {
			RoleModel roleModel = new RoleModel();
			roleModel.setId(rs.getLong("id"));
			roleModel.setCode(rs.getString("code"));
			roleModel.setName(rs.getString("name"));
			roleModel.setCreatedBy(rs.getString("created_by"));
			roleModel.setCreatedDate(rs.getTimestamp("created_date"));
			roleModel.setModifiedBy(rs.getString("modified_by"));
			roleModel.setModifiedDate(rs.getTimestamp("modified_date"));
			return roleModel;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
