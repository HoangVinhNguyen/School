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
import com.school.model.TeacherClassroomModel;
import com.school.model.UserModel;
import com.school.service.ITeacherClassroomService;
import com.school.utils.HttpUtil;
import com.school.utils.SessionUtil;

@WebServlet(urlPatterns = {"/api-admin-teacher"})
public class TeacherClassroomAPI extends HttpServlet {

	private static final long serialVersionUID = -6773555456454414119L;
	
	@Inject
	private ITeacherClassroomService teacherService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		List<TeacherClassroomModel> classModel = teacherService.findAll();
		mapper.writeValue(resp.getOutputStream(), classModel);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		TeacherClassroomModel teacherClassroomModel =  HttpUtil.of(req.getReader()).toModel(TeacherClassroomModel.class);
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null && model.getFullname() != null) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			teacherClassroomModel.setCreatedBy(model.getFullname());
			teacherClassroomModel.setCreatedDate(timestamp);
			teacherClassroomModel.setModifiedBy(model.getModifiedBy());
			teacherClassroomModel.setModifiedDate(timestamp);
			Long id = teacherService.save(teacherClassroomModel);
			mapper.writeValue(resp.getOutputStream(), id);
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		TeacherClassroomModel teacherClassroomModel =  HttpUtil.of(req.getReader()).toModel(TeacherClassroomModel.class);
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null && model.getFullname() != null) {
			teacherClassroomModel.setModifiedBy(model.getFullname());
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			teacherClassroomModel.setModifiedDate(timestamp);
			Long id = teacherService.save(teacherClassroomModel);
			mapper.writeValue(resp.getOutputStream(), id);
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		TeacherClassroomModel teacherClassroomModel =  HttpUtil.of(req.getReader()).toModel(TeacherClassroomModel.class);
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null && model.getFullname() != null) {
			teacherClassroomModel.setModifiedBy(model.getFullname());
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			teacherClassroomModel.setModifiedDate(timestamp);
			Long id = teacherService.delete(teacherClassroomModel);
			mapper.writeValue(resp.getOutputStream(), id);
		}
	}

}
