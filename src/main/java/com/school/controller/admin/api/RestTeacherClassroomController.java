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
import com.school.model.PointModel;
import com.school.service.ITeacherClassroomService;

@RestController
@RequestMapping(value= {"/admin"})
public class RestTeacherClassroomController {

	@Autowired
	private ITeacherClassroomService teacherClassroomService;
	
	@RequestMapping(value={"/api-admin-teacher"}, method=RequestMethod.GET)
	public List<PointModel> getAllClassrooms(HttpServletRequest request) {
		return teacherClassroomService.findAll();
	}
	
	@RequestMapping(value={"/api-admin-teacher"}, method=RequestMethod.POST)
	public ResponseEntity<PointModel> getCreateClassroom(HttpServletRequest request, @RequestBody PointModel pointModel) {
		if (teacherClassroomService.save(pointModel, SystemConstant.INSERT) == 0L) {
			 return new ResponseEntity<>(pointModel, HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(pointModel, HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-teacher"}, method=RequestMethod.PUT)
	public ResponseEntity<PointModel> getUpdateClassroom(HttpServletRequest request, @RequestBody PointModel pointModel) {
		if (teacherClassroomService.save(pointModel, SystemConstant.MODIFY) == 0L) {
			return new ResponseEntity<>(pointModel, HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(pointModel, HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-teacher"}, method= RequestMethod.DELETE)
	public Long getDeleteClassroom(HttpServletRequest request, @RequestBody PointModel pointModel) {
		return teacherClassroomService.delete(pointModel);
	}
	
}
