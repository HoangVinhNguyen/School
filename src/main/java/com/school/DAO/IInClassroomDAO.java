package com.school.DAO;

import java.util.List;

import com.school.entity.InClassroomEntity;

public interface IInClassroomDAO {

	public List<InClassroomEntity> findAll();
	public InClassroomEntity findOne(long id);
	public InClassroomEntity findOneByUser(String userEmail);
	public InClassroomEntity findOneByUserId(Long id);
	public List<InClassroomEntity> findOneByClass(String className);
	public Long save(InClassroomEntity inClassroomEntity);
	public Long delete(InClassroomEntity inClassroomEntity);
	public int getTotalItem();
}
