package com.school.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.school.DAO.IRoleDAO;
import com.school.model.RoleModel;
import com.school.paging.Pageble;
import com.school.service.IRoleService;

public class RoleService implements IRoleService{

	@Inject
	private IRoleDAO roleDAO;
	
	@Override
	public List<RoleModel> findAll(Pageble pageble) {
		return roleDAO.findAll(pageble);
	}

	@Override
	public List<RoleModel> findAll() {
		return roleDAO.findAll();
	}

	@Override
	public RoleModel findOne(long id) {
		return roleDAO.findOne(id);
	}

	@Override
	public RoleModel findOneByCode(String code) {
		return roleDAO.findOneByCode(code);
	}

	@Override
	public RoleModel findOneByName(String name) {
		return roleDAO.findOneByName(name);
	}

	@Override
	public Long save(RoleModel roleModel) {
		return roleDAO.save(roleModel);
	}

	@Override
	public Long delete(RoleModel roleModel) {
		return roleDAO.delete(roleModel);
	}

	@Override
	public int getTotalItem() {
		return roleDAO.getTotalItem();
	}

}
