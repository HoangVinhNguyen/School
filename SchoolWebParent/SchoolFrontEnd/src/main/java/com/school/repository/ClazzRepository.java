package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.common.entity.Clazz;

public interface ClazzRepository extends JpaRepository<Clazz, Long>{

	@Query("SELECT clazz FROM Clazz clazz WHERE clazz.id = :id AND clazz.isDeleted = FALSE")
	public Clazz findClazzById(@Param("id") Long id);
}
