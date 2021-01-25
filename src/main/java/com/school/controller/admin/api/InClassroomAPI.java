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
import com.school.model.InClassroomModel;
import com.school.model.UserModel;
import com.school.service.IInClassroomService;
import com.school.utils.HttpUtil;
import com.school.utils.SessionUtil;

@WebServlet(urlPatterns = {"/api-admin-inclassroom"})
public class InClassroomAPI extends HttpServlet{

	private static final long serialVersionUID = -3996389888519704391L;
	
	@Inject
	private IInClassroomService inClassroomService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		List<InClassroomModel> inClassModel = inClassroomService.findAll();
		mapper.writeValue(resp.getOutputStream(), inClassModel);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		InClassroomModel inClassModel =  HttpUtil.of(req.getReader()).toModel(InClassroomModel.class);
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null && model.getFullname() != null) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			inClassModel.setCreatedBy(model.getFullname());
			inClassModel.setCreatedDate(timestamp);
			inClassModel.setModifiedDate(timestamp);
			Long id = inClassroomService.save(inClassModel);
			mapper.writeValue(resp.getOutputStream(), id);
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		InClassroomModel inClassModel =  HttpUtil.of(req.getReader()).toModel(InClassroomModel.class);
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null && model.getFullname() != null) {
			inClassModel.setModifiedBy(model.getFullname());
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			inClassModel.setModifiedDate(timestamp);
			Long id = inClassroomService.save(inClassModel);
			mapper.writeValue(resp.getOutputStream(), id);
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		InClassroomModel inClassModel =  HttpUtil.of(req.getReader()).toModel(InClassroomModel.class);
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null && model.getFullname() != null) {
			inClassModel.setModifiedBy(model.getFullname());
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			inClassModel.setModifiedDate(timestamp);
			Long id = inClassroomService.delete(inClassModel);
			mapper.writeValue(resp.getOutputStream(), id);
		}
	}
	
}
