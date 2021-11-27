package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.common.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	@Query("SELECT u FROM User u WHERE u.email = :email AND u.isDeleted = FALSE")
	public User findUserByEmail(@Param("email") String email);

	@Query("SELECT u FROM User u WHERE u.id = :id AND u.isDeleted = FALSE")
	public User findUserById(@Param("id") Long id);
}
