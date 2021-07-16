package com.school.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.DAO.IRoleDAO;
import com.school.entity.RoleEntity;
import com.school.model.RoleModel;
import com.school.service.IRoleService;

@Service
public class RoleService implements IRoleService{

	@Autowired
	private IRoleDAO roleDAO;

	@Override
	public List<RoleModel> findAll() {
		List<RoleModel> roleModels = new ArrayList<RoleModel>();
		List<RoleEntity> roleEntities = roleDAO.findAll();
		Iterator<RoleEntity> itr = roleEntities.iterator();
		while(itr.hasNext()) {
			RoleModel roleModel = new RoleModel();
			roleModel.loadFromEntity(itr.next());
			roleModels.add(roleModel);
		}
		return roleModels;
	}

	@Override
	public RoleModel findOne(long id) {
		RoleModel roleModel = new RoleModel();
		roleModel.loadFromEntity(roleDAO.findOne(id));
		return roleModel;
	}

	@Override
	public RoleModel findOneByCode(String code) {
		RoleModel roleModel = new RoleModel();
		roleModel.loadFromEntity(roleDAO.findOneByCode(code));
		return roleModel;
	}

	@Override
	public RoleModel findOneByName(String name) {
		RoleModel roleModel = new RoleModel();
		roleModel.loadFromEntity(roleDAO.findOneByName(name));
		return roleModel;
	}

	@Override
	public Long save(RoleModel roleModel) {
		RoleEntity entity = new RoleEntity();
		entity.loadFromDTO(roleModel);
		return roleDAO.save(entity);
	}

	@Override
	public Long delete(RoleModel roleModel) {
		RoleEntity entity = new RoleEntity();
		entity.loadFromDTO(roleModel);
		return roleDAO.delete(entity);
	}

	@Override
	public int getTotalItem() {
		return roleDAO.getTotalItem();
	}

}
