package com.school.admin.grade;

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

import com.school.admin.repository.GradeRepository;
import com.school.common.entity.Grade;
import com.school.common.entity.Level;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class GradeRepositoryTests {

	@Autowired
	private GradeRepository repo;
	
	@Test
	public void createGrade() {
		LocalDateTime time = LocalDateTime.now();
		Grade g = new Grade(1, "1", "of high school", new Level(2L));
		g.setCreatedDate(time);
		g.setCreatedBy("admin-test");
		Grade saved = repo.save(g);
		
		assertThat(saved.getId()).isGreaterThan(0);
	}
	
	@Test
	public void createMultiGrade() {
		LocalDateTime time = LocalDateTime.now();
		Grade g2 = new Grade(2, "Second Grade", "of high school", new Level(2L));
		g2.setCreatedDate(time);
		g2.setCreatedBy("admin-test");
		Grade g3 = new Grade(3, "Third Grade", "of high school", new Level(2L));
		g3.setCreatedDate(time);
		g3.setCreatedBy("admin-test");
		Grade g4 = new Grade(4, "Fourth Grade", "of high school", new Level(2L));
		g4.setCreatedDate(time);
		g4.setCreatedBy("admin-test");
		Grade g5 = new Grade(5, "Fifth Grade", "of high school", new Level(2L));
		g5.setCreatedDate(time);
		g5.setCreatedBy("admin-test");
		Grade g6 = new Grade(6, "Sixth Grade", "of junior high school", new Level(3L));
		g6.setCreatedDate(time);
		g6.setCreatedBy("admin-test");
		Grade g7 = new Grade(7, "Seventh Grade", "of junior high school", new Level(3L));
		g7.setCreatedDate(time);
		g7.setCreatedBy("admin-test");
		Grade g8 = new Grade(8, "Eighth Grade", "of junior high school", new Level(3L));
		g8.setCreatedDate(time);
		g8.setCreatedBy("admin-test");
		Grade g9 = new Grade(9, "Ninth Grade", "of junior high school", new Level(3L));
		g9.setCreatedDate(time);
		g9.setCreatedBy("admin-test");
		Grade g10 = new Grade(10, "Tenth Grade", "of high school", new Level(4L));
		g10.setCreatedDate(time);
		g10.setCreatedBy("admin-test");
		Grade g11 = new Grade(11, "Eleventh Grade", "of high school", new Level(4L));
		g11.setCreatedDate(time);
		g11.setCreatedBy("admin-test");
		Grade g12 = new Grade(12, "Twelve Grade", "of high school", new Level(4L));
		g12.setCreatedDate(time);
		g12.setCreatedBy("admin-test");
		
		repo.saveAll(List.of(g2, g3, g4, g5, g6, g7, g8, g9, g10, g11, g12));
	}
	
	@Test
	public void updateGrade() {
		Optional<Grade> op = Optional.ofNullable(repo.getGradeByCode(1));
		if (op.isPresent()) {
			Grade g = op.get();
			LocalDateTime modified = LocalDateTime.now();
			g.setName("First Grade");
			g.setModifiedDate(modified);
			g.setModifiedBy("admin-test");
			
			Grade saved = repo.save(g);
			assertThat(saved.getId()).isEqualTo(g.getId());
		}
	}
	
	@Test
	public void findAll() {
		Optional<List<Grade>> op = Optional.ofNullable(repo.findAll());
		if (op.isPresent()) {
			op.get().forEach(g -> System.out.println(g));
			assert(true);
		}
	}
	
	@Test
	public void deleteGrade() {
		Optional<Grade> op = Optional.ofNullable(repo.getGradeByCode(1));
		if (op.isPresent()) {
			Grade lvns = op.get();
			repo.deleteById(lvns.getId());
		}
	}
	
}
