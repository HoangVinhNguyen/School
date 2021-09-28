package com.school.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.common.entity.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long>{

	@Query("SELECT g FROM Grade g WHERE g.code = :code AND g.isDeleted = FALSE")
	public Grade getLevelByCode(@Param("code") String code);
	
	@Query("SELECT g FROM Grade g WHERE g.name = :name AND g.isDeleted = FALSE")
	public Grade getLevelByName(@Param("name") String name);
	
	@Query("SELECT g FROM Grade g WHERE g.id = ?1 AND g.isDeleted = FALSE")
	public Optional<Grade> findById(Long id);
	
	@Query("SELECT g FROM Grade g WHERE g.isDeleted = FALSE")
	public List<Grade> findAll();
	
	@Query("UPDATE Grade g SET g.isDeleted = TRUE WHERE g.id = ?1  AND g.isDeleted = FALSE")
	@Modifying
	public void deleteById(Long id);
	
	/*
	 * For paging.
	 */
	@Query("SELECT COUNT(g) FROM Grade g WHERE g.id = :id AND g.isDeleted = FALSE")
	public Long countById(@Param("id") Long id);
	
	@Query("SELECT g FROM Grade g WHERE g.isDeleted = FALSE AND CONCAT(g.id, ' ', g.code, ' ', g.name, ' ', g.level.id) LIKE %?1%")
	public Page<Grade> findAll(String keyword, Pageable pageable);
	
	@Query("SELECT g FROM Grade g WHERE g.isDeleted = FALSE")
	public Page<Grade> findAll(Pageable pageable);
}
