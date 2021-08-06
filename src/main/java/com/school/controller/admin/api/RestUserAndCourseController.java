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
import com.school.model.UserAndCourseModel;
import com.school.service.IUserAndCourseService;

@RestController
@RequestMapping(value= {"/admin"})
public class RestUserAndCourseController {

	@Autowired
	private IUserAndCourseService userAndCourseService;
	
	@RequestMapping(value={"/api-admin-user-and-course"}, method=RequestMethod.GET)
	public List<UserAndCourseModel> getAllClassrooms(HttpServletRequest request) {
		return userAndCourseService.findAll();
	}
	
	@RequestMapping(value={"/api-admin-user-and-course"}, method=RequestMethod.POST)
	public ResponseEntity<?> getCreateClassroom(HttpServletRequest request, @RequestBody UserAndCourseModel course) {
		Long rs = userAndCourseService.save(course, SystemConstant.INSERT);
		if (rs == SystemConstant.DUPLICATE) {
			return new ResponseEntity<>(SystemConstant.DUPLICATE_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		else if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.ERROR_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(SystemConstant.OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-user-and-course"}, method=RequestMethod.PUT)
	public ResponseEntity<?> getUpdateClassroom(HttpServletRequest request, @RequestBody UserAndCourseModel course) {
		Long rs = userAndCourseService.save(course, SystemConstant.MODIFY);
		if (rs == SystemConstant.DUPLICATE) {
			return new ResponseEntity<>(SystemConstant.DUPLICATE_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		else if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.ERROR_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(SystemConstant.OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-user-and-course"}, method= RequestMethod.DELETE)
	public ResponseEntity<?> getDeleteClassroom(HttpServletRequest request, @RequestBody UserAndCourseModel course) {
		Long rs = userAndCourseService.delete(course);
		if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.DELETE_FAILED_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(SystemConstant.DELETE_OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
}
