package com.school.student.service;

import java.util.List;

import com.school.common.dto.AcademicTranscriptDto;

public interface StudentService {

	public List<AcademicTranscriptDto> findAcademicTranscriptByStudentId(Long idStudent);
}
