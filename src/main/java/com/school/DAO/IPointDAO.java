package com.school.DAO;

import java.util.List;

import com.school.entity.ClazzEntity;
import com.school.entity.CourseEntity;
import com.school.entity.PointEntity;
import com.school.entity.UserEntity;

public interface IPointDAO {
	
	public List<PointEntity> findAll();
	public PointEntity findOne(long id);
	public PointEntity findOneByUserCourseClazz(UserEntity teacher, UserEntity student, CourseEntity course, ClazzEntity clazz);
	public List<PointEntity> findAllByCourse(CourseEntity course);
	public List<PointEntity> findAllByClazz(ClazzEntity clazz);
	public List<PointEntity> findAllByTeacher(UserEntity teacher);
	public List<PointEntity> findAllByStudent(UserEntity student);
	public Long save(PointEntity pointEntity);
	public Long savePoint(PointEntity pointEntity);
	public Long delete(PointEntity pointEntity);
	public int getTotalItem();
}
