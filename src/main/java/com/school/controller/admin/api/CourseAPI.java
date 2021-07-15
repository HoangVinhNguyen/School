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
import com.school.entity.CourseEntity;
import com.school.entity.UserEntity;
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
		List<CourseEntity> courseEntity = courseService.findAll();
		mapper.writeValue(resp.getOutputStream(), courseEntity);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		CourseEntity courseEntity =  HttpUtil.of(req.getReader()).toModel(CourseEntity.class);
		UserEntity model = (UserEntity) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null && model.getFullname() != null) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			courseEntity.setCreatedBy(model.getFullname());
			courseEntity.setCreatedDate(timestamp);
			courseEntity.setModifiedBy(model.getModifiedBy());
			courseEntity.setModifiedDate(timestamp);
			Long id = courseService.save(courseEntity);
			mapper.writeValue(resp.getOutputStream(), id);
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		CourseEntity courseEntity =  HttpUtil.of(req.getReader()).toModel(CourseEntity.class);
		UserEntity model = (UserEntity) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null && model.getFullname() != null) {
			courseEntity.setModifiedBy(model.getFullname());
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			courseEntity.setModifiedDate(timestamp);
			Long id = courseService.save(courseEntity);
			mapper.writeValue(resp.getOutputStream(), id);
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		CourseEntity courseEntity =  HttpUtil.of(req.getReader()).toModel(CourseEntity.class);
		UserEntity model = (UserEntity) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null && model.getFullname() != null) {
			courseEntity.setModifiedBy(model.getFullname());
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			courseEntity.setModifiedDate(timestamp);
			Long id = courseService.delete(courseEntity);
			mapper.writeValue(resp.getOutputStream(), id);
		}
	}
}
