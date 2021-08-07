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
import com.school.model.UserAndClazzModel;
import com.school.service.IUserAndClazzService;

@RestController
@RequestMapping(value= {"/admin"})
public class RestUserAndCourseController {

	@Autowired
	private IUserAndClazzService userAndClazzService;
	
	@RequestMapping(value={"/api-admin-user-and-class"}, method=RequestMethod.GET)
	public List<UserAndClazzModel> getAllClazzs(HttpServletRequest request) {
		return userAndClazzService.findAll();
	}
	
	@RequestMapping(value={"/api-admin-user-and-class"}, method=RequestMethod.POST)
	public ResponseEntity<?> getCreateClazz(HttpServletRequest request, @RequestBody UserAndClazzModel model) {
		Long rs = userAndClazzService.save(model, SystemConstant.INSERT);
		if (rs == SystemConstant.DUPLICATE) {
			return new ResponseEntity<>(SystemConstant.DUPLICATE_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		else if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.ERROR_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(SystemConstant.OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-user-and-class"}, method=RequestMethod.PUT)
	public ResponseEntity<?> getUpdateClazz(HttpServletRequest request, @RequestBody UserAndClazzModel model) {
		Long rs = userAndClazzService.save(model, SystemConstant.MODIFY);
		if (rs == SystemConstant.DUPLICATE) {
			return new ResponseEntity<>(SystemConstant.DUPLICATE_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		else if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.ERROR_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(SystemConstant.OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-user-and-class"}, method= RequestMethod.DELETE)
	public ResponseEntity<?> getDeleteClazz(HttpServletRequest request, @RequestBody UserAndClazzModel model) {
		Long rs = userAndClazzService.delete(model);
		if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.DELETE_FAILED_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(SystemConstant.DELETE_OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
}
