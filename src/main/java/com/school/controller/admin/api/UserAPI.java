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
import com.school.model.ClassroomModel;
import com.school.model.UserModel;
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
		List<UserModel> userModel = userService.findAll();
		mapper.writeValue(resp.getOutputStream(), userModel);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		UserModel userModel =  HttpUtil.of(req.getReader()).toModel(UserModel.class);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(userModel.getPassword());
        userModel.setPassword(hashedPassword);
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null && model.getFullname() != null) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			userModel.setCreatedBy(model.getFullname());
			userModel.setCreatedDate(timestamp);
			userModel.setModifiedBy(model.getFullname());
			userModel.setModifiedDate(timestamp);
			Long id = userService.save(userModel);
			mapper.writeValue(resp.getOutputStream(), id);
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		UserModel userModel =  HttpUtil.of(req.getReader()).toModel(UserModel.class);
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null && model.getFullname() != null) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			userModel.setModifiedBy(model.getFullname());
			userModel.setModifiedDate(timestamp);
			Long id = userService.save(userModel);
			mapper.writeValue(resp.getOutputStream(), id);
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		UserModel userModel =  HttpUtil.of(req.getReader()).toModel(UserModel.class);
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null && model.getFullname() != null) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			userModel.setModifiedBy(model.getFullname());
			userModel.setModifiedDate(timestamp);
			Long id = userService.delete(userModel);
			mapper.writeValue(resp.getOutputStream(), id);
		}
	}
	
}
