package com.school.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.school.common.common.SystemConstant;
import com.school.service.UserService;

@Controller
@RequestMapping("/api/user")
@PreAuthorize("hasAnyRole('" + SystemConstant.STUDENT + ", " + SystemConstant.TEACHER + "')")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getClasses(@PathVariable(name="id") Long id) {
		return ResponseEntity.ok(userService.findUserByIdDto(id));
	}
}
