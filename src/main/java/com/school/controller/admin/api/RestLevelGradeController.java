package com.school.controller.admin.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
	public Long getCreateClassroom(HttpServletRequest request, @RequestBody LevelGradeModel levelGrade) {
		return levelGradeService.save(levelGrade, SystemConstant.INSERT);
	}
	
	@RequestMapping(value={"/api-admin-level-grade"}, method=RequestMethod.PUT)
	public Long getUpdateClassroom(HttpServletRequest request, @RequestBody LevelGradeModel levelGrade) {
		return levelGradeService.save(levelGrade, SystemConstant.MODIFY);
	}
	
	@RequestMapping(value={"/api-admin-level-grade"}, method= RequestMethod.DELETE)
	public Long getDeleteClassroom(HttpServletRequest request, @RequestBody LevelGradeModel levelGrade) {
		return levelGradeService.delete(levelGrade);
	}
	
	@RequestMapping(value={"/api-admin-level-grade-file"}, method= RequestMethod.POST)
	public Long sendFileClassroom(@RequestParam(name="file") MultipartFile file) {
		return levelGradeService.saveList(file);
	}
}
