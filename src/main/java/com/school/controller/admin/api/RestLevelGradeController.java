package com.school.controller.admin.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.school.constant.SystemConstant;
import com.school.model.LevelGradeModel;
import com.school.service.ILevelGradeService;

@RestController
@RequestMapping(value= {"/admin"})
public class RestLevelGradeController {

	@Autowired
	private ILevelGradeService levelGradeService;
	
	@RequestMapping(value={"/api-admin-level-grade"}, method=RequestMethod.GET)
	public List<LevelGradeModel> getAllClassrooms(HttpServletRequest request) {
		return levelGradeService.findAll();
	}
	
	@RequestMapping(value={"/api-admin-level-grade"}, method=RequestMethod.POST)
	public ResponseEntity<?> getCreateClassroom(HttpServletRequest request, @RequestBody LevelGradeModel levelGrade) {
		Long rs = levelGradeService.save(levelGrade, SystemConstant.INSERT);
		if (rs == SystemConstant.DUPLICATE) {
			return new ResponseEntity<>(SystemConstant.DUPLICATE_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		else if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.ERROR_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(SystemConstant.OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-level-grade"}, method=RequestMethod.PUT)
	public ResponseEntity<?> getUpdateClassroom(HttpServletRequest request, @RequestBody LevelGradeModel levelGrade) {
		Long rs = levelGradeService.save(levelGrade, SystemConstant.MODIFY);
		if (rs == SystemConstant.DUPLICATE) {
			return new ResponseEntity<>(SystemConstant.DUPLICATE_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		else if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.ERROR_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(SystemConstant.OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-level-grade"}, method= RequestMethod.DELETE)
	public ResponseEntity<?> getDeleteClassroom(HttpServletRequest request, @RequestBody LevelGradeModel levelGrade) {
		Long rs = levelGradeService.delete(levelGrade);
		if (rs == SystemConstant.ERROR) {
			return new ResponseEntity<>(SystemConstant.DELETE_FAILED_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(SystemConstant.DELETE_OK_RES.getBytes(SystemConstant.CHARSET_UTF_8), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/api-admin-level-grade-file"}, method= RequestMethod.POST)
	public Long sendFileClassroom(@RequestParam(name="file") MultipartFile file) {
		return levelGradeService.saveList(file);
	}
}
