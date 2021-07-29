package com.school.DAO;

import java.util.List;

import com.school.entity.InCourseEntity;

public interface IInCourseDAO {

	public List<InCourseEntity> findAll();
	public InCourseEntity findOne(long id);
	public InCourseEntity findOneByUser(String userEmail);
	public InCourseEntity findOneByUserId(Long id);
	public List<InCourseEntity> findOneByCourse(String courseName);
	public Long save(InCourseEntity inCourseEntity);
	public Long delete(InCourseEntity inCourseEntity);
	public int getTotalItem();
}
