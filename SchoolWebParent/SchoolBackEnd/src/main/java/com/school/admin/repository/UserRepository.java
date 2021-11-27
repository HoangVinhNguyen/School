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
import org.springframework.stereotype.Repository;

import com.school.common.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	
	@Query("SELECT u FROM User u WHERE u.email = :email AND u.isDeleted = FALSE")
	public User findUserByEmail(@Param("email") String email);
	
	@Query("SELECT u FROM User u WHERE u.id = :id AND u.isDeleted = FALSE")
	public User findUserById(@Param("id") Long id);

	@Query("SELECT u FROM User u JOIN u.roles r WHERE u.id = :id AND u.isDeleted = FALSE AND r.name LIKE 'ROLE_TEACHER'")
	public User findByIdTeacherRest(Long id);
	@Query("SELECT u FROM User u JOIN u.roles r WHERE u.id = :id AND u.isDeleted = FALSE AND r.name LIKE 'ROLE_STUDENT'")
	public User findByIdStudentRest(Long id);

	@Query("SELECT u FROM User u WHERE u.isDeleted = FALSE")
	public List<User> findAllUser();
	@Query("SELECT u FROM User u JOIN u.roles usr WHERE u.isDeleted = FALSE AND usr.name LIKE 'ROLE_TEACHER'")
	public List<User> findAllTeacher();
	@Query("SELECT u FROM User u JOIN u.roles usr WHERE u.isDeleted = FALSE AND usr.name LIKE 'ROLE_TEACHER'")
	public List<User> findAllTeacher(Sort sort);
	@Query("SELECT u FROM User u JOIN u.roles usr WHERE u.isDeleted = FALSE AND usr.name LIKE 'ROLE_STUDENT'")
	public List<User> findAllStudent();
	@Query("SELECT u FROM User u JOIN u.roles usr WHERE u.isDeleted = FALSE AND usr.name LIKE 'ROLE_STUDENT'")
	public List<User> findAllStudent(Sort sort);
	
	@Query("SELECT u FROM User u WHERE u.isDeleted = FALSE")
	public List<User> findAll(Sort sort);
	
	@Query("SELECT u FROM User u WHERE u.id = ?1 AND u.isDeleted = FALSE")
	public Optional<User> findById(Long id);
	
	@Query("SELECT COUNT(u) FROM User u WHERE u.id = :id AND u.isDeleted = FALSE")
	public Long countById(@Param("id") Long id);
	
	@Query("SELECT u FROM User u WHERE u.isDeleted = FALSE AND CONCAT(u.id, ' ', u.email, ' ', u.firstName, ' ', u.lastName) LIKE %?1%")
	public Page<User> findAll(String keyword, Pageable pageable);
	
	@Query("SELECT u FROM User u WHERE u.isDeleted = FALSE")
	public Page<User> findAll(Pageable pageable);
	
	@Query("UPDATE User u SET u.enabled = ?2 WHERE u.id = ?1 AND u.isDeleted = FALSE")
	@Modifying
	public void updateEnableStatus(Long id, boolean enabled);
	
	@Query("UPDATE User u SET u.isDeleted = TRUE WHERE u.id = ?1  AND u.isDeleted = FALSE")
	@Modifying
	public void deleteById(Long id);

	
	@Query("SELECT u FROM User u LEFT JOIN u.roles r WHERE r.name LIKE 'ROLE_TEACHER' AND u.isDeleted = FALSE AND CONCAT(u.id, ' ', u.email, ' ', u.firstName, ' ', u.lastName) LIKE %?1%")
	public Page<User> findAllTeacher(String keyword, Pageable pageable);
	@Query("SELECT u FROM User u LEFT JOIN u.roles r WHERE r.name LIKE 'ROLE_TEACHER' AND u.isDeleted = FALSE")
	public Page<User> findAllTeacher(Pageable pageable);
	
	@Query("SELECT u FROM User u LEFT JOIN u.roles r WHERE r.name LIKE 'ROLE_STUDENT' AND u.isDeleted = FALSE AND CONCAT(u.id, ' ', u.email, ' ', u.firstName, ' ', u.lastName) LIKE %?1%")
	public Page<User> findAllStudent(String keyword, Pageable pageable);
	@Query("SELECT u FROM User u LEFT JOIN u.roles r WHERE r.name LIKE 'ROLE_STUDENT' AND u.isDeleted = FALSE")
	public Page<User> findAllStudent(Pageable pageable);
	
	@Query("SELECT u FROM User u JOIN u.roles usr JOIN u.clazzes cl "
			+ "WHERE u.isDeleted = FALSE AND usr.name LIKE 'ROLE_TEACHER' "
			+ "AND cl.id = :idClass")
	public List<User> findAllTeacherByClassId(@Param("idClass") Long idClass);
	@Query("SELECT u FROM User u JOIN u.roles usr JOIN u.clazzes cl "
			+ "WHERE u.isDeleted = FALSE AND usr.name LIKE 'ROLE_STUDENT' "
			+ "AND cl.id = :idClass")
	public List<User> findAllStudentByClassId(@Param("idClass") Long idClass);

}
