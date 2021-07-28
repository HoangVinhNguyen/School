package com.school.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.school.model.ClassroomModel;
import com.school.model.GradeModel;

public interface IClassroomService {

	public List<ClassroomModel> findAll();
	public List<ClassroomModel> findByGrade(GradeModel gradeModel);
	public ClassroomModel findOne(long id);
	public ClassroomModel findOneByCode(String code);
	public ClassroomModel findOneByName(String name);
	public int getTotalItem();
	public Long save(ClassroomModel classroomModel, String method);
	public Long delete(ClassroomModel classroomModel);
	
	public Long saveList(MultipartFile file);
}
