package com.school.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.school.service.IPostStatusService;

@WebServlet(urlPatterns = {"/admin-poststatus"})
public class PostStatusController extends HttpServlet{

	private static final long serialVersionUID = -6192949334483858792L;

	@Inject
	private IPostStatusService postStatusService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/post_status/list.jsp");
		rd.forward(req, resp);
	}
}
