package com.school.DAO;

import java.util.List;

import com.school.model.ClassroomModel;
import com.school.paging.Pageble;

public interface IClassroomDAO extends GenericDAO<ClassroomModel> {

	public List<ClassroomModel> findAll(Pageble pageble);
	public List<ClassroomModel> findAll();
	public ClassroomModel findOne(long id);
	public ClassroomModel findOneByCode(String code);
	public ClassroomModel findOneByName(String name);
	public Long save(ClassroomModel classroomModel);
	public Long delete(ClassroomModel classroomModel);
	public int getTotalItem();
}
