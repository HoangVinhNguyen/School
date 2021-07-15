package com.school.DAO;

import java.util.List;

import com.school.entity.ClassroomEntity;

public interface IClassroomDAO {

	public List<ClassroomEntity> findAll();
	public ClassroomEntity findOne(long id);
	public ClassroomEntity findOneByCode(String code);
	public ClassroomEntity findOneByName(String name);
	public Long save(ClassroomEntity classroomEntity);
	public Long delete(ClassroomEntity classroomEntity);
	public int getTotalItem();
}
