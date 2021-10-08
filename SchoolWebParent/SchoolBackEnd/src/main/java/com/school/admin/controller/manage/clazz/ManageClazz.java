package com.school.admin.controller.manage.clazz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.school.admin.service.ClazzService;

@Controller
public class ManageClazz {

	@Autowired
	private ClazzService service;
	
	@GetMapping("/manage-class")
	public String getManageClazz() {
		return "manage/clazz";
	}
}
