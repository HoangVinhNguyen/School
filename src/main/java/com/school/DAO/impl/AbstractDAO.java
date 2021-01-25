package com.school.DAO.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.school.DAO.GenericDAO;
import com.school.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T>{

	ResourceBundle processDB = ResourceBundle.getBundle("db");
	
	@Override
	public Connection getConnection() {
		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			String url = "jdbc:mysql://localhost:3306/newsdb";
//			String user = "root";
//			String pass = "1234";
			Class.forName(processDB.getString("driverName"));
			String url = processDB.getString("url");
			String user = processDB.getString("user");
			String pass = processDB.getString("pass");
			return DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
		List<T> results = new ArrayList<T>();
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			conn = getConnection();
			statement = conn.prepareStatement(sql);
			setParameters(statement, parameters);
			resultset = statement.executeQuery();
			while (resultset.next()) {
				results.add(rowMapper.mapRow(resultset));
			}
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultset != null) {
					resultset.close();
				}
			} catch (SQLException e) {
				return null;
			}
		}
	}

	private void setParameters(PreparedStatement statement, Object...parameters) {
		
		try {
			for (int i = 1; i <= parameters.length; i++) {
				Object parameter = parameters[i - 1];
				if (parameter instanceof Long) {
					statement.setLong(i, (Long)parameter);
				}
				else if(parameter instanceof String) {
					statement.setString(i, (String)parameter);
				}
				else if (parameter instanceof Integer) {
					statement.setInt(i, (int)parameter);
				}
				else if (parameter instanceof Double) {
					statement.setDouble(i, (Double)parameter);
				}
				else if (parameter instanceof Timestamp) {
					statement.setTimestamp(i, (Timestamp)parameter);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Long update(String sql, Object... parameters) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			Long id = null;
			conn = getConnection();
			conn.setAutoCommit(false);
			statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setParameters(statement, parameters);
			statement.executeUpdate();
			resultset = statement.getGeneratedKeys();
			if (resultset.next()) {
				id = resultset.getLong(1);
			}
			conn.commit();
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Long insert(String sql, Object... parameters) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			Long id = null;
			conn = getConnection();
			conn.setAutoCommit(false);
			statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setParameters(statement, parameters);
			statement.executeUpdate();
			resultset = statement.getGeneratedKeys();
			if (resultset.next()) {
				id = resultset.getLong(1);
			}
			conn.commit();
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultset != null) {
					resultset.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public int count(String sql, Object... parameters) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			int count = 0;
			conn = getConnection();
			statement = conn.prepareStatement(sql);
			setParameters(statement, parameters);
			resultset = statement.executeQuery();
			while (resultset.next()) {
//				count = resultset.getInt("count(*)");
				count = resultset.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultset != null) {
					resultset.close();
				}
			} catch (SQLException e) {
				return 0;
			}
		}
	}

}
