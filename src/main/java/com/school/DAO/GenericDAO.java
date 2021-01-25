package com.school.DAO;

import java.sql.Connection;
import java.util.List;

import com.school.mapper.RowMapper;

public interface GenericDAO<T> {

	public Connection getConnection();
	
	<T> List<T> query(String sql, RowMapper<T> rowMapper, Object...parameters);
	Long update(String sql, Object...parameters);
	Long insert(String sql, Object...parameters);
	int count(String sql, Object...parameters);
}
