package com.school.DAO.impl;

import java.util.List;

import com.school.DAO.IRoleDAO;
import com.school.mapper.ClassroomMapper;
import com.school.mapper.RoleMapper;
import com.school.model.ClassroomModel;
import com.school.model.RoleModel;
import com.school.paging.Pageble;

public class RoleDAO extends AbstractDAO<RoleModel> implements IRoleDAO{

	@Override
	public List<RoleModel> findAll(Pageble pageble) {
		String sql = "SELECT * FROM role";
		return query(sql, new RoleMapper());
	}

	@Override
	public RoleModel findOne(long id) {
		String sql = "SELECT * FROM role WHERE id = ? AND is_deleted = 0";
		List<RoleModel> roleModel = query(sql, new RoleMapper(), id);
		return roleModel.isEmpty() ? null : roleModel.get(0);
	}

	@Override
	public RoleModel findOneByCode(String code) {
		String sql = "SELECT * FROM role WHERE code = ? AND is_deleted = 0";
		List<RoleModel> roleModel = query(sql, new RoleMapper(), code);
		return roleModel.isEmpty() ? null : roleModel.get(0);
	}
	
	@Override
	public RoleModel findOneByName(String name) {
		String sql = "SELECT * FROM role WHERE name = ? AND is_deleted = 0";
		List<RoleModel> roleModel = query(sql, new RoleMapper(), name);
		return roleModel.isEmpty() ? null : roleModel.get(0);
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM role WHERE is_deleted = 0";
		return count(sql);
	}

	@Override
	public Long save(RoleModel roleModel) {
		RoleModel roleModelCheck;
		if (roleModel.getId() != null) {
			roleModelCheck = findOne(roleModel.getId());
			if (roleModelCheck != null && roleModelCheck.getId() != 0) {
				String sql = "UPDATE role SET name=?, code=?, modified_by=?, modified_date=? WHERE id=?";
				return update(sql, roleModel.getName(), roleModel.getCode(),
						roleModel.getModifiedBy(), roleModel.getModifiedDate(), roleModel.getId());
			}
		}
		String sql = "INSERT INTO role (name, code, created_by, created_date, modified_date) values (?, ?, ?, ?, ?)";
		return insert(sql, roleModel.getName(), roleModel.getCode(), roleModel.getCreatedBy(), roleModel.getCreatedDate(), roleModel.getModifiedDate());
	}

	@Override
	public List<RoleModel> findAll() {
		String sql = "SELECT * FROM role  WHERE is_deleted = 0";
		return query(sql, new RoleMapper());
	}

	@Override
	public Long delete(RoleModel roleModel) {
		String sql = "UPDATE role SET modified_by=?, modified_date=?, is_deleted = 1 WHERE id=?";
		return update(sql, roleModel.getModifiedBy(), roleModel.getModifiedDate(), roleModel.getId());
	}
}
