package com.school.admin.level;

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

import com.school.admin.repository.LevelRepository;
import com.school.common.entity.Level;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class LevelRepositoryTests {

	@Autowired
	private LevelRepository repo;
	
	@Test
	public void createLevel() {
		LocalDateTime time = LocalDateTime.now();
		Level lv = new Level("ns", "Nursery School", "2-5 years old");
		lv.setCreatedDate(time);
		lv.setCreatedBy("admin-test");
		Level saved = repo.save(lv);
		
		assertThat(saved.getId()).isGreaterThan(0);
	}
	
	@Test
	public void createMultiLevel() {
		LocalDateTime time = LocalDateTime.now();
		Level lvPS = new Level("ps", "Primary School", "5-11 years old");
		lvPS.setCreatedDate(time);
		lvPS.setCreatedBy("admin-test");
		Level lvJHS = new Level("jhs", "Junior High School", "11-15 years old");
		lvJHS.setCreatedDate(time);
		lvJHS.setCreatedBy("admin-test");
		Level lvHS = new Level("hs", "High School", "15-18 years old");
		lvHS.setCreatedDate(time);
		lvHS.setCreatedBy("admin-test");
		Level lvUNI = new Level("uni", "University", "for bachelor");
		lvUNI.setCreatedDate(time);
		lvUNI.setCreatedBy("admin-test");
		Level lvCL = new Level("cleg", "College", "for bachelor");
		lvCL.setCreatedDate(time);
		lvCL.setCreatedBy("admin-test");
		repo.saveAll(List.of(lvPS, lvJHS, lvHS, lvUNI, lvCL));
	}
	
	@Test
	public void updateLevel() {
		Optional<Level> op = Optional.ofNullable(repo.getLevelByCode("ns"));
		if (op.isPresent()) {
			Level lvns = op.get();
			LocalDateTime modified = LocalDateTime.now();
			
			lvns.setDescription("2-5 years old, for children");
			lvns.setModifiedDate(modified);
			lvns.setModifiedBy("admin-test");
			
			Level saved = repo.save(lvns);
			assertThat(saved.getId()).isEqualTo(lvns.getId());
		}
	}
	
	@Test
	public void findAll() {
		Optional<List<Level>> op = Optional.ofNullable(repo.findAll());
		if (op.isPresent()) {
			op.get().forEach(lv -> System.out.println(lv));
			assert(true);
		}
	}
	
	@Test
	public void deleteLevel() {
		Optional<Level> op = Optional.ofNullable(repo.getLevelByCode("ns"));
		if (op.isPresent()) {
			Level lvns = op.get();
			repo.deleteById(lvns.getId());
		}
	}
	
}
