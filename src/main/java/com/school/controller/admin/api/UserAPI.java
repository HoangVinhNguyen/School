package com.school.controller.admin.api;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.entity.ClassroomEntity;
import com.school.entity.UserEntity;
import com.school.service.IUserService;
import com.school.utils.HttpUtil;
import com.school.utils.SessionUtil;

@WebServlet(urlPatterns = {"/api-admin-user"})
public class UserAPI extends HttpServlet {

	private static final long serialVersionUID = 6519976315859170460L;

	@Inject
	private IUserService userService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		List<UserEntity> userEntity = userService.findAll();
		mapper.writeValue(resp.getOutputStream(), userEntity);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		UserEntity userEntity =  HttpUtil.of(req.getReader()).toModel(UserEntity.class);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(hashedPassword);
		UserEntity model = (UserEntity) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null && model.getFullname() != null) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			userEntity.setCreatedBy(model.getFullname());
			userEntity.setCreatedDate(timestamp);
			userEntity.setModifiedBy(model.getFullname());
			userEntity.setModifiedDate(timestamp);
			Long id = userService.save(userEntity);
			mapper.writeValue(resp.getOutputStream(), id);
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		UserEntity userEntity =  HttpUtil.of(req.getReader()).toModel(UserEntity.class);
		UserEntity model = (UserEntity) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null && model.getFullname() != null) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			userEntity.setModifiedBy(model.getFullname());
			userEntity.setModifiedDate(timestamp);
			Long id = userService.save(userEntity);
			mapper.writeValue(resp.getOutputStream(), id);
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		UserEntity userEntity =  HttpUtil.of(req.getReader()).toModel(UserEntity.class);
		UserEntity model = (UserEntity) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null && model.getFullname() != null) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			userEntity.setModifiedBy(model.getFullname());
			userEntity.setModifiedDate(timestamp);
			Long id = userService.delete(userEntity);
			mapper.writeValue(resp.getOutputStream(), id);
		}
	}
	
}
