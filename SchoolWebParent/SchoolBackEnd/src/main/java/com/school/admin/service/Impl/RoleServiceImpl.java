package com.school.admin.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.admin.repository.RoleRepository;
import com.school.admin.service.RoleService;
import com.school.common.entity.Role;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository repo;

	@Override
	public List<Role> getAllRoleNotAD() {
		Optional<List<Role>> op = Optional.ofNullable(repo.findAllRoleNotAD());
		return op.orElse(null);
	}

}
