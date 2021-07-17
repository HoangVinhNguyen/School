package com.school.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value= {"/admin"})
public class AdminController {

	@RequestMapping(value= {"/", "/home"}, method = RequestMethod.GET)
	public String getHome(HttpServletRequest request) {
		return "home";
	}
	
	@RequestMapping(value= {"/user"}, method = RequestMethod.GET)
	public String getUsers(HttpServletRequest request) {
		return "user-administration";
	}
	
	@RequestMapping(value= {"/teacherclassroom"}, method = RequestMethod.GET)
	public String getTeacherClassroom(HttpServletRequest request) {
		return "teacher-classroom-administration";
	}
	
	@RequestMapping(value= {"/inclassroom"}, method = RequestMethod.GET)
	public String getInClassroom(HttpServletRequest request) {
		return "in-classroom-administration";
	}
	
	@RequestMapping(value= {"/course"}, method = RequestMethod.GET)
	public String getCourse(HttpServletRequest request) {
		return "course-administration";
	}
	
	@RequestMapping(value= {"/classroom"}, method = RequestMethod.GET)
	public String getClassroom(HttpServletRequest request) {
		return "classroom-administration";
	}
	
}
