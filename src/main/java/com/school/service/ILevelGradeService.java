package com.school.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.school.model.LevelGradeModel;

public interface ILevelGradeService {

	public List<LevelGradeModel> findAll();
	public LevelGradeModel findOne(long id);
	public LevelGradeModel findOneByCode(String code);
	public LevelGradeModel findOneByName(String name);
	public int getTotalItem();
	public Long save(LevelGradeModel levelGradeModel, String method);
	public Long delete(LevelGradeModel levelGradeModel);
	
	public Long saveList(MultipartFile file);
}
