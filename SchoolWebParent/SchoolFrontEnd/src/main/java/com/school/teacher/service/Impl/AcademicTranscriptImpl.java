package com.school.teacher.service.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.school.common.dto.AcademicBodyReq;
import com.school.common.dto.AcademicTranscriptDto;
import com.school.common.dto.AcademicTranscriptStudentPointDto;
import com.school.common.dto.StudentAndPointDto;
import com.school.common.dto.TopicDto;
import com.school.common.dto.UserDto;
import com.school.common.entity.AcademicTranscript;
import com.school.common.entity.Clazz;
import com.school.common.entity.Course;
import com.school.common.entity.StudentAndPoint;
import com.school.common.entity.Topic;
import com.school.common.entity.User;
import com.school.teacher.repository.AcademicTranscriptRepository;
import com.school.teacher.repository.StudentAndPointRepository;
import com.school.teacher.service.AcademicTranscriptService;

@Service
@Transactional
public class AcademicTranscriptImpl implements AcademicTranscriptService {

	@Autowired
	private AcademicTranscriptRepository repo;
	
	@Autowired
	private StudentAndPointRepository repoStudentAndPoint;

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
	public List<StudentAndPointDto> findStudentAcademicTranscriptWithPoint(AcademicBodyReq academicTranscript) {
		Optional<List<StudentAndPoint>> op = Optional
				.ofNullable(repoStudentAndPoint.findStudentAcademicTranscript(User.convertToUser(academicTranscript.getTeacher()),
						Course.convertToCourse(academicTranscript.getCourse()),
						Clazz.convertToClazz(academicTranscript.getClazz())));
		if (op.isPresent()) {
			List<StudentAndPoint> academicTranscripts = op.get();
			List<StudentAndPointDto> listStudent = academicTranscripts.stream().map(StudentAndPointDto::new).collect(Collectors.toList());
			List<StudentAndPointDto> result = new ArrayList<>();
			result = new ArrayList<>(
					listStudent.stream()
				    .collect(
				        Collectors.toMap(StudentAndPointDto::getStudent, Function.identity(), (StudentAndPointDto account1, StudentAndPointDto account2) -> {
				            account1.getListPoint().addAll(account2.getListPoint());
				            account2.getListPoint().clear();
				            return account1;
				        })
				    ).values());
			return result;
		}
		return null;
	}

	@Override
	public AcademicTranscript findAcademic(AcademicTranscriptDto academicTranscriptDto) {
		Optional<AcademicTranscript> op = Optional
				.ofNullable(repo.findAcademicTranscript(academicTranscriptDto.getId()));
		if (op.isPresent()) {
			AcademicTranscript academicTranscripts = op.get();
			//AcademicTranscriptDto academicTranscriptDtos = new AcademicTranscriptDto(academicTranscripts);
			return academicTranscripts;
		}
		return null;
	}

	public AcademicTranscript findAcademicTranscriptStudentPointDto(AcademicTranscriptStudentPointDto academicTranscriptDto) {
		Optional<AcademicTranscript> op = Optional
				.ofNullable(repo.findAcademicTranscript(academicTranscriptDto.getId()));
		if (op.isPresent()) {
			AcademicTranscript academicTranscripts = op.get();
			//AcademicTranscriptDto academicTranscriptDtos = new AcademicTranscriptDto(academicTranscripts);
			return academicTranscripts;
		}
		return null;
	}

	@Override
	public StudentAndPoint save(AcademicTranscriptDto academicTranscriptDto) {
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
				academicTranscripts.setCoefficient(academicTranscriptDto.getCoefficient());
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
		AcademicTranscript academicTranscript = repo.save(academicTranscripts);
		return new StudentAndPoint(academicTranscript.getId(), academicTranscript.getStudent(), 
				academicTranscript.getPoint(), academicTranscript.getCoefficient(), academicTranscript.getCreatedDate()) ;
	}

	@Override
	public AcademicTranscriptDto saveAcademicStudent(AcademicTranscriptStudentPointDto academicTranscriptDto) {
		AcademicTranscript academicTranscripts = findAcademicTranscriptStudentPointDto(academicTranscriptDto);

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
				academicTranscripts.setCoefficient(academicTranscriptDto.getCoefficient());
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
		AcademicTranscript academicTranscript = repo.save(academicTranscripts);
		return new AcademicTranscriptDto(academicTranscript) ;
	}

	@Override
	public void saveModifyPointAcademicById(AcademicTranscriptDto academicTranscriptDto) {
		repo.saveStudentAcademicTranscriptById(
				academicTranscriptDto.getId(), academicTranscriptDto.getPoint());
	}

	@Override
	public AcademicTranscriptDto findPointExisting(UserDto student, TopicDto topic) {
		Optional<List<AcademicTranscript>> op = Optional
				.ofNullable(repo.findAcademicTranscriptByStudentAndTopic(User.convertToUser(student), new Topic(topic)));
		if (op.isPresent()) {
			List<AcademicTranscript> academicTranscripts = op.get();
			if (academicTranscripts.size() > 0) {
				AcademicTranscriptDto academicTranscriptDtos = new AcademicTranscriptDto(academicTranscripts.get(0));
				return academicTranscriptDtos;
			}
		}
		return null;
	}

//	@Override
//	public void saveStudentAcademicTranscript(SavePointBodyReq savePointBodyReq) {
//		savePointBodyReq.getListStudents().forEach(studentOb -> {
//			repo.saveStudentAcademicTranscript(User.convertToUser(savePointBodyReq.getTeacher()),
//					User.convertToUser(studentOb), Course.convertToCourse(savePointBodyReq.getCourse()),
//					Clazz.convertToClazz(savePointBodyReq.getClazz()), studentOb.getPoint());
//		});
//	}

	
}
