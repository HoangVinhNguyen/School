package com.school.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.school.common.common.SystemConstant;
import com.school.student.service.StudentService;

@Controller
@RequestMapping("/api/student")
@PreAuthorize("hasRole('" + SystemConstant.STUDENT + "')")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/academic-transcript/{id}")
	public ResponseEntity<?> getClasses(@PathVariable(name="id") Long id) {
		return ResponseEntity.ok(studentService.findAcademicTranscriptByStudentId(id));
	}
}
