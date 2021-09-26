package com.school.admin.util;

import java.util.List;

import org.springframework.ui.Model;

import com.school.common.common.SystemConstant;

public class StaticUtil {

	public static void setTitleAndStatic(Model model, String title, List<String> js, List<String> css) {
		model.addAttribute(SystemConstant.PAGE_TILE, title);
		model.addAttribute(SystemConstant.JS_FILE, js);
		model.addAttribute(SystemConstant.CSS_FILE, css);
	}
	
	public static void setTitleAndStatic(Model model, String title) {
		model.addAttribute(SystemConstant.PAGE_TILE, title);
		model.addAttribute(SystemConstant.JS_FILE, null);
		model.addAttribute(SystemConstant.CSS_FILE, null);
	}
}
