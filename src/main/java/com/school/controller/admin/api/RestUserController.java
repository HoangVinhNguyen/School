package com.school.controller.admin.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;
import com.school.constant.SystemConstant;
import com.school.model.RoleModel;
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
	public Long getAddUser(@RequestBody UserModel user) {
		return userService.save(user, SystemConstant.INSERT);
	}
	
	@RequestMapping(value="/api-admin-user", method=RequestMethod.PUT)
	public Long getUpdateUser(@RequestBody UserModel user) {
		return userService.save(user,SystemConstant.MODIFY);
	}
	
	@RequestMapping(value="/api-admin-user", method=RequestMethod.DELETE)
	public Long getDeleteUser(@RequestBody UserModel user) {
		return userService.delete(user);
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
