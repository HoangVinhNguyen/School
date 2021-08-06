package com.school.controller.admin.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.school.model.UserModel;
import com.school.service.IUserService;

@RestController
@RequestMapping(value= {"/admin"})
public class RestUserController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/api-admin-user", method=RequestMethod.GET)
	public List<UserModel> getAllUser(HttpServletRequest request) {
		List<UserModel> users = userService.findAll();
		return users;
	}
	
	@RequestMapping(value="/api-admin-user", method=RequestMethod.POST)
	public ResponseEntity<?> getAddUser(@RequestBody UserModel user) {
		Long rs = userService.save(user, SystemConstant.INSERT);
		if (rs == SystemConstant.DUPLICATE) {
			return new ResponseEntity<>(SystemConstant.DUPLICATE_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		else if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.ERROR_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(SystemConstant.OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value="/api-admin-user", method=RequestMethod.PUT)
	public ResponseEntity<?> getUpdateUser(@RequestBody UserModel user) {
		Long rs = userService.save(user,SystemConstant.MODIFY);
		if (rs == SystemConstant.DUPLICATE) {
			return new ResponseEntity<>(SystemConstant.DUPLICATE_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		else if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.ERROR_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(SystemConstant.OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value="/api-admin-user-course", method=RequestMethod.POST)
	public ResponseEntity<?> getUpdateUserCourse(@RequestBody UserModel user) {
		Long rs = userService.saveCourse(user,SystemConstant.MODIFY);
		if (rs == SystemConstant.DUPLICATE) {
			return new ResponseEntity<>(SystemConstant.DUPLICATE_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		else if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.ERROR_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(SystemConstant.OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value="/api-admin-user-class", method=RequestMethod.POST)
	public ResponseEntity<?> getUpdateUserClass(@RequestBody UserModel user) {
		Long rs = userService.saveClazz(user,SystemConstant.MODIFY);
		if (rs == SystemConstant.DUPLICATE) {
			return new ResponseEntity<>(SystemConstant.DUPLICATE_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		else if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.ERROR_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(SystemConstant.OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value="/api-admin-user", method=RequestMethod.DELETE)
	public ResponseEntity<?> getDeleteUser(@RequestBody UserModel user) {
		Long rs = userService.delete(user);
		if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.DELETE_FAILED_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(SystemConstant.DELETE_OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value="/api-admin-user-file-teacher", method=RequestMethod.POST)
	public Long getFileTeacher(@RequestParam(name="file") MultipartFile file) {
		return userService.saveList(file, SystemConstant.ROLE_TEACHER);
	}
	
	@RequestMapping(value="/api-admin-user-file-student", method=RequestMethod.POST)
	public Long getFileStudent(@RequestParam(name="file") MultipartFile file) {
		return userService.saveList(file, SystemConstant.ROLE_STUDENT);
	}
}
