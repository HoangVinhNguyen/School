package com.school.teacher.service;

import java.util.List;

import com.school.common.dto.AcademicBodyReq;
import com.school.common.dto.AcademicTranscriptDto;
import com.school.common.dto.AcademicTranscriptStudentPointDto;
import com.school.common.dto.StudentAndPointDto;
import com.school.common.dto.TopicDto;
import com.school.common.dto.UserDto;
import com.school.common.entity.AcademicTranscript;
import com.school.common.entity.StudentAndPoint;

public interface AcademicTranscriptService {

	public List<AcademicTranscriptDto> findStudentAcademicTranscript(
			AcademicBodyReq academicTranscript);
	public List<StudentAndPointDto> findStudentAcademicTranscriptWithPoint(
			AcademicBodyReq academicTranscript);
	public AcademicTranscript findAcademic(AcademicTranscriptDto academicTranscriptDto);
	public StudentAndPoint save(AcademicTranscriptDto academicTranscriptDto);
	public AcademicTranscriptDto saveAcademicStudent(AcademicTranscriptStudentPointDto academicTranscriptDto);
	public void saveModifyPointAcademicById(AcademicTranscriptDto academicTranscriptDto);
	public AcademicTranscriptDto findPointExisting(UserDto student, TopicDto topic);
	//public void saveStudentAcademicTranscript(SavePointBodyReq savePointBodyReq);
}
