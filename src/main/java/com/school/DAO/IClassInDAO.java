package com.school.DAO;

import java.util.List;

import com.school.entity.ClazzEntity;
import com.school.entity.GradeEntity;

public interface IClassInDAO {

	public List<ClazzEntity> findAll();
	public ClazzEntity findOne(long id);
	public ClazzEntity findOneByCode(String code);
	public ClazzEntity findOneByName(String name);
	public List<ClazzEntity> findAllByGreadId(GradeEntity entity);
	public ClazzEntity findOneByNameAndCodeAndGreadId(ClazzEntity entity);
	public Long save(ClazzEntity clazzEntity);
	public Long delete(ClazzEntity clazzEntity);
	public int getTotalItem();
}
