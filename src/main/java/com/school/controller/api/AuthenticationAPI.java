package com.school.controller.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.school.model.UserModel;
import com.school.service.IUserService;
import com.school.utils.HttpUtil;
import com.school.utils.SessionUtil;

@WebServlet(urlPatterns = {"/api-login"})
public class AuthenticationAPI extends HttpServlet{

	private static final long serialVersionUID = -4172613936859666716L;

	@Inject
	private IUserService userService;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		UserModel userModel =  HttpUtil.of(req.getReader()).toModel(UserModel.class);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pass = userModel.getPassword();
        userModel = userService.findByEmailAndPasswordAndStatus(userModel.getEmail(), userModel.getPassword(), 0);
		if (userModel != null && passwordEncoder.matches(pass, userModel.getPassword())) {
			SessionUtil.getInstance().putValue(req, "USERMODEL", userModel);
			if (userModel.getRole().getCode().equals("USER")) {
				resp.sendRedirect(req.getContextPath() + "/home");
			}
			else if (userModel.getRole().getCode().equals("ADMIN")){
				mapper.writeValue(resp.getOutputStream(), new JSONPObject("authentication", true));
			}
		}
		else {
			resp.sendRedirect(req.getContextPath() + "/login?action=login&message=username_password_invalid&alert=danger");
		}
	}
}
