package com.school.DAO;

import java.util.List;

import com.school.entity.GradeEntity;

public interface IGradeDAO {

	public List<GradeEntity> findAll();
	public GradeEntity findOne(long id);
	public GradeEntity findOneByCode(String code);
	public GradeEntity findOneByName(String name);
	public Long save(GradeEntity gradeEntity);
	public Long delete(GradeEntity gradeEntity);
	public int getTotalItem();
}
