package com.school.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.school.model.GradeModel;

public interface IGradeService {

	public List<GradeModel> findAll();
	public GradeModel findOne(long id);
	public GradeModel findOneByCode(String code);
	public GradeModel findOneByName(String name);
	public int getTotalItem();
	public Long save(GradeModel gradeModel, String method);
	public Long delete(GradeModel gradeModel);
	
	public Long saveList(MultipartFile file);
}
