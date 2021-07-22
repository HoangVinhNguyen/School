package com.school.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value= {"/", "/home"}, method=RequestMethod.GET)
	public String getHome(HttpServletRequest request, HttpServletResponse response) {
		return "home";
	}
	
	@RequestMapping(value= {"/403"}, method=RequestMethod.GET)
	public String get403(HttpServletRequest request, HttpServletResponse response) {
		return "403";
	}
}
