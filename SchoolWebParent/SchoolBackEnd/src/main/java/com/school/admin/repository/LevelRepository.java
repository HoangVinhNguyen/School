package com.school.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.common.entity.Level;

public interface LevelRepository extends JpaRepository<Level, Long>{

	@Query("SELECT lv FROM Level lv WHERE lv.code = :code AND lv.isDeleted = FALSE")
	public Level getLevelByCode(@Param("code") String code);
	
	@Query("SELECT lv FROM Level lv WHERE lv.name = :name AND lv.isDeleted = FALSE")
	public Level getLevelByName(@Param("name") String name);
	
	@Query("SELECT lv FROM Level lv WHERE lv.id = ?1 AND lv.isDeleted = FALSE")
	public Optional<Level> findById(Long id);
	
	@Query("SELECT lv FROM Level lv WHERE lv.isDeleted = FALSE")
	public List<Level> findAll();
	
	@Query("UPDATE Level lv SET lv.isDeleted = TRUE WHERE lv.id = ?1  AND lv.isDeleted = FALSE")
	@Modifying
	public void deleteById(Long id);
	
	/*
	 * For paging.
	 */
	@Query("SELECT COUNT(lv) FROM Level lv WHERE lv.id = :id AND lv.isDeleted = FALSE")
	public Long countById(@Param("id") Long id);
	
	@Query("SELECT lv FROM Level lv WHERE lv.isDeleted = FALSE AND CONCAT(lv.id, ' ', lv.code, ' ', lv.name) LIKE %?1%")
	public Page<Level> findAll(String keyword, Pageable pageable);
	
	@Query("SELECT lv FROM Level lv WHERE lv.isDeleted = FALSE")
	public Page<Level> findAll(Pageable pageable);
}
