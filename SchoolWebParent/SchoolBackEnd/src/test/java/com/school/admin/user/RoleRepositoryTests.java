package com.school.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.school.admin.repository.RoleRepository;
import com.school.common.entity.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {

	@Autowired
	private RoleRepository repo;
	
	@Test
	public void testCreateFirstRole() {
		Role role = new Role("Admin", "manage everything");
		LocalDateTime createDate = LocalDateTime.now();
		role.setCreatedBy("amdin-test");
		role.setCreatedDate(createDate);
		Role savedRole = repo.save(role);
		assertThat(savedRole.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateRoles() {
		LocalDateTime createDate = LocalDateTime.now();
		Role roleAdmin = new Role("Admin", "manage everything");
		roleAdmin.setCreatedBy("amdin-test");
		roleAdmin.setCreatedDate(createDate);
		Role roleTeacher = new Role("Teacher", "manage student, activity teach");
		roleTeacher.setCreatedBy("amdin-test");
		roleTeacher.setCreatedDate(createDate);
		Role roleStudent = new Role("Student", "take result study");
		roleStudent.setCreatedBy("amdin-test");
		roleStudent.setCreatedDate(createDate);
		repo.saveAll(List.of(roleAdmin, roleTeacher, roleStudent));
	}
	
	@Test
	public void testUpdateRole() {
		LocalDateTime modifiedDate = LocalDateTime.now();
		Optional<Role> roleOpt = repo.findById(3);
		Role savedRole = null;
		if (roleOpt.isPresent()) {
			Role role = roleOpt.get();
			role.setModifiedBy("admin-test");
			role.setModifiedDate(modifiedDate);
			role.setDecription("take result study and schedule");
			savedRole = repo.save(role);
		}
		assertThat(savedRole.getId()).isEqualTo(3);
	}
}
