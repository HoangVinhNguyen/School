package com.school.DAO;

import java.util.List;

import com.school.entity.RoleEntity;

public interface IRoleDAO {

	public List<RoleEntity> findAll();
	public RoleEntity findOne(long id);
	public RoleEntity findOneByCode(String code);
	public RoleEntity findOneByName(String name);
	public Long save(RoleEntity roleEntity);
	public Long delete(RoleEntity roleEntity);
	public int getTotalItem();
}
