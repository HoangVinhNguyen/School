package com.school.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.school.service.IUserService;

@WebServlet(urlPatterns = {"/admin-user"})
public class UserController extends HttpServlet {

	private static final long serialVersionUID = -6780426807882597935L;
	
	@Inject
	private IUserService userService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/user/list.jsp");
		rd.forward(req, resp);
	}
}
