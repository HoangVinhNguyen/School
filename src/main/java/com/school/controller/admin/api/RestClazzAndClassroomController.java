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
import com.school.model.ClazzAndClassroomModel;
import com.school.service.IClazzAndClassroomService;

@RestController
@RequestMapping(value= {"/admin"})
public class RestClazzAndClassroomController {

	@Autowired
	private IClazzAndClassroomService clazzAndClassroomService;
	
	@RequestMapping(value={"/api-admin-class-and-classroom"}, method=RequestMethod.GET)
	public List<ClazzAndClassroomModel> getAllClassAndClassrooms(HttpServletRequest request) {
		return clazzAndClassroomService.findAll();
	}
	
	@RequestMapping(value={"/api-admin-class-and-classroom"}, method=RequestMethod.POST)
	public ResponseEntity<?> getCreateClassAndClassroom(HttpServletRequest request, @RequestBody ClazzAndClassroomModel model) {
		Long rs = clazzAndClassroomService.save(model, SystemConstant.INSERT);
		if (rs == SystemConstant.DUPLICATE) {
			return new ResponseEntity<>(SystemConstant.DUPLICATE_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		else if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.ERROR_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(SystemConstant.OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-class-and-classroom"}, method=RequestMethod.PUT)
	public ResponseEntity<?> getUpdateClassAndClassroom(HttpServletRequest request, @RequestBody ClazzAndClassroomModel model) {
		Long rs = clazzAndClassroomService.save(model, SystemConstant.MODIFY);
		if (rs == SystemConstant.DUPLICATE) {
			return new ResponseEntity<>(SystemConstant.DUPLICATE_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		else if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.ERROR_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(SystemConstant.OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-class-and-classroom"}, method= RequestMethod.DELETE)
	public ResponseEntity<?> getDeleteClassAndClassroom(HttpServletRequest request, @RequestBody ClazzAndClassroomModel model) {
		Long rs = clazzAndClassroomService.delete(model);
		if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.DELETE_FAILED_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(SystemConstant.DELETE_OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
}
