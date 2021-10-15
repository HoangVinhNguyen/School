package com.school.admin.controller.clazz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.admin.service.ClazzService;
import com.school.common.dto.UserDto;

@RestController
public class ClazzRestController {

	@Autowired
	private ClazzService service;
	
	@PostMapping("/clazzes/check_namcode")
	public String checkDuplicateNameCode(@Param("id") Long id, @Param("name") String name, 
			@Param("code") String code) {
		boolean checkName = service.isNameUnique(id, name);
		boolean checkCode = service.isCodeUnique(id, code);
		if (checkName == true && checkCode == true) {
			return "OK";
		}
		else if (checkName == false && checkCode == false) {
			return "Duplicated";
		}
		else if (checkName == false && checkCode == true) {
			return "Name";
		}
		else if (checkName == true && checkCode == false) {
			return "Code";
		}
		return null;
	}
	
	@PostMapping("/clazzes/add_teacher")
	public UserDto addTeacherToClass(@Param("id") Long id, @Param("email") String email) {
		return service.addTeacherToClass(id, email);
	}
	@PostMapping("/clazzes/add_student")
	public UserDto addStudentToClass(@Param("id") Long id, @Param("email") String email) {
		return service.addStudentToClass(id, email);
	}

	@PostMapping("/clazzes/delete_user")
	public UserDto deleteUserInClass(@Param("id") Long id, @Param("email") String email) {
		return service.deleteUserInClass(id, email);
	}

	@PostMapping("/clazzes/check_user_inclass")
	public boolean checkUserInClass(@Param("id") Long id, @Param("email") String email) {
		return service.checkUserInClass(id, email);
	}
	
}
