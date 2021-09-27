package com.school.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.school.common.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	
	@Query("SELECT u FROM User u WHERE u.email = :email AND u.isDeleted = FALSE")
	public User getUserByEmail(@Param("email") String email);
	
	@Query("SELECT u FROM User u WHERE u.isDeleted = FALSE")
	public List<User> findAllUser();
	
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
}
