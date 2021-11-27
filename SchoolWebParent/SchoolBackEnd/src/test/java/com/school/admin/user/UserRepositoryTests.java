package com.school.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import com.school.admin.repository.UserRepository;
import com.school.common.common.SystemConstant;
import com.school.common.entity.Role;
import com.school.common.entity.User;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private UserRepository repo;

	@Test
	public void createUserWithOneRole() {
		LocalDateTime createdDate = LocalDateTime.now();
		Role role = new Role(1L);

		User user = new User("Vinh", "Nguyen", "admin@gmail.com", "$2a$12$lBqU0yNuKrJindFuRJRfAesjMmwNYki/.Dr6MiCTDqoBb.4wn4f8u");
		user.setCreatedDate(createdDate);
		user.setCreatedBy("admin-test");
		user.addRole(role);
		LocalDate dob = LocalDate.now();
		user.setDob(dob);
		user.setEnabled(true);
		User savedUser = repo.save(user);

		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void createUserWithTwoRoles() {
		LocalDateTime createdDate = LocalDateTime.now();
		Role role1 = new Role(1L);
		Role role2 = new Role(2L);

		User user = new User("Hai", "Nguyen", "hai@gmail.com", "123");
		user.setCreatedDate(createdDate);
		user.setCreatedBy("admin-test");
		user.addRole(role1);
		user.addRole(role2);
		User savedUser = repo.save(user);

		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testListAllUser() {
		Iterable<User> listUsers = repo.findAllUser();
		listUsers.forEach(u -> System.out.println(u));
	}
	@Test
	public void testListAllTeacher() {
		Iterable<User> listUsers = repo.findAllTeacher();
		listUsers.forEach(u -> System.out.println(u));
	}
	@Test
	public void testListAllTeacherSort() {
		Iterable<User> listUsers = repo.findAllTeacher(Sort.by(SystemConstant.FIRST_NAME).ascending());
		listUsers.forEach(u -> System.out.println(u));
	}
	@Test
	public void testListAllStudent() {
		Iterable<User> listUsers = repo.findAllStudent();
		listUsers.forEach(u -> System.out.println(u));
	}
	
	@Test
	public void testGetUserById() {
		Optional<User> op = repo.findById(2L);
		if (op.isPresent()) {
			User user = op.get();
			System.out.println(user);
		}
	}
	
	@Test
	public void testUpdateUserDetail() {
		Optional<User> op = repo.findById(2L);
		if (op.isPresent()) {
			User user = op.get();
			user.setPhone("023456789");
			User savedUser = repo.save(user);
			
			assertThat(savedUser.getId()).isEqualTo(2);
		}
	}
	
	@Test
	public void testUpdateUserRole() {
		Optional<User> op = repo.findById(2L);
		if (op.isPresent()) {
			Role role1 = new Role(1L);
			Role role3 = new Role(3L);
			User user = op.get();
			user.getRoles().remove(role1);
			user.addRole(role3);
			user.addRole(role1);
			User savedUser = repo.save(user);
			
			assertThat(savedUser.getId()).isEqualTo(2);
		}
	}
	
	@Test
	public void testGetUserByEmail() {
		Optional<User> op = Optional.of(repo.findUserByEmail("vinh@gmail.com"));
		if (op.isPresent()) {
			User user = op.get();
			System.out.println(user);
			
			assertThat(user.getId()).isEqualTo(2);
		}
	}
	
	@Test
	public void testCountById() {
		Long id = 3L;
		Long countById = repo.countById(id);
		
		assertThat(countById).isNotNull().isGreaterThan(0);
	}
	
	@Test
	public void testEnableUser() {
		Optional<User> op = Optional.of(repo.findUserByEmail("vinh@gmail.com"));
		if (op.isPresent()) {
			User user = op.get();
			repo.updateEnableStatus(user.getId(), true);
		}
	}
	
	@Test
	public void testDeleteUser() {
		Optional<User> op = Optional.of(repo.findUserByEmail("vinh@gmail.com"));
		if (op.isPresent()) {
			User user = op.get();
			repo.deleteById(user.getId());
			System.out.println(user);
		}
	}
}
