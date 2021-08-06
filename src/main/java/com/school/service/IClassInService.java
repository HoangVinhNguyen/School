package com.school.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.school.model.ClassInModel;
import com.school.model.GradeModel;

public interface IClassInService {

	public List<ClassInModel> findAll();
	public ClassInModel findOne(long id);
	public ClassInModel findOneByCode(String code);
	public ClassInModel findOneByName(String name);
	public List<ClassInModel> findAllByLevelGreadId(GradeModel gradeModel);
	public int getTotalItem();
	public Long save(ClassInModel classInModel, String method);
	public Long saveClassroom(ClassInModel classInModel, String method);
	public Long delete(ClassInModel classInModel);
	
	public Long saveList(MultipartFile file);
	public Long downloadForm(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException;
	public Long getReport(HttpServletRequest request, HttpServletResponse response);
}
