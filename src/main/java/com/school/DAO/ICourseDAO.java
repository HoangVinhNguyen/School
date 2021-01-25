package com.school.DAO;

import java.util.List;

import com.school.model.ClassroomModel;
import com.school.model.CourseModel;
import com.school.paging.Pageble;

public interface ICourseDAO extends GenericDAO<CourseModel> {

	public List<CourseModel> findAll(Pageble pageble);
	public List<CourseModel> findAll();
	public CourseModel findOne(long id);
	public CourseModel findOneByCode(String code);
	public CourseModel findOneByName(String name);
	public Long save(CourseModel courseModel);
	public Long delete(CourseModel courseModel);
	public int getTotalItem();
}
