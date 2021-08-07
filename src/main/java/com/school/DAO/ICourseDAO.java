package com.school.DAO;

import java.util.List;

import com.school.entity.CourseEntity;

public interface ICourseDAO {

	public List<CourseEntity> findAll();
	public CourseEntity findOne(long id);
	public CourseEntity findOneByCode(String code);
	public CourseEntity findOneByName(String name);
	public Long save(CourseEntity courseEntity);
	public Long delete(CourseEntity courseEntity);
	public int getTotalItem();
}
