package com.school.controller.admin;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.school.model.UserModel;
import com.school.service.IUserService;
import com.school.utils.FormUtil;
import com.school.utils.SessionUtil;

@WebServlet(urlPatterns = {"/admin-home"})
public class HomeController extends HttpServlet{

	private static final long serialVersionUID = -5476413585523898127L;
	
	@Inject
	private IUserService userService;
	
	private ResourceBundle messageBdl = ResourceBundle.getBundle("message");
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action != null && action.equals("login")) {
			String message = req.getParameter("message");
			String alert = req.getParameter("alert");
			if (message != null && alert != null) {
				req.setAttribute("message", messageBdl.getString(message));
				req.setAttribute("alert", alert);
			}
			RequestDispatcher rd = req.getRequestDispatcher("/views/login.jsp");
			rd.forward(req, resp);
		}
		else if (action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(req, "USERMODEL");
			resp.sendRedirect(req.getContextPath() + "/home");
		}
		else {
			RequestDispatcher rd = req.getRequestDispatcher("/views/admin/home.jsp");
			rd.forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action != null && action.equals("login")) {
			UserModel model = FormUtil.toModel(UserModel.class, req);
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        String hashedPassword = passwordEncoder.encode(model.getPassword());
			model = userService.findByEmailAndPasswordAndStatus(model.getEmail(), hashedPassword, 1);
			if (model != null) {
				SessionUtil.getInstance().putValue(req, "USERMODEL", model);
				if (model.getRole().getCode().equals("STUDENT")) {
					resp.sendRedirect(req.getContextPath() + "/home");
				}
				else if(model.getRole().getCode().equals("TEACHER")) {
					resp.sendRedirect(req.getContextPath() + "/teacher");
				}
				else if (model.getRole().getCode().equals("ADMIN")){
					resp.sendRedirect(req.getContextPath() + "/admin-home");
				}
			}
			else {
				resp.sendRedirect(req.getContextPath() + "/login?action=login&message=username_password_invalid&alert=danger");
			}
		}
	}
}
