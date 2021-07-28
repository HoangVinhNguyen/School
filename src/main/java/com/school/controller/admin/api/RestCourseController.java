package com.school.controller.admin.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.school.constant.SystemConstant;
import com.school.model.CourseModel;
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
	public Long getCreateCourse(HttpServletRequest request, @RequestBody CourseModel classroom) {
		return courseService.save(classroom, SystemConstant.INSERT);
	}
	
	@RequestMapping(value={"/api-admin-course"}, method=RequestMethod.PUT)
	public Long getUpdateCourse(HttpServletRequest request, @RequestBody CourseModel classroom) {
		return courseService.save(classroom, SystemConstant.MODIFY);
	}
	
	@RequestMapping(value={"/api-admin-course"}, method= RequestMethod.DELETE)
	public Long getDeleteCourse(HttpServletRequest request, @RequestBody CourseModel classroom) {
		return courseService.delete(classroom);
	}
	
	@RequestMapping(value={"/api-admin-course-file"}, method= RequestMethod.POST)
	public Long sendFileCourse(@RequestParam(name="file") MultipartFile file) {
		return courseService.saveList(file);
	}
}
