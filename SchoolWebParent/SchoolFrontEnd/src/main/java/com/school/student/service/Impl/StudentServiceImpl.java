package com.school.student.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.common.dto.AcademicTranscriptDto;
import com.school.common.entity.AcademicTranscript;
import com.school.common.entity.User;
import com.school.service.UserService;
import com.school.student.repository.StudentRepository;
import com.school.student.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository repo;
	
	@Autowired
	private UserService userService;

	@Override
	public List<AcademicTranscriptDto> findAcademicTranscriptByStudentId(Long idStudent) {
		User student = userService.findUserById(idStudent);
		Optional<List<AcademicTranscript>> op = Optional.ofNullable(repo.findAcademicTranscriptByStudentId(student));
		if (op.isPresent()) {
			List<AcademicTranscript> academicTranscripts = op.get();
			List<AcademicTranscriptDto> academicTranscriptDtos = academicTranscripts.stream()
					.map(AcademicTranscriptDto::new).collect(Collectors.toList());
			return academicTranscriptDtos;
		}
		return null;
	}

}
