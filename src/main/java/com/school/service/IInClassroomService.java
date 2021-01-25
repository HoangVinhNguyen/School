package com.school.service;

import java.util.List;

import com.school.model.InClassroomModel;
import com.school.paging.Pageble;

public interface IInClassroomService {

	public List<InClassroomModel> findAll(Pageble pageble);
	public List<InClassroomModel> findAll();
	public InClassroomModel findOne(long id);
	public InClassroomModel findOneByUser(String userEmail);
	public List<InClassroomModel> findOneByClass(String className);
	public Long save(InClassroomModel inClassroomModel);
	public Long delete(InClassroomModel inClassroomModel);
	public int getTotalItem();
}
