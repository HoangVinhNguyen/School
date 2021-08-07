package com.school.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.school.model.ClazzModel;
import com.school.model.GradeModel;

public interface IClazzService {

	public List<ClazzModel> findAll();
	public ClazzModel findOne(long id);
	public ClazzModel findOneByCode(String code);
	public ClazzModel findOneByName(String name);
	public List<ClazzModel> findAllByLevelGreadId(GradeModel gradeModel);
	public int getTotalItem();
	public Long save(ClazzModel clazzModel, String method);
	public Long delete(ClazzModel clazzModel);
	
	public Long saveList(MultipartFile file);
	public Long downloadForm(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException;
	public Long getReport(HttpServletRequest request, HttpServletResponse response);
}
