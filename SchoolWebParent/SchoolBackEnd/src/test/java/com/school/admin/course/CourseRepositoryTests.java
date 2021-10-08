package com.school.admin.course;

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

import com.school.admin.repository.CourseRepository;
import com.school.common.entity.Course;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CourseRepositoryTests {

	@Autowired
	private CourseRepository repo;
	
	@Test
	public void createCourse() {
		LocalDateTime time = LocalDateTime.now();
		Course c = new Course("m", "Math", "Math Course");
		c.setCreatedDate(time);
		c.setCreatedBy("admin-test");
		Course saved = repo.save(c);
		
		assertThat(saved.getId()).isGreaterThan(0);
	}
	
	@Test
	public void createMultiCourse() {
		LocalDateTime time = LocalDateTime.now();
		Course c2 = new Course("p", "Physics", "Physics Course");
		c2.setCreatedDate(time);
		c2.setCreatedBy("admin-test");
		Course c3 = new Course("c", "Chemistry", "Chemistry Course");
		c3.setCreatedDate(time);
		c3.setCreatedBy("admin-test");
		Course c4 = new Course("b", "Biology", "Biology Course");
		c4.setCreatedDate(time);
		c4.setCreatedBy("admin-test");
		Course c5 = new Course("g", "Geography", "Geography Course");
		c5.setCreatedDate(time);
		c5.setCreatedBy("admin-test");
		Course c6 = new Course("fa", "Fine Art", "Fine Art Course");
		c6.setCreatedDate(time);
		c6.setCreatedBy("admin-test");
		Course c7 = new Course("l", "Literature", "Literature Course");
		c7.setCreatedDate(time);
		c7.setCreatedBy("admin-test");
		Course c8 = new Course("ms", "Music", "Music Course");
		c8.setCreatedDate(time);
		c8.setCreatedBy("admin-test");
		
		repo.saveAll(List.of(c2, c3, c4, c5, c6, c7));
	}
	
	@Test
	public void updateCourse() {
		Optional<Course> op = Optional.ofNullable(repo.getCourseByCode("m"));
		if (op.isPresent()) {
			Course c = op.get();
			LocalDateTime modified = LocalDateTime.now();
			c.setName("Mathematics");
			c.setModifiedDate(modified);
			c.setModifiedBy("admin-test");
			
			Course saved = repo.save(c);
			assertThat(saved.getId()).isEqualTo(c.getId());
		}
	}
	
	@Test
	public void findAll() {
		Optional<List<Course>> op = Optional.ofNullable(repo.findAll());
		if (op.isPresent()) {
			op.get().forEach(c -> System.out.println(c));
			assert(true);
		}
	}
	
	@Test
	public void deleteCourse() {
		Optional<Course> op = Optional.ofNullable(repo.getCourseByCode("ms"));
		if (op.isPresent()) {
			Course lvns = op.get();
			repo.deleteById(lvns.getId());
		}
	}
	
}
