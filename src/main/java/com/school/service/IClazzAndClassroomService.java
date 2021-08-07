package com.school.service;

import java.util.List;

import com.school.model.ClassroomModel;
import com.school.model.ClazzAndClassroomModel;
import com.school.model.ClazzModel;

public interface IClazzAndClassroomService {

	public List<ClazzAndClassroomModel> findAll();
	public ClazzAndClassroomModel findOne(ClazzModel clazz, ClassroomModel classroom);
	public List<ClazzAndClassroomModel> findOneByClazzName(String name);
	public List<ClazzAndClassroomModel> findOneByClassroomName(String name);
	public Long save(ClazzAndClassroomModel model, String method);
	public Long delete(ClazzAndClassroomModel model);
	public int getTotalItem();
}
