package com.school.admin.controller.clazz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.admin.service.ClazzService;

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
	
}