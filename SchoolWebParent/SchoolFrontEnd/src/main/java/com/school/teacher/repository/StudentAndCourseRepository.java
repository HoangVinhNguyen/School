package com.school.teacher.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.common.entity.Clazz;
import com.school.common.entity.Course;
import com.school.common.entity.StudentAndCourse;
import com.school.common.entity.StudentAndPoint;
import com.school.common.entity.User;

public interface StudentAndCourseRepository extends JpaRepository<StudentAndCourse, Long>{

	@Query("SELECT NEW StudentAndCourse(at.id, at.student, at.teacher, at.clazz, at.course, at.point, at.coefficient, at.createdDate) "
			+ "FROM AcademicTranscript at "
			+ "WHERE at.isDeleted = FALSE "
			+ "AND at.student = :student")
	public List<StudentAndCourse> findAcademicTranscriptByStudentId(
			@Param("student") User idStudent);

}
