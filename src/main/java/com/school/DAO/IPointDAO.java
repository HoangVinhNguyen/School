package com.school.DAO;

import java.util.List;

import com.school.entity.PointEntity;

public interface IPointDAO {
	
	public List<PointEntity> findAll();
	public PointEntity findOne(long id);
	public PointEntity findOneByClassroom(String classroomId);
	public List<PointEntity> findAllByClassroom(String className);
	public List<PointEntity> findAllByTeacherEmail(String userEmail);
	public List<PointEntity> findAllByStudentEmail(String userEmail);
	public Long save(PointEntity pointEntity);
	public Long savePoint(PointEntity pointEntity);
	public Long delete(PointEntity pointEntity);
	public int getTotalItem();
}
