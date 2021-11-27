package com.school.teacher.service;

import java.util.List;

import com.school.common.dto.AcademicBodyReq;
import com.school.common.dto.AcademicTranscriptDto;
import com.school.common.dto.SavePointBodyReq;
import com.school.common.entity.AcademicTranscript;

public interface AcademicTranscriptService {

	public List<AcademicTranscriptDto> findStudentAcademicTranscript(
			AcademicBodyReq academicTranscript);
	public AcademicTranscript findAcademic(AcademicTranscriptDto academicTranscriptDto);
	public AcademicTranscript save(AcademicTranscriptDto academicTranscriptDto);
	public void saveStudentAcademicTranscript(SavePointBodyReq savePointBodyReq);
}
