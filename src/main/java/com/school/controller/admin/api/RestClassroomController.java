package com.school.controller.admin.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.school.model.ClassroomModel;
import com.school.service.IClassroomService;

@RestController
@RequestMapping(value= {"/admin"})
public class RestClassroomController {

	@Autowired
	private IClassroomService classroomService;
	
	@RequestMapping(value={"/api-admin-classroom"}, method=RequestMethod.GET)
	public List<ClassroomModel> getAllClassroom(HttpServletRequest request) {
		return classroomService.findAll();
	}
}
