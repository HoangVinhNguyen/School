package com.school.controller.admin.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.school.constant.SystemConstant;
import com.school.model.InCourseModel;
import com.school.service.IInCourseService;

@RestController
@RequestMapping(value= {"/admin"})
public class RestInCourseController {

	@Autowired
	private IInCourseService inCourseService;
	
	@RequestMapping(value={"/api-admin-in-course"}, method=RequestMethod.GET)
	public List<InCourseModel> getAllClassrooms(HttpServletRequest request) {
		return inCourseService.findAll();
	}
	
	@RequestMapping(value={"/api-admin-in-course"}, method=RequestMethod.POST)
	public ResponseEntity<InCourseModel> getCreateClassroom(HttpServletRequest request, @RequestBody InCourseModel course) {
		if (inCourseService.save(course, SystemConstant.INSERT) == 0L) {
			 return new ResponseEntity<>(course, HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(course, HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-in-course"}, method=RequestMethod.PUT)
	public ResponseEntity<InCourseModel> getUpdateClassroom(HttpServletRequest request, @RequestBody InCourseModel course) {
		if (inCourseService.save(course, SystemConstant.MODIFY) == 0L) {
			return new ResponseEntity<>(course, HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(course, HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-in-course"}, method= RequestMethod.DELETE)
	public Long getDeleteClassroom(HttpServletRequest request, @RequestBody InCourseModel course) {
		return inCourseService.delete(course);
	}
}
