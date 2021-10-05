package com.school.admin.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.admin.service.UserService;
import com.school.common.entity.User;

@RestController
public class UserRestController {

	@Autowired
	private UserService service;
	
	@PostMapping("/users/check_email")
	public String checkDuplicateEmail(@Param("id") Long id, @Param("email") String email) {
		return service.isEmailUnique(id, email) ? "OK" : "Duplicated";
	}
	
	@PostMapping("/users/get_user")
	public User getUserByEmail(@Param("email") String email) {
		User user =  service.getByEmail(email);
		return user;
	}
}
