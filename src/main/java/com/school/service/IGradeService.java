package com.school.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.school.model.GradeModel;
import com.school.model.LevelGradeModel;

public interface IGradeService {

	public List<GradeModel> findAll();
	public GradeModel findOne(long id);
	public GradeModel findOneByCode(String code);
	public GradeModel findOneByName(String name);
	public List<GradeModel> findAllByLevelGreadId(LevelGradeModel levelGradeModel);
	public int getTotalItem();
	public Long save(GradeModel gradeModel, String method);
	public Long delete(GradeModel gradeModel);
	
	public Long saveList(MultipartFile file);
	public Long downloadForm(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException;
	public Long getReport(HttpServletRequest request, HttpServletResponse response);
}
