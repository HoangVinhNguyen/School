package com.school.controller.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value= {"/admin"})
public class AdminController {

	@RequestMapping(value= {"/", "/home"}, method = RequestMethod.GET)
	public String getHome(HttpServletRequest request) {
		return "adminHome";
	}
	
	@RequestMapping(value= {"/user"}, method = RequestMethod.GET)
	public String getUsers(HttpServletRequest request) {
		return "user-administration";
	}
	
	@RequestMapping(value= {"/teacherclassroom"}, method = RequestMethod.GET)
	public String getTeacherClassroom(HttpServletRequest request) {
		return "teacher-classroom-administration";
	}
	
	@RequestMapping(value= {"/userCourse"}, method = RequestMethod.GET)
	public String getInClassroom(HttpServletRequest request) {
		return "user-course-administration";
	}
	
	@RequestMapping(value= {"/course"}, method = RequestMethod.GET)
	public String getCourse(HttpServletRequest request) {
		return "course-administration";
	}
	
	@RequestMapping(value= {"/classroom"}, method = RequestMethod.GET)
	public String getClassroom(HttpServletRequest request) {
		return "classroom-administration";
	}
	
	@RequestMapping(value= {"/class"}, method = RequestMethod.GET)
	public String getClassIn(HttpServletRequest request) {
		return "class-administration";
	}
	
	@RequestMapping(value= {"/grade"}, method = RequestMethod.GET)
	public String getGrade(HttpServletRequest request) {
		return "grade-administration";
	}
	
	@RequestMapping(value= {"/level-grade"}, method = RequestMethod.GET)
	public String getLevelGrade(HttpServletRequest request) {
		return "level-grade-administration";
	}
	
}
