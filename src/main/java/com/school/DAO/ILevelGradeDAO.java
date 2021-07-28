package com.school.DAO;

import java.util.List;

import com.school.entity.LevelGradeEntity;

public interface ILevelGradeDAO {

	public List<LevelGradeEntity> findAll();
	public LevelGradeEntity findOne(long id);
	public LevelGradeEntity findOneByCode(String code);
	public LevelGradeEntity findOneByName(String name);
	public Long save(LevelGradeEntity levelGradeEntity);
	public Long delete(LevelGradeEntity levelGradeEntity);
	public int getTotalItem();
}
