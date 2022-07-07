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

public interface StudentAndPointRepository extends JpaRepository<StudentAndPoint, Long>{

	@Query("SELECT NEW StudentAndPoint(at.id, at.student, at.point, at.coefficient, at.createdDate) FROM AcademicTranscript at "
			+ "WHERE at.isDeleted = FALSE "
			+ "AND at.teacher = :teacher "
			+ "AND at.course = :course "
			+ "AND at.clazz = :class")
	public List<StudentAndPoint> findStudentAcademicTranscript(
			@Param("teacher") User idTeacher,
			@Param("course") Course idCourse,
			@Param("class") Clazz idClass);

}
