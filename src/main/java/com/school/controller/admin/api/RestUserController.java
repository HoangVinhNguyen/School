package com.school.controller.admin.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.school.model.UserModel;
import com.school.service.IUserService;

@RestController
@RequestMapping(value= {"/admin"})
public class RestUserController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/admin-user", method=RequestMethod.GET)
	public List<UserModel> getAllUser(HttpServletRequest request) {
		List<UserModel> users = userService.findAll();
		return users;
	}
}
