package com.school.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value= {"/"}, method = RequestMethod.GET)
	public String getHome(HttpServletRequest request) {
		System.out.println("home");
		return "home";
	}
	
	@RequestMapping(value= {"/home"}, method = RequestMethod.GET)
	public String getHome2(HttpServletRequest request) {
		System.out.println("home2");
		return "home2";
	}
}
