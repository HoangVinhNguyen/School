package com.school.controller.admin.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.school.constant.SystemConstant;
import com.school.model.RoleModel;
import com.school.service.IRoleService;

@RestController
@RequestMapping(value= {"/admin"})
public class RestRoleController {

	@Autowired
	private IRoleService roleService;
	
	@RequestMapping(value={"/api-admin-role"}, method=RequestMethod.GET)
	public List<RoleModel> getAllClassrooms(HttpServletRequest request) {
		return roleService.findAll();
	}
	
	@RequestMapping(value={"/roleServiceapi-admin-role"}, method=RequestMethod.POST)
	public Long getCreateClassroom(HttpServletRequest request, @RequestBody RoleModel classroom) {
		return roleService.save(classroom, SystemConstant.INSERT);
	}
	
	@RequestMapping(value={"/roleServiceapi-admin-role"}, method=RequestMethod.PUT)
	public Long getUpdateClassroom(HttpServletRequest request, @RequestBody RoleModel classroom) {
		return roleService.save(classroom, SystemConstant.MODIFY);
	}
	
	@RequestMapping(value={"/roleServiceapi-admin-role"}, method= RequestMethod.DELETE)
	public Long getDeleteClassroom(HttpServletRequest request, @RequestBody RoleModel classroom) {
		return roleService.delete(classroom);
	}
}
