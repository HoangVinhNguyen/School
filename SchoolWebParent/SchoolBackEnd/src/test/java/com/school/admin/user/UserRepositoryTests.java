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
import org.springframework.test.annotation.Rollback;

import com.school.admin.repository.RoleRepository;
import com.school.admin.repository.UserRepository;
import com.school.common.entity.Role;
import com.school.common.entity.User;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private UserRepository repo;

	@Autowired
	private RoleRepository roleRepo;

	@Test
	public void createUserWithOneRole() {
		LocalDateTime createdDate = LocalDateTime.now();
		Role role = new Role(1);

		User user = new User("Vinh", "Nguyen", "vinhx@gmail.com", "12345678");
		user.setCreatedDate(createdDate);
		user.setCreatedBy("admin-test");
		user.addRole(role);
		LocalDate dob = LocalDate.now();
		user.setDob(dob);
		User savedUser = repo.save(user);

		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void createUserWithTwoRoles() {
		LocalDateTime createdDate = LocalDateTime.now();
		Role role1 = new Role(1);
		Role role2 = new Role(2);

		User user = new User("Hai", "Nguyen", "hai@gmail.com", "123");
		user.setCreatedDate(createdDate);
		user.setCreatedBy("admin-test");
		user.addRole(role1);
		user.addRole(role2);
		User savedUser = repo.save(user);

		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testListAllUSer() {
		Iterable<User> listUsers = repo.findAllUser();
		listUsers.forEach(u -> System.out.println(u));
	}
	
	@Test
	public void testGetUserById() {
		Optional<User> op = repo.findById(2);
		if (op.isPresent()) {
			User user = op.get();
			System.out.println(user);
		}
	}
	
	@Test
	public void testUpdateUserDetail() {
		Optional<User> op = repo.findById(2);
		if (op.isPresent()) {
			User user = op.get();
			user.setPhone("023456789");
			User savedUser = repo.save(user);
			
			assertThat(savedUser.getId()).isEqualTo(2);
		}
	}
	
	@Test
	public void testUpdateUserRole() {
		Optional<User> op = repo.findById(3);
		if (op.isPresent()) {
			Role role2 = new Role(2);
			Role role3 = new Role(3);
			User user = op.get();
			user.getRoles().remove(role2);
			user.addRole(role3);
			User savedUser = repo.save(user);
			
			assertThat(savedUser.getId()).isEqualTo(3);
		}
	}
	
	@Test
	public void testGetUserByEmail() {
		Optional<User> op = Optional.of(repo.getUserByEmail("vinh@gmail.com"));
		if (op.isPresent()) {
			User user = op.get();
			System.out.println(user);
			
			assertThat(user.getId()).isEqualTo(2);
		}
	}
	
	@Test
	public void testCountById() {
		Integer id = 3;
		Long countById = repo.countById(id);
		
		assertThat(countById).isNotNull().isGreaterThan(0);
	}
	
	@Test
	public void testEnableUser() {
		Optional<User> op = Optional.of(repo.getUserByEmail("vinh@gmail.com"));
		if (op.isPresent()) {
			User user = op.get();
			repo.updateEnableStatus(user.getId(), true);
		}
	}
	
	@Test
	public void testDeleteUser() {
		Optional<User> op = Optional.of(repo.getUserByEmail("vinh@gmail.com"));
		if (op.isPresent()) {
			User user = op.get();
			repo.deleteUser(user.getId());
			System.out.println(user);
		}
	}
}
