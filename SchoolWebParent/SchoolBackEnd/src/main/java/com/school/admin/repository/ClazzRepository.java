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

import com.school.common.entity.Clazz;
import com.school.common.entity.User;

public interface ClazzRepository extends JpaRepository<Clazz, Long>{

	@Query("SELECT c FROM Clazz c WHERE c.code = :code AND c.isDeleted = FALSE")
	public Clazz getClazzByCode(@Param("code") String code);
	
	@Query("SELECT c FROM Clazz c WHERE c.name = :name AND c.isDeleted = FALSE")
	public Clazz getClazzByName(@Param("name") String name);
	
	@Query("SELECT c FROM Clazz c WHERE c.id = ?1 AND c.isDeleted = FALSE")
	public Optional<Clazz> findById(Long id);
	
	@Query("SELECT c FROM Clazz c WHERE c.isDeleted = FALSE")
	public List<Clazz> findAll();
	
	@Query("SELECT c FROM Clazz c WHERE c.isDeleted = FALSE")
	public List<Clazz> findAll(Sort sort);
	
	@Query("UPDATE Clazz c SET c.isDeleted = TRUE WHERE c.id = ?1  AND c.isDeleted = FALSE")
	@Modifying
	public void deleteById(Long id);
	
	/*
	 * For paging.
	 */
	@Query("SELECT COUNT(c) FROM Clazz c WHERE c.id = :id AND c.isDeleted = FALSE")
	public Long countById(@Param("id") Long id);
	
	@Query("SELECT c FROM Clazz c WHERE c.isDeleted = FALSE AND CONCAT(c.id, ' ', c.code, ' ', c.name, ' ', c.description, ' ', c.grade.name) LIKE %?1%")
	public Page<Clazz> findAll(String keyword, Pageable pageable);
	
	@Query("SELECT c FROM Clazz c WHERE c.isDeleted = FALSE")
	public Page<Clazz> findAll(Pageable pageable);

	@Query("SELECT u FROM Clazz c JOIN c.users u JOIN u.roles r WHERE c.isDeleted = FALSE AND c.id=?1 "
			+ "AND r.name LIKE 'Teacher' AND u.isDeleted = FALSE AND CONCAT(u.firstName, ' ', u.email) LIKE %?2%")
	public Page<User> findAllTeacher(Long id, String keyword, Pageable pageable);

	@Query("SELECT u FROM Clazz c JOIN c.users u JOIN u.roles r WHERE c.isDeleted = FALSE AND c.id=?1 "
			+ "AND r.name LIKE 'Teacher' AND u.isDeleted = FALSE")
	public Page<User> findAllTeacher(Long id, Pageable pageable);
	
	@Query("SELECT u FROM Clazz c JOIN c.users u JOIN u.roles r WHERE c.isDeleted = FALSE AND c.id=?1 "
			+ "AND r.name LIKE 'Student' AND u.isDeleted = FALSE AND CONCAT(u.firstName, ' ', u.email) LIKE %?2%")
	public Page<User> findAllStudent(Long id, String keyword, Pageable pageable);
	
	@Query("SELECT u FROM Clazz c JOIN c.users u JOIN u.roles r WHERE c.isDeleted = FALSE AND c.id=?1 "
			+ "AND r.name LIKE 'Student' AND u.isDeleted = FALSE")
	public Page<User> findAllStudent(Long id, Pageable pageable);

}
