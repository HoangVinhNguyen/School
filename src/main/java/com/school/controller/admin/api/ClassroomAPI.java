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
import com.school.model.ClassroomModel;
import com.school.model.UserModel;
import com.school.service.IClassroomService;
import com.school.utils.HttpUtil;
import com.school.utils.SessionUtil;

@WebServlet(urlPatterns = {"/api-admin-classroom"})
public class ClassroomAPI extends HttpServlet{

	private static final long serialVersionUID = 9027587310194754726L;
	
	@Inject
	private IClassroomService classroomService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		List<ClassroomModel> classModel = classroomService.findAll();
		mapper.writeValue(resp.getOutputStream(), classModel);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ClassroomModel classroomModel =  HttpUtil.of(req.getReader()).toModel(ClassroomModel.class);
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null && model.getFullname() != null) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			classroomModel.setCreatedBy(model.getFullname());
			classroomModel.setCreatedDate(timestamp);
			classroomModel.setModifiedBy(model.getModifiedBy());
			classroomModel.setModifiedDate(timestamp);
			Long id = classroomService.save(classroomModel);
			mapper.writeValue(resp.getOutputStream(), id);
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ClassroomModel classroomModel =  HttpUtil.of(req.getReader()).toModel(ClassroomModel.class);
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null && model.getFullname() != null) {
			classroomModel.setModifiedBy(model.getFullname());
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			classroomModel.setModifiedDate(timestamp);
			//newModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
			Long id = classroomService.save(classroomModel);
			mapper.writeValue(resp.getOutputStream(), id);
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ClassroomModel classroomModel =  HttpUtil.of(req.getReader()).toModel(ClassroomModel.class);
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null && model.getFullname() != null) {
			classroomModel.setModifiedBy(model.getFullname());
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			classroomModel.setModifiedDate(timestamp);
			Long id = classroomService.delete(classroomModel);
			mapper.writeValue(resp.getOutputStream(), id);
		}
	}
}
