package com.school.teacher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.common.common.SystemConstant;
import com.school.common.dto.AcademicBodyReq;
import com.school.common.dto.SavePointBodyReq;
import com.school.teacher.service.AcademicTranscriptService;
import com.school.teacher.service.TeacherService;

@RestController
@RequestMapping("/api/teacher")
@PreAuthorize("hasRole('" + SystemConstant.TEACHER + "')")
public class ManageRestController {
	
	//@CrossOrigin(origins = "http://localhost:4200")
	
	@Autowired
	private TeacherService service;
	
	@Autowired
	private AcademicTranscriptService academicTranscriptService;

	@GetMapping("/classes")
	public ResponseEntity<?> getClasses() {
		String email = service.getNamePrinciple();
		return ResponseEntity.ok(service.findClassesOfEmailTeacher(email));
	}
	
	@GetMapping("/class/{id}/student")
	public ResponseEntity<?> getStudentInClass(@PathVariable(name="id") Long id) {
		String email = service.getNamePrinciple();
		return ResponseEntity.ok(service.findStudentInClass(id, email));
	}
	
	@GetMapping("/courses")
	public ResponseEntity<?> getCourses() {
		String email = service.getNamePrinciple();
		return ResponseEntity.ok(service.findCourses(email));
	}
	
	@PostMapping("/academic-transcript")
	public ResponseEntity<?> getAcademicTranscript(@RequestBody AcademicBodyReq acaTranscript) {
		return ResponseEntity.ok(academicTranscriptService.findStudentAcademicTranscript(acaTranscript));
	}
	
	@GetMapping("/profile/{id}")
	public ResponseEntity<?> getProfile(@PathVariable(name="id") Long id) {
		return ResponseEntity.ok(service.findUserById(id));
	}
	
	@PostMapping("/save-point")
	public ResponseEntity<?> savePoint(@RequestBody SavePointBodyReq savePointReq) {
		return ResponseEntity.ok(service.saveStudentAcademicTranscript(savePointReq));
	}
	
}
