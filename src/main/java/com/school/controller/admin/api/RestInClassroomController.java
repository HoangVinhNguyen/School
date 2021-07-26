package com.school.controller.admin.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.school.constant.SystemConstant;
import com.school.model.InClassroomModel;
import com.school.service.IInClassroomService;

@RestController
@RequestMapping(value= {"/admin"})
public class RestInClassroomController {

	@Autowired
	private IInClassroomService inClassroomService;
	
	@RequestMapping(value={"/api-admin-inclassroom"}, method=RequestMethod.GET)
	public List<InClassroomModel> getAllClassrooms(HttpServletRequest request) {
		return inClassroomService.findAll();
	}
	
	@RequestMapping(value={"/api-admin-inclassroom"}, method=RequestMethod.POST)
	public ResponseEntity<InClassroomModel> getCreateClassroom(HttpServletRequest request, @RequestBody InClassroomModel classroom) {
		if (inClassroomService.save(classroom, SystemConstant.INSERT) == 0L) {
			 return new ResponseEntity<>(classroom, HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(classroom, HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-inclassroom"}, method=RequestMethod.PUT)
	public ResponseEntity<InClassroomModel> getUpdateClassroom(HttpServletRequest request, @RequestBody InClassroomModel classroom) {
		if (inClassroomService.save(classroom, SystemConstant.MODIFY) == 0L) {
			return new ResponseEntity<>(classroom, HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(classroom, HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-inclassroom"}, method= RequestMethod.DELETE)
	public Long getDeleteClassroom(HttpServletRequest request, @RequestBody InClassroomModel classroom) {
		return inClassroomService.delete(classroom);
	}
}
