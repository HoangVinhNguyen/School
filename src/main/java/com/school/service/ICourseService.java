package com.school.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.school.model.CourseModel;

public interface ICourseService {

	public List<CourseModel> findAll();
	public CourseModel findOne(long id);
	public CourseModel findOneByCode(String code);
	public CourseModel findOneByName(String name);
	public Long save(CourseModel courseModel, String method);
	public Long saveUser(CourseModel courseModel, String method);
	public Long delete(CourseModel courseModel);
	public int getTotalItem();
	
	public Long saveList(MultipartFile file);
	public Long downloadForm(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException;
	public Long getReport(HttpServletRequest request, HttpServletResponse response);
}
