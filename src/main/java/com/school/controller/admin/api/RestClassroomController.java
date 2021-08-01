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
import com.school.model.ClassroomModel;
import com.school.service.IClassroomService;

@RestController
@RequestMapping(value= {"/admin"})
public class RestClassroomController {

	@Autowired
	private IClassroomService classroomService;
	
	@RequestMapping(value={"/api-admin-classroom"}, method=RequestMethod.GET)
	public List<ClassroomModel> getAllClassrooms(HttpServletRequest request) {
		return classroomService.findAll();
	}
	
	@RequestMapping(value={"/api-admin-classroom"}, method=RequestMethod.POST)
	public ResponseEntity<?> getCreateClassroom(HttpServletRequest request, @RequestBody ClassroomModel classroom) {
		Long rs = classroomService.save(classroom, SystemConstant.INSERT);
		if (rs == SystemConstant.DUPLICATE) {
			return new ResponseEntity<>(SystemConstant.DUPLICATE_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		else if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.ERROR_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(SystemConstant.OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-classroom"}, method=RequestMethod.PUT)
	public ResponseEntity<?> getUpdateClassroom(HttpServletRequest request, @RequestBody ClassroomModel classroom) {
		Long rs = classroomService.save(classroom, SystemConstant.MODIFY);
		if (rs == SystemConstant.DUPLICATE) {
			return new ResponseEntity<>(SystemConstant.DUPLICATE_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		else if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.ERROR_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(SystemConstant.OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-classroom"}, method= RequestMethod.DELETE)
	public ResponseEntity<?> getDeleteClassroom(HttpServletRequest request, @RequestBody ClassroomModel classroom) {
		Long rs = classroomService.delete(classroom);
		if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.DELETE_FAILED_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(SystemConstant.DELETE_OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-classroom-file"}, method= RequestMethod.POST)
	public Long sendFileClassroom(@RequestParam(name="file") MultipartFile file) {
		return classroomService.saveList(file);
	}
	
	@RequestMapping(value={"/api-admin-classroom-file-form-download"}, method=RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		classroomService.downloadForm(request, response);
	}
	
	@RequestMapping(value={"/api-admin-classroom-file-report-download"}, method=RequestMethod.GET)
	public void downloadReport(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		classroomService.getReport(request, response);
	}
}
