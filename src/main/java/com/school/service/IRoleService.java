package com.school.service;

import java.util.List;

import com.school.model.RoleModel;
import com.school.paging.Pageble;

public interface IRoleService {

	public List<RoleModel> findAll(Pageble pageble);
	public List<RoleModel> findAll();
	public RoleModel findOne(long id);
	public RoleModel findOneByCode(String code);
	public RoleModel findOneByName(String name);
	public Long save(RoleModel roleModel);
	public Long delete(RoleModel roleModel);
	public int getTotalItem();
}
