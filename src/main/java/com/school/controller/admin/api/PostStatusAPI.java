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
import com.school.entity.PostStatusModel;
import com.school.entity.UserEntity;
import com.school.service.IPostStatusService;
import com.school.utils.HttpUtil;
import com.school.utils.SessionUtil;

@WebServlet(urlPatterns = {"/api-admin-poststatus"})
public class PostStatusAPI extends HttpServlet{

	private static final long serialVersionUID = 2502767128989819920L;
	
	@Inject
	private IPostStatusService postStatusService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		List<PostStatusModel> postStatusModel = postStatusService.findAll();
		mapper.writeValue(resp.getOutputStream(), postStatusModel);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		PostStatusModel postStatusModel =  HttpUtil.of(req.getReader()).toModel(PostStatusModel.class);
		UserEntity model = (UserEntity) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model != null && model.getFullname() != null) {
			postStatusModel.setModifiedBy(model.getFullname());
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			postStatusModel.setModifiedDate(timestamp);
			Long id = postStatusService.delete(postStatusModel);
			mapper.writeValue(resp.getOutputStream(), id);
		}
	}
}
