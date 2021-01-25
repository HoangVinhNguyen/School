package com.school.controller.teacher.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.constant.SystemConstant;
import com.school.model.UserModel;
import com.school.service.ILiteratureCourseService;
import com.school.utils.SessionUtil;

@WebServlet(urlPatterns = {"/teacher-literaturecourse"})
public class LiteratureCourseAPI extends HttpServlet {

	private static final long serialVersionUID = -3011629129111772537L;
	
	@Inject
	private ILiteratureCourseService literatureService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
		if (model.getRole().getCode().equals(SystemConstant.TEACHER)) {
			
		}
	}
	
}
