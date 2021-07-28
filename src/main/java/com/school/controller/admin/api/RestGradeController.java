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
import com.school.model.GradeModel;
import com.school.service.IGradeService;

@RestController
@RequestMapping(value= {"/admin"})
public class RestGradeController {

	@Autowired
	private IGradeService gradeService;
	
	@RequestMapping(value={"/api-admin-grade"}, method=RequestMethod.GET)
	public List<GradeModel> getAllClassrooms(HttpServletRequest request) {
		return gradeService.findAll();
	}
	
	@RequestMapping(value={"/api-admin-grade"}, method=RequestMethod.POST)
	public Long getCreateClassroom(HttpServletRequest request, @RequestBody GradeModel grade) {
		return gradeService.save(grade, SystemConstant.INSERT);
	}
	
	@RequestMapping(value={"/api-admin-grade"}, method=RequestMethod.PUT)
	public Long getUpdateClassroom(HttpServletRequest request, @RequestBody GradeModel grade) {
		return gradeService.save(grade, SystemConstant.MODIFY);
	}
	
	@RequestMapping(value={"/api-admin-grade"}, method= RequestMethod.DELETE)
	public Long getDeleteClassroom(HttpServletRequest request, @RequestBody GradeModel grade) {
		return gradeService.delete(grade);
	}
	
	@RequestMapping(value={"/api-admin-grade-file"}, method= RequestMethod.POST)
	public Long sendFileClassroom(@RequestParam(name="file") MultipartFile file) {
		return gradeService.saveList(file);
	}
}
