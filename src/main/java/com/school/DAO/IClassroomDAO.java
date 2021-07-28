package com.school.DAO;

import java.util.List;

import com.school.entity.ClassroomEntity;
import com.school.entity.GradeEntity;

public interface IClassroomDAO {

	public List<ClassroomEntity> findAll();
	public List<ClassroomEntity> findByGrade(GradeEntity gradeEntity);
	public ClassroomEntity findOne(long id);
	public ClassroomEntity findOneByCode(String code);
	public ClassroomEntity findOneByName(String name);
	public Long save(ClassroomEntity classroomEntity);
	public Long delete(ClassroomEntity classroomEntity);
	public int getTotalItem();
}
