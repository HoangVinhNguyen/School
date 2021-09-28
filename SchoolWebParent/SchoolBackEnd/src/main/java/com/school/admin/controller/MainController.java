package com.school.admin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.school.admin.util.StaticUtil;
import com.school.common.common.SystemConstant;

@Controller
public class MainController {

	@GetMapping({"", "/", "/home"})
	public String getHomePage(Model model) {
		StaticUtil.setTitleAndStatic(model, SystemConstant.TITLE_HOME, List.of("common.js"), List.of("style.css"));
		return "index";
	}
	
	@GetMapping("/login")
	public String viewLoginPage(Model model) {
		StaticUtil.setTitleAndStatic(model, SystemConstant.TITLE_LOGIN);
		return "login";
	}
	
	@GetMapping("/error")
	public String viewErrorPage(Model model) {
		StaticUtil.setTitleAndStatic(model, SystemConstant.TITLE_ERROR);
		return "error";
	}
}
