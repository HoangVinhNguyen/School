package com.school.admin.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.admin.exception.EntityNotFoundException;
import com.school.admin.service.UserService;
import com.school.common.common.SystemConstant;
import com.school.common.dto.UserDto;

@RestController
public class UserRestController {

	@Autowired
	private UserService service;
	
	@PostMapping("/users/check_email")
	public String checkDuplicateEmail(@Param("id") Long id, @Param("email") String email) {
		return service.isEmailUnique(id, email) ? "OK" : "Duplicated";
	}
	
	@PostMapping("/users/get_user")
	public UserDto getUserByEmail(@Param("email") String email) {
		UserDto user =  service.getByEmailRest(email);
		return user;
	}
	
	@PostMapping("/users/get_user_id")
	public UserDto getUserById(@Param("id") Long id) throws EntityNotFoundException {
		UserDto user =  service.getByIdRest(id);
		return user;
	}
	@PostMapping("/users/get_user_teacher")
	public UserDto getTeacherById(@Param("id") Long id) throws EntityNotFoundException {
		UserDto user = service.getByIdTeacherRest(id);
		return user;
	}
	
	@PostMapping("/users/get_user_student")
	public UserDto getStudentById(@Param("id") Long id) throws EntityNotFoundException {
		UserDto user = service.getByIdStudentRest(id);
		return user;
	}
}
