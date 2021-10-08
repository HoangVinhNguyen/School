package com.school.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.common.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

	@Query("SELECT c FROM Course c WHERE c.code = :code AND c.isDeleted = FALSE")
	public Course getCourseByCode(@Param("code") String code);
	
	@Query("SELECT c FROM Course c WHERE c.name = :name AND c.isDeleted = FALSE")
	public Course getCourseByName(@Param("name") String name);
	
	@Query("SELECT c FROM Course c WHERE c.id = ?1 AND c.isDeleted = FALSE")
	public Optional<Course> findById(Long id);
	
	@Query("SELECT c FROM Course c WHERE c.isDeleted = FALSE")
	public List<Course> findAll();
	
	@Query("SELECT c FROM Course c WHERE c.isDeleted = FALSE")
	public List<Course> findAll(Sort sort);
	
	@Query("UPDATE Course c SET c.isDeleted = TRUE WHERE c.id = ?1  AND c.isDeleted = FALSE")
	@Modifying
	public void deleteById(Long id);
	
	/*
	 * For paging.
	 */
	@Query("SELECT COUNT(c) FROM Course c WHERE c.id = :id AND c.isDeleted = FALSE")
	public Long countById(@Param("id") Long id);
	
	@Query("SELECT c FROM Course c WHERE c.isDeleted = FALSE AND CONCAT(c.id, ' ', c.code, ' ', c.name, ' ', c.description) LIKE %?1%")
	public Page<Course> findAll(String keyword, Pageable pageable);
	
	@Query("SELECT c FROM Course c WHERE c.isDeleted = FALSE")
	public Page<Course> findAll(Pageable pageable);
}
