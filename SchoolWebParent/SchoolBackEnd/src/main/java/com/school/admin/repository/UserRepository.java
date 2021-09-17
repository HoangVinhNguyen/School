package com.school.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.school.common.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	
	@Query("SELECT u FROM User u WHERE u.email = :email AND u.isDeleted = 0")
	public User getUserByEmail(@Param("email") String email);
	
	@Query("SELECT u FROM User u WHERE u.isDeleted = 0")
	public List<User> findAllUser();
	
	@Query("SELECT COUNT(u) FROM User u WHERE u.id = :id AND u.isDeleted = 0")
	public Long countById(@Param("id") Integer id);
	
	@Query("SELECT u FROM User u WHERE u.isDeleted = 0 AND CONCAT(u.id, ' ', u.email, ' ', u.firstName, ' ', u.lastName) LIKE %?1%")
	public Page<User> findAll(String keyword, Pageable pageable);
	
	@Query("UPDATE User u SET u.enabled = ?2 WHERE u.id = ?1 AND u.isDeleted = 0")
	@Modifying
	public void updateEnableStatus(Integer id, boolean enabled);
	
	@Query("UPDATE User u SET u.isDeleted = 1 WHERE u.id = ?1  AND u.isDeleted = 0")
	@Modifying
	public void deleteUser(Integer id);
}
