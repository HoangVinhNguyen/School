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
import com.school.model.TeacherClassroomModel;
import com.school.service.ITeacherClassroomService;

@RestController
@RequestMapping(value= {"/admin"})
public class RestTeacherClassroomController {

	@Autowired
	private ITeacherClassroomService teacherClassroomService;
	
	@RequestMapping(value={"/api-admin-teacher"}, method=RequestMethod.GET)
	public List<TeacherClassroomModel> getAllClassrooms(HttpServletRequest request) {
		return teacherClassroomService.findAll();
	}
	
	@RequestMapping(value={"/api-admin-teacher"}, method=RequestMethod.POST)
	public ResponseEntity<TeacherClassroomModel> getCreateClassroom(HttpServletRequest request, @RequestBody TeacherClassroomModel teacherClassroomModel) {
		if (teacherClassroomService.save(teacherClassroomModel, SystemConstant.INSERT) == 0L) {
			 return new ResponseEntity<>(teacherClassroomModel, HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(teacherClassroomModel, HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-teacher"}, method=RequestMethod.PUT)
	public ResponseEntity<TeacherClassroomModel> getUpdateClassroom(HttpServletRequest request, @RequestBody TeacherClassroomModel teacherClassroomModel) {
		if (teacherClassroomService.save(teacherClassroomModel, SystemConstant.MODIFY) == 0L) {
			return new ResponseEntity<>(teacherClassroomModel, HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(teacherClassroomModel, HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-teacher"}, method= RequestMethod.DELETE)
	public Long getDeleteClassroom(HttpServletRequest request, @RequestBody TeacherClassroomModel teacherClassroomModel) {
		return teacherClassroomService.delete(teacherClassroomModel);
	}
	
}
