package com.school.admin.classroom;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.school.admin.repository.ClassroomRepository;
import com.school.common.entity.Classroom;
import com.school.common.entity.Clazz;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ClassroomRepositoryTests {

	@Autowired
	private ClassroomRepository repo;
	
	@Test
	public void createClassroom() {
		LocalDateTime time = LocalDateTime.now();
		Clazz clazz = new Clazz(1L);
		Classroom c = new Classroom("cl1", "1CL", "classroom 1");
		c.addClass(clazz);
		c.setCreatedDate(time);
		c.setCreatedBy("admin-test");
		Classroom saved = repo.save(c);
		
		assertThat(saved.getId()).isGreaterThan(0);
	}
	
	@Test
	public void createMultiClassroom() {
		LocalDateTime time = LocalDateTime.now();
		Classroom c2 = new Classroom("cl2", "2CL", "classroom 2");
		c2.setCreatedDate(time);
		c2.setCreatedBy("admin-test");
		Classroom c3 = new Classroom("cl3", "3CL", "classroom 3");
		c3.setCreatedDate(time);
		c3.setCreatedBy("admin-test");
		Classroom c4 = new Classroom("cl4", "4CL", "classroom 4");
		c4.setCreatedDate(time);
		c4.setCreatedBy("admin-test");
		Classroom c5 = new Classroom("cl5", "5CL", "classroom 5");
		c5.setCreatedDate(time);
		c5.setCreatedBy("admin-test");
		Classroom c6 = new Classroom("cl6", "6CL", "classroom 6");
		c6.setCreatedDate(time);
		c6.setCreatedBy("admin-test");
		Classroom c7 = new Classroom("cl7", "7CL", "classroom 7");
		c7.setCreatedDate(time);
		c7.setCreatedBy("admin-test");
		
		repo.saveAll(List.of(c2, c3, c4, c5, c6, c7));
	}
	
	@Test
	public void updateClassroom() {
		Optional<Classroom> op = Optional.ofNullable(repo.getClassroomByCode("cl1"));
		if (op.isPresent()) {
			Classroom c = op.get();
			LocalDateTime modified = LocalDateTime.now();
			c.setName("1ACL");
			c.setModifiedDate(modified);
			c.setModifiedBy("admin-test");
			
			Classroom saved = repo.save(c);
			assertThat(saved.getId()).isEqualTo(c.getId());
		}
	}
	
	@Test
	public void findAll() {
		Optional<List<Classroom>> op = Optional.ofNullable(repo.findAll());
		if (op.isPresent()) {
			op.get().forEach(c -> System.out.println(c));
			assert(true);
		}
	}
	
	@Test
	public void deleteClassroom() {
		Optional<Classroom> op = Optional.ofNullable(repo.getClassroomByCode("cl1"));
		if (op.isPresent()) {
			Classroom lvns = op.get();
			repo.deleteById(lvns.getId());
		}
	}
	
}
