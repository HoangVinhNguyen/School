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

import com.school.common.entity.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Long>{

	@Query("SELECT c FROM Classroom c WHERE c.code = :code AND c.isDeleted = FALSE")
	public Classroom getClassroomByCode(@Param("code") String code);
	
	@Query("SELECT c FROM Classroom c WHERE c.name = :name AND c.isDeleted = FALSE")
	public Classroom getClassroomByName(@Param("name") String name);
	
	@Query("SELECT c FROM Classroom c WHERE c.id = ?1 AND c.isDeleted = FALSE")
	public Optional<Classroom> findById(Long id);
	
	@Query("SELECT c FROM Classroom c WHERE c.isDeleted = FALSE")
	public List<Classroom> findAll();
	
	@Query("SELECT c FROM Classroom c WHERE c.isDeleted = FALSE")
	public List<Classroom> findAll(Sort sort);
	
	@Query("UPDATE Classroom c SET c.isDeleted = TRUE WHERE c.id = ?1  AND c.isDeleted = FALSE")
	@Modifying
	public void deleteById(Long id);
	
	/*
	 * For paging.
	 */
	@Query("SELECT COUNT(c) FROM Classroom c WHERE c.id = :id AND c.isDeleted = FALSE")
	public Long countById(@Param("id") Long id);
	
	@Query("SELECT c FROM Classroom c WHERE c.isDeleted = FALSE AND CONCAT(c.id, ' ', c.code, ' ', c.name, ' ', c.description) LIKE %?1%")
	public Page<Classroom> findAll(String keyword, Pageable pageable);
	
	@Query("SELECT c FROM Classroom c WHERE c.isDeleted = FALSE AND c.name LIKE %?1%")
	public List<Classroom> searchKeyWord(String keyword);
	
	@Query("SELECT c FROM Classroom c WHERE c.isDeleted = FALSE")
	public Page<Classroom> findAll(Pageable pageable);
}
