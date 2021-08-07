package com.school.DAO;

import java.util.List;

import com.school.entity.ClassroomEntity;
import com.school.entity.ClazzAndClassroomEntity;
import com.school.entity.ClazzEntity;

public interface IClazzAndClassroomDAO {

	public List<ClazzAndClassroomEntity> findAll();
	public ClazzAndClassroomEntity findOne(ClazzEntity clazz, ClassroomEntity classroom);
	public ClazzAndClassroomEntity findOneById(long id);
	public List<ClazzAndClassroomEntity> findOneByClazzName(String name);
	public List<ClazzAndClassroomEntity> findOneByClassroomName(String name);
	public Long save(ClazzAndClassroomEntity entity);
	public Long delete(ClazzAndClassroomEntity entity);
	public int getTotalItem();
}
