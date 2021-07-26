package com.school.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.school.DAO.IRoleDAO;
import com.school.constant.SystemConstant;
import com.school.entity.RoleEntity;
import com.school.model.ClassroomModel;
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
			RoleModel model = new RoleModel();
			model.loadFromEntity(itr.next());
			roleModels.add(model);
		}
		return roleModels;
	}

	@Override
	public RoleModel findOne(long id) {
		RoleModel model = new RoleModel();
		model.loadFromEntity(roleDAO.findOne(id));
		return model;
	}

	@Override
	public RoleModel findOneByCode(String code) {
		RoleModel model = new RoleModel();
		model.loadFromEntity(roleDAO.findOneByCode(code));
		return model;
	}

	@Override
	public RoleModel findOneByName(String name) {
		RoleModel model = new RoleModel();
		model.loadFromEntity(roleDAO.findOneByName(name));
		return model;
	}

	@Override
	public Long save(RoleModel model, String method) {
		model = getModifiedField(model, method);
		RoleEntity entity = new RoleEntity();
		entity.loadFromDTO(model);
		return roleDAO.save(entity);
	}

	@Override
	public Long delete(RoleModel model) {
		RoleEntity entity = new RoleEntity();
		entity.loadFromDTO(model);
		return roleDAO.delete(entity);
	}

	@Override
	public int getTotalItem() {
		return roleDAO.getTotalItem();
	}
	
	private RoleModel getModifiedField(RoleModel model, String method) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		switch (method) {
		case SystemConstant.INSERT:
			model.setModifiedDate(timestamp);
			model.setCreatedBy(authentication.getName());
			model.setCreatedDate(timestamp);
			break;
		case SystemConstant.MODIFY:
			model.setModifiedDate(timestamp);
			model.setModifiedBy(authentication.getName());
			break;
		}
		return model;
	}

}
