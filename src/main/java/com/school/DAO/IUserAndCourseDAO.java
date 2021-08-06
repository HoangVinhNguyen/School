package com.school.DAO;

import java.util.List;

import com.school.entity.CourseEntity;
import com.school.entity.UserAndCourseEntity;
import com.school.entity.UserEntity;

public interface IUserAndCourseDAO {

	public List<UserAndCourseEntity> findAll();
	public UserAndCourseEntity findOne(UserEntity user, CourseEntity course);
	public UserAndCourseEntity findOneByUser(String userEmail);
	public UserAndCourseEntity findOneByUserId(Long id);
	public List<UserAndCourseEntity> findOneByCourseId(long id);
	public Long save(UserAndCourseEntity userAndCourseEntity);
	public Long delete(UserAndCourseEntity userAndCourseEntity);
	public int getTotalItem();
}
