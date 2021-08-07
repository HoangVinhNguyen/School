package com.school.DAO;

import java.util.List;

import com.school.entity.CourseEntity;
import com.school.entity.UserAndCourseEntity;
import com.school.entity.UserEntity;

public interface IUserAndCourseDAO {

	public List<UserAndCourseEntity> findAll();
	public UserAndCourseEntity findOne(UserEntity user, CourseEntity course);
	public UserAndCourseEntity findOneByID(long id);
	public List<UserAndCourseEntity> findOneByUserEmail(String userEmail);
	public List<UserAndCourseEntity> findOneByCourseName(String name);
	public Long save(UserAndCourseEntity userAndCourseEntity);
	public Long delete(UserAndCourseEntity userAndCourseEntity);
	public int getTotalItem();
}
