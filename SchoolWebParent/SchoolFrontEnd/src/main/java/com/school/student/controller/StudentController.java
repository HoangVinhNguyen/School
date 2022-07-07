package com.school.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.school.common.common.SystemConstant;
import com.school.common.dto.CommentDto;
import com.school.common.dto.StudentAndTopicDto;
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
	
	@GetMapping("/topic/course/{idCourse}/class/{idClass}")
	public ResponseEntity<?> getTopics(@PathVariable(name="idCourse") Long idCourse, @PathVariable(name="idClass") Long idClass) {
		return ResponseEntity.ok(studentService.findTopicByCourseIdAndClazzId(idCourse, idClass));
	}

	@GetMapping("/topic/course/{idCourse}/class/{idClass}/full")
	public ResponseEntity<?> getTopicsFull(@PathVariable(name="idCourse") Long idCourse, @PathVariable(name="idClass") Long idClass) {
		return ResponseEntity.ok(studentService.findTopicByCourseIdAndClazzIdFull(idCourse, idClass));
	}
	
	@PostMapping("/topic/answer")
	public ResponseEntity<?> postAnswer(@RequestBody StudentAndTopicDto reqBody) {
		return ResponseEntity.ok(studentService.saveAnswer(reqBody));
	}
	@GetMapping("/topic/answer/student/{idStudent}/topic/{idTopic}")
	public ResponseEntity<?> getAnswer(@PathVariable(name="idStudent") Long idStudent, @PathVariable(name="idTopic") Long idTopic) {
		return ResponseEntity.ok(studentService.getAnswer(idStudent, idTopic));
	}
	
	@PostMapping("/topic/comment")
	public ResponseEntity<?> postComment(@RequestBody CommentDto body) {
		return ResponseEntity.ok(studentService.saveComment(body));
	}

	@GetMapping("/topic/comment/{idTopic}")
	public ResponseEntity<?> getComment(@PathVariable(name="idTopic") Long idTopic) {
		return ResponseEntity.ok(studentService.findCommentByTopicId(idTopic));
	}
}
