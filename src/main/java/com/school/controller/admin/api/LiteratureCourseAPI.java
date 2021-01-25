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
import com.school.model.LiteratureCourseModel;
import com.school.model.UserModel;
import com.school.service.ILiteratureCourseService;
import com.school.utils.HttpUtil;
import com.school.utils.SessionUtil;

@WebServlet(urlPatterns = {"/api-admin-literaturecourse"})
public class LiteratureCourseAPI extends HttpServlet{

	private static final long serialVersionUID = 2366653363869338130L;

	@Inject
	private ILiteratureCourseService literatureCourseService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		List<LiteratureCourseModel> literatureCourseModel = literatureCourseService.findAll();
		mapper.writeValue(resp.getOutputStream(), literatureCourseModel);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		LiteratureCourseModel literatureCourseModel =  HttpUtil.of(req.getReader()).toModel(LiteratureCourseModel.class);
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null && model.getFullname() != null) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			literatureCourseModel.setCreatedBy(model.getFullname());
			literatureCourseModel.setCreatedDate(timestamp);
			literatureCourseModel.setModifiedDate(timestamp);
			Long id = literatureCourseService.save(literatureCourseModel);
			mapper.writeValue(resp.getOutputStream(), id);
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		LiteratureCourseModel literatureCourseModel =  HttpUtil.of(req.getReader()).toModel(LiteratureCourseModel.class);
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null && model.getFullname() != null) {
			literatureCourseModel.setModifiedBy(model.getFullname());
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			literatureCourseModel.setModifiedDate(timestamp);
			Long id = literatureCourseService.save(literatureCourseModel);
			mapper.writeValue(resp.getOutputStream(), id);
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		LiteratureCourseModel literatureCourseModel =  HttpUtil.of(req.getReader()).toModel(LiteratureCourseModel.class);
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null && model.getFullname() != null) {
			literatureCourseModel.setModifiedBy(model.getFullname());
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			literatureCourseModel.setModifiedDate(timestamp);
			Long id = literatureCourseService.delete(literatureCourseModel);
			mapper.writeValue(resp.getOutputStream(), id);
		}
	}
}
