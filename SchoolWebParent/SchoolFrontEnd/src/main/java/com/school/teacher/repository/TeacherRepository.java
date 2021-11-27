package com.school.teacher.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.common.dto.SavePointBodyReq;
import com.school.common.entity.AcademicTranscript;
import com.school.common.entity.Clazz;
import com.school.common.entity.Course;
import com.school.common.entity.User;

public interface TeacherRepository extends JpaRepository<User, Long>{
	
	@Query("SELECT u FROM User u JOIN u.roles r WHERE u.isDeleted = FALSE AND u.id = :id "
			+ "AND r.name LIKE 'ROLE_TEACHER'")
	public User findUserById(@Param("id") Long id);

	@Query("SELECT cl FROM User u JOIN u.clazzes cl "
			+ "WHERE u.email = :email AND u.isDeleted = FALSE")
	public List<Clazz> findClassesOfEmailTeacher(@Param("email") String email);
	
	@Query("SELECT s FROM User u JOIN u.clazzes cl JOIN cl.users s JOIN s.roles r "
			+ "WHERE u.email = :email AND u.isDeleted = FALSE AND r.name LIKE 'ROLE_STUDENT' "
			+ "AND cl.id = :id")
	public List<User> findStudentInClass(@Param("id") Long id, @Param("email") String email);

	@Query("SELECT c FROM User u JOIN u.courses c "
			+ "WHERE u.email = :email AND u.isDeleted = FALSE")
	public List<Course> findCourses(@Param("email") String email);
	
}
