package com.school.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.school.common.entity.AcademicTranscript;
import com.school.common.entity.User;

@Repository
public interface StudentRepository extends JpaRepository<AcademicTranscript, Long>{

	@Query("SELECT at FROM AcademicTranscript at "
			+ "WHERE at.isDeleted = FALSE "
			+ "AND at.student = :student")
	public List<AcademicTranscript> findAcademicTranscriptByStudentId(@Param("student") User student);
}
