package com.school.DAO;

import java.util.List;

import com.school.entity.ClassInEntity;
import com.school.entity.GradeEntity;

public interface IClassInDAO {

	public List<ClassInEntity> findAll();
	public ClassInEntity findOne(long id);
	public ClassInEntity findOneByCode(String code);
	public ClassInEntity findOneByName(String name);
	public List<ClassInEntity> findAllByGreadId(GradeEntity entity);
	public ClassInEntity findOneByNameAndCodeAndGreadId(ClassInEntity entity);
	public Long save(ClassInEntity classInEntity);
	public Long delete(ClassInEntity classInEntity);
	public int getTotalItem();
}
