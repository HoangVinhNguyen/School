package com.school.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.common.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
