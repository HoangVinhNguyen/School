package com.school.controller.admin.api;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.school.constant.SystemConstant;
import com.school.model.CourseModel;
import com.school.model.UserModel;
import com.school.service.ICourseService;

@RestController
@RequestMapping(value= {"/admin"})
public class RestCourseController {

	@Autowired
	private ICourseService courseService;
	
	@RequestMapping(value={"/api-admin-course"}, method=RequestMethod.GET)
	public List<CourseModel> getAllCourses(HttpServletRequest request) {
		return courseService.findAll();
	}
	
	@RequestMapping(value={"/api-admin-course"}, method=RequestMethod.POST)
	public ResponseEntity<?> getCreateCourse(HttpServletRequest request, @RequestBody CourseModel model) {
		Long rs = courseService.save(model, SystemConstant.INSERT);
		if (rs == SystemConstant.DUPLICATE) {
			return new ResponseEntity<>(SystemConstant.DUPLICATE_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		else if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.ERROR_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(SystemConstant.OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-course"}, method=RequestMethod.PUT)
	public ResponseEntity<?> getUpdateCourse(HttpServletRequest request, @RequestBody CourseModel model) {
		Long rs = courseService.save(model, SystemConstant.MODIFY);
		if (rs == SystemConstant.DUPLICATE) {
			return new ResponseEntity<>(SystemConstant.DUPLICATE_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		else if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.ERROR_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(SystemConstant.OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value="/api-admin-course-user", method=RequestMethod.POST)
	public ResponseEntity<?> getUpdateCourseUser(@RequestBody CourseModel model) {
		Long rs = courseService.saveUser(model,SystemConstant.MODIFY);
		if (rs == SystemConstant.DUPLICATE) {
			return new ResponseEntity<>(SystemConstant.DUPLICATE_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		else if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.ERROR_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(SystemConstant.OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-course"}, method= RequestMethod.DELETE)
	public ResponseEntity<?> getDeleteCourse(HttpServletRequest request, @RequestBody CourseModel model) {
		Long rs = courseService.delete(model);
		if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.DELETE_FAILED_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(SystemConstant.DELETE_OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-course-file"}, method= RequestMethod.POST)
	public Long sendFileCourse(@RequestParam(name="file") MultipartFile file) {
		return courseService.saveList(file);
	}
	
	@RequestMapping(value={"/api-admin-course-file-form-download"}, method=RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		courseService.downloadForm(request, response);
	}
	
	@RequestMapping(value={"/api-admin-course-file-report-download"}, method=RequestMethod.GET)
	public void downloadReport(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		courseService.getReport(request, response);
	}
}
