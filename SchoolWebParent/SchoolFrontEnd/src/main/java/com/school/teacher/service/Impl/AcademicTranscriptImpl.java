package com.school.teacher.service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.school.common.dto.AcademicBodyReq;
import com.school.common.dto.AcademicTranscriptDto;
import com.school.common.dto.SavePointBodyReq;
import com.school.common.entity.AcademicTranscript;
import com.school.common.entity.Clazz;
import com.school.common.entity.Course;
import com.school.common.entity.User;
import com.school.teacher.repository.AcademicTranscriptRepository;
import com.school.teacher.service.AcademicTranscriptService;

@Service
@Transactional
public class AcademicTranscriptImpl implements AcademicTranscriptService {

	@Autowired
	private AcademicTranscriptRepository repo;

	@Override
	public List<AcademicTranscriptDto> findStudentAcademicTranscript(AcademicBodyReq academicTranscript) {
		Optional<List<AcademicTranscript>> op = Optional
				.ofNullable(repo.findStudentAcademicTranscript(User.convertToUser(academicTranscript.getTeacher()),
						Course.convertToCourse(academicTranscript.getCourse()),
						Clazz.convertToClazz(academicTranscript.getClazz())));
		if (op.isPresent()) {
			List<AcademicTranscript> academicTranscripts = op.get();
			List<AcademicTranscriptDto> academicTranscriptDtos = academicTranscripts.stream()
					.map(AcademicTranscriptDto::new).collect(Collectors.toList());
			return academicTranscriptDtos;
		}
		return null;
	}

	@Override
	public AcademicTranscript findAcademic(AcademicTranscriptDto academicTranscriptDto) {
		Optional<AcademicTranscript> op = Optional
				.ofNullable(repo.findAcademicTranscript(User.convertToUser(academicTranscriptDto.getTeacher()),
						User.convertToUser(academicTranscriptDto.getStudent()),
						Course.convertToCourse(academicTranscriptDto.getCourse()),
						Clazz.convertToClazz(academicTranscriptDto.getClazz())));
		if (op.isPresent()) {
			AcademicTranscript academicTranscripts = op.get();
			//AcademicTranscriptDto academicTranscriptDtos = new AcademicTranscriptDto(academicTranscripts);
			return academicTranscripts;
		}
		return null;
	}

	@Override
	public AcademicTranscript save(AcademicTranscriptDto academicTranscriptDto) {
		AcademicTranscript academicTranscripts = findAcademic(academicTranscriptDto);

		Optional<AcademicTranscript> opCheck = Optional.ofNullable(academicTranscripts);
		Long idCheck = null;
		if (opCheck.isPresent()) {
			idCheck = opCheck.get().getId();
		}
		
		boolean isUpdatingUser = (idCheck != null);
		LocalDateTime dateNow = LocalDateTime.now();
		Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
		if (auth.isPresent()) {
			StringBuilder adminControl = new StringBuilder(auth.get().getName());
			if (isUpdatingUser) {
				academicTranscripts.setPoint(academicTranscriptDto.getPoint());
				academicTranscripts.setModifiedDate(dateNow);
				academicTranscripts.setModifiedBy(adminControl.toString());
			} else
			{
				academicTranscripts = AcademicTranscript.convertToAcademicTranscript(academicTranscriptDto);
				academicTranscripts.setCreatedDate(dateNow);
				academicTranscripts.setCreatedBy(adminControl.toString());
			}
		} else {
			return null;
		}
		return repo.save(academicTranscripts);
	}

	@Override
	public void saveStudentAcademicTranscript(SavePointBodyReq savePointBodyReq) {
		savePointBodyReq.getListStudents().forEach(studentOb -> {
			repo.saveStudentAcademicTranscript(User.convertToUser(savePointBodyReq.getTeacher()),
					User.convertToUser(studentOb), Course.convertToCourse(savePointBodyReq.getCourse()),
					Clazz.convertToClazz(savePointBodyReq.getClazz()), studentOb.getPoint());
		});
	}

}
