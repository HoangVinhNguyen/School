package com.school.admin.clazz;

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

import com.school.admin.repository.ClazzRepository;
import com.school.common.entity.Clazz;
import com.school.common.entity.Grade;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ClazzRepositoryTests {

	@Autowired
	private ClazzRepository repo;
	
	@Test
	public void createClazz() {
		LocalDateTime time = LocalDateTime.now();
		Clazz c = new Clazz("1c1", "1C1", "1c1 class", new Grade(1L));
		c.setCreatedDate(time);
		c.setCreatedBy("admin-test");
		Clazz saved = repo.save(c);
		
		assertThat(saved.getId()).isGreaterThan(0);
	}
	
	@Test
	public void createMultiClazz() {
		LocalDateTime time = LocalDateTime.now();
		Clazz c2 = new Clazz("2c1", "2C1", "2c1 class", new Grade(2L));
		c2.setCreatedDate(time);
		c2.setCreatedBy("admin-test");
		Clazz c3 = new Clazz("3c1", "3C1", "3c1 class", new Grade(3L));
		c3.setCreatedDate(time);
		c3.setCreatedBy("admin-test");
		Clazz c4 = new Clazz("6c1", "6C1", "6c1 class", new Grade(6L));
		c4.setCreatedDate(time);
		c4.setCreatedBy("admin-test");
		Clazz c5 = new Clazz("7c1", "7C1", "7c1 class", new Grade(7L));
		c5.setCreatedDate(time);
		c5.setCreatedBy("admin-test");
		Clazz c6 = new Clazz("10c1", "10C1", "10c1 class", new Grade(10L));
		c6.setCreatedDate(time);
		c6.setCreatedBy("admin-test");
		Clazz c7 = new Clazz("11c1", "11C1", "11c1 class", new Grade(11L));
		c7.setCreatedDate(time);
		c7.setCreatedBy("admin-test");
		
		repo.saveAll(List.of(c2, c3, c4, c5, c6, c7));
	}
	
	@Test
	public void updateClazz() {
		Optional<Clazz> op = Optional.ofNullable(repo.getClazzByCode("1c1"));
		if (op.isPresent()) {
			Clazz c = op.get();
			LocalDateTime modified = LocalDateTime.now();
			c.setName("1C2");
			c.setModifiedDate(modified);
			c.setModifiedBy("admin-test");
			
			Clazz saved = repo.save(c);
			assertThat(saved.getId()).isEqualTo(c.getId());
		}
	}
	
	@Test
	public void findAll() {
		Optional<List<Clazz>> op = Optional.ofNullable(repo.findAll());
		if (op.isPresent()) {
			op.get().forEach(c -> System.out.println(c));
			assert(true);
		}
	}
	
	@Test
	public void deleteClazz() {
		Optional<Clazz> op = Optional.ofNullable(repo.getClazzByCode("1c1"));
		if (op.isPresent()) {
			Clazz lvns = op.get();
			repo.deleteById(lvns.getId());
		}
	}
	
}
