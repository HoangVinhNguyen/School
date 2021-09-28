package com.school.admin.controller.grade;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.school.admin.exception.EntityNotFoundException;
import com.school.admin.service.GradeService;
import com.school.admin.service.LevelService;
import com.school.admin.util.StaticUtil;
import com.school.common.common.SystemConstant;
import com.school.common.entity.Grade;
import com.school.common.entity.Level;

@Controller
public class GradeController {

	@Autowired
	private GradeService service;
	
	@Autowired
	private LevelService levelService;
	
	@GetMapping("/grades")
	public String listFirstPage(Model model) {
		return listByPage(1, model, SystemConstant.NAME, SystemConstant.ASC, null);
	}
	
	@GetMapping("/grades/page/{pageNum}")
	public String listByPage(@PathVariable(name="pageNum") int pageNum, Model model,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir, 
			@Param("keyword") String keyword) {
		
		Page<Grade> page = service.listByPage(pageNum, sortField, sortDir, keyword);
		List<Grade> listGrades = page.getContent();
		
		long startCount = (pageNum - 1) * LevelService.LEVEL_PER_PAGE + 1;
		long endCount = startCount + LevelService.LEVEL_PER_PAGE - 1;
		
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		String reverseSortDir = sortDir.equals(SystemConstant.ASC) ? SystemConstant.DESC : SystemConstant.ASC;
		
		model.addAttribute(SystemConstant.CURRENT_PAGE, pageNum);
		model.addAttribute(SystemConstant.TOTAL_PAGE, page.getTotalPages());
		model.addAttribute(SystemConstant.START_COUNT, startCount);
		model.addAttribute(SystemConstant.END_COUNT, endCount);
		model.addAttribute(SystemConstant.TOTAL_ITEM, page.getTotalElements());
		model.addAttribute("listGrades", listGrades);
		model.addAttribute(SystemConstant.SORT_FILED, sortField);
		model.addAttribute(SystemConstant.SORT_DIR, sortDir);
		model.addAttribute(SystemConstant.REVERSE_SORT_DIR, reverseSortDir);
		model.addAttribute(SystemConstant.KEYWORD, keyword);
		model.addAttribute(SystemConstant.LINK, "grades");
		StaticUtil.setTitleAndStatic(model, SystemConstant.TITLE_GRADE);
		return "grades/grades";
	}
	
	@GetMapping("/grades/new")
	public String newLevel(Model model) {
		Grade grade = new Grade();
		Optional<List<Level>> opLevel = Optional.ofNullable(levelService.listAll());
		if (opLevel.isPresent()) {
			model.addAttribute("grade", grade);
			model.addAttribute("listLevels", opLevel.get());
			model.addAttribute(SystemConstant.LINK, "grades");
			StaticUtil.setTitleAndStatic(model, SystemConstant.TITLE_CREATE_NEW_LEVEL);
			return "grades/grade_form";
		}
		return "redirect:/grades";
	}
	
	@PostMapping("/grades/save")
	public String saveLevel(Grade level, RedirectAttributes redirectAttributes) throws IOException {
		service.save(level);
		redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE, SystemConstant.ATTR_CONTENT_LEVEL_SAVE_SUCCESS);
		return getRedirectURLtoAffectedLevel(level);
	}
	
	private String getRedirectURLtoAffectedLevel(Grade level) {
		String name = level.getName();
		return "redirect:/grades/page/1?sortField=id&sortDir=asc&keyword=" + name;
	}
	
	@GetMapping("/grades/edit/{id}")
	public String editLevel(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes, Model model) {
		try {
			Optional<Grade> opGrade = Optional.ofNullable(service.get(id));
			Optional<List<Level>> opLevel = Optional.ofNullable(levelService.listAll());
			
			if (opGrade.isPresent() && opLevel.isPresent()) {
				StringBuilder title = new StringBuilder();
				title.append(SystemConstant.TITLE_EDIT_LEVEL).append(id);

				model.addAttribute("grade", opGrade.get());
				model.addAttribute("listLevels", opLevel.get());
				model.addAttribute(SystemConstant.LINK, "grades");
				StaticUtil.setTitleAndStatic(model, title.toString());
				return "grades/grade_form";
			}
		} catch (EntityNotFoundException e) {
			redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE, e.getMessage());
		}
		return "redirect:/grades";
	}
	
	@GetMapping("/grades/delete/{id}")
	public String deleteLevel(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
		try {
			service.deleteLevel(id);
			redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE,
					SystemConstant.ATTR_CONTENT_USER_EDIT_SUCCESS(id));
		} catch (EntityNotFoundException e) {
			redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE, e.getMessage());
		}
		return "redirect:/grades";
	}
	
}
