package com.school.DAO;

import java.util.List;

import com.school.entity.TeacherClassroomEntity;

public interface ITeacherClassroomDAO {
	
	public List<TeacherClassroomEntity> findAll();
	public TeacherClassroomEntity findOne(long id);
	public TeacherClassroomEntity findOneByClassroom(String classroomId);
	public List<TeacherClassroomEntity> findAllByClassroom(String className);
	public List<TeacherClassroomEntity> findAllByTeacherEmail(String userEmail);
	public List<TeacherClassroomEntity> findAllByStudentEmail(String userEmail);
	public Long save(TeacherClassroomEntity teacherClassroomEntity);
	public Long savePoint(TeacherClassroomEntity teacherClassroomEntity);
	public Long delete(TeacherClassroomEntity teacherClassroomEntity);
	public int getTotalItem();
}
