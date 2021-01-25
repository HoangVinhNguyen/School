package com.school.service;

import java.util.List;

import com.school.model.ClassroomModel;
import com.school.paging.Pageble;

public interface IClassroomService {

	public List<ClassroomModel> findAll(Pageble pageble);
	public List<ClassroomModel> findAll();
	public ClassroomModel findOne(long id);
	public ClassroomModel findOneByCode(String code);
	public ClassroomModel findOneByName(String name);
	public int getTotalItem();
	public Long save(ClassroomModel classroomModel);
	public Long delete(ClassroomModel classroomModel);
}
