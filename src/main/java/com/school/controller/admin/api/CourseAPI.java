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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.model.CourseModel;
import com.school.model.UserModel;
import com.school.service.ICourseService;
import com.school.utils.HttpUtil;
import com.school.utils.SessionUtil;

@WebServlet(urlPatterns = {"/api-admin-course"})
public class CourseAPI extends HttpServlet{

	private static final long serialVersionUID = 4030565494760933857L;
	
	@Inject
	private ICourseService courseService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		List<CourseModel> courseModel = courseService.findAll();
		mapper.writeValue(resp.getOutputStream(), courseModel);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		CourseModel courseModel =  HttpUtil.of(req.getReader()).toModel(CourseModel.class);
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null && model.getFullname() != null) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			courseModel.setCreatedBy(model.getFullname());
			courseModel.setCreatedDate(timestamp);
			courseModel.setModifiedBy(model.getModifiedBy());
			courseModel.setModifiedDate(timestamp);
			Long id = courseService.save(courseModel);
			mapper.writeValue(resp.getOutputStream(), id);
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		CourseModel courseModel =  HttpUtil.of(req.getReader()).toModel(CourseModel.class);
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null && model.getFullname() != null) {
			courseModel.setModifiedBy(model.getFullname());
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			courseModel.setModifiedDate(timestamp);
			Long id = courseService.save(courseModel);
			mapper.writeValue(resp.getOutputStream(), id);
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		CourseModel courseModel =  HttpUtil.of(req.getReader()).toModel(CourseModel.class);
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null && model.getFullname() != null) {
			courseModel.setModifiedBy(model.getFullname());
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			courseModel.setModifiedDate(timestamp);
			Long id = courseService.delete(courseModel);
			mapper.writeValue(resp.getOutputStream(), id);
		}
	}
}
