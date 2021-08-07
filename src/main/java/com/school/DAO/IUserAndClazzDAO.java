package com.school.DAO;

import java.util.List;

import com.school.entity.ClazzEntity;
import com.school.entity.UserAndClazzEntity;
import com.school.entity.UserEntity;

public interface IUserAndClazzDAO {

	public List<UserAndClazzEntity> findAll();
	public UserAndClazzEntity findOne(UserEntity user, ClazzEntity clazz);
	public UserAndClazzEntity findOneByID(long id);
	public List<UserAndClazzEntity> findOneByUserEmail(String userEmail);
	public List<UserAndClazzEntity> findOneByClazzName(String name);
	public Long save(UserAndClazzEntity userAndCourseEntity);
	public Long delete(UserAndClazzEntity userAndCourseEntity);
	public int getTotalItem();
}
