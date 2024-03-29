package com.school.teacher.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.common.entity.AcademicTranscript;
import com.school.common.entity.Clazz;
import com.school.common.entity.Course;
import com.school.common.entity.Topic;
import com.school.common.entity.User;

public interface AcademicTranscriptRepository extends JpaRepository<AcademicTranscript, Long>{

	@Query("SELECT at FROM AcademicTranscript at "
			+ "WHERE at.isDeleted = FALSE "
			+ "AND at.teacher = :teacher "
			+ "AND at.course = :course "
			+ "AND at.clazz = :class")
	public List<AcademicTranscript> findStudentAcademicTranscript(
			@Param("teacher") User idTeacher,
			@Param("course") Course idCourse,
			@Param("class") Clazz idClass);
	
	@Query("SELECT at "
			+ "FROM AcademicTranscript at "
			+ "WHERE at.isDeleted = FALSE "
			+ "AND at.id = :id")
	public AcademicTranscript findAcademicTranscript(
			@Param("id") Long id);

	@Query("SELECT at FROM AcademicTranscript at "
			+ "WHERE at.isDeleted = FALSE "
			+ "AND at.student = :student "
			+ "AND at.topic = :topic")
	public List<AcademicTranscript> findAcademicTranscriptByStudentAndTopic(
			@Param("student") User idStudent,
			@Param("topic") Topic idTopic);
	
	@Modifying
	@Query("UPDATE AcademicTranscript at SET at.point = :point "
			+ "WHERE at.isDeleted = FALSE "
			+ "AND at.teacher = :teacher "
			+ "AND at.course = :course "
			+ "AND at.clazz = :class "
			+ "AND at.student = :student")
	public void saveStudentAcademicTranscript(
			@Param("teacher") User idTeacher,
			@Param("student") User idStudent,
			@Param("course") Course idCourse,
			@Param("class") Clazz idClass,
			@Param("point") Float point);

	@Modifying
	@Query("UPDATE AcademicTranscript at SET at.point = :point "
			+ "WHERE at.isDeleted = FALSE "
			+ "AND at.id = :id")
	public void saveStudentAcademicTranscriptById(
			@Param("id") Long id,
			@Param("point") Float point);
}
