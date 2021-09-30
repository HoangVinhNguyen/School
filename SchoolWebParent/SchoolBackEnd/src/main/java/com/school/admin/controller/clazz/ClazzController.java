package com.school.admin.controller.clazz;

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
import com.school.admin.service.ClazzService;
import com.school.admin.service.GradeService;
import com.school.admin.util.StaticUtil;
import com.school.common.common.SystemConstant;
import com.school.common.entity.Clazz;
import com.school.common.entity.Grade;

@Controller
public class ClazzController {

	@Autowired
	private ClazzService service;

	@Autowired
	private GradeService gradeService;
	
	@GetMapping("/clazzes")
	public String listFirstPage(Model model) {
		return listByPage(1, model, SystemConstant.NAME, SystemConstant.ASC, null);
	}
	
	@GetMapping("/clazzes/page/{pageNum}")
	public String listByPage(@PathVariable(name="pageNum") int pageNum, Model model,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir, 
			@Param("keyword") String keyword) {
		
		Page<Clazz> page = service.listByPage(pageNum, sortField, sortDir, keyword);
		List<Clazz> listClazzes = page.getContent();
		
		long startCount = (pageNum - 1) * ClazzService.CLAZZ_PER_PAGE + 1;
		long endCount = startCount + ClazzService.CLAZZ_PER_PAGE - 1;
		
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		String reverseSortDir = sortDir.equals(SystemConstant.ASC) ? SystemConstant.DESC : SystemConstant.ASC;
		
		model.addAttribute(SystemConstant.CURRENT_PAGE, pageNum);
		model.addAttribute(SystemConstant.TOTAL_PAGE, page.getTotalPages());
		model.addAttribute(SystemConstant.START_COUNT, startCount);
		model.addAttribute(SystemConstant.END_COUNT, endCount);
		model.addAttribute(SystemConstant.TOTAL_ITEM, page.getTotalElements());
		model.addAttribute("listClazzes", listClazzes);
		model.addAttribute(SystemConstant.SORT_FILED, sortField);
		model.addAttribute(SystemConstant.SORT_DIR, sortDir);
		model.addAttribute(SystemConstant.REVERSE_SORT_DIR, reverseSortDir);
		model.addAttribute(SystemConstant.KEYWORD, keyword);
		model.addAttribute(SystemConstant.LINK, "clazzes");
		StaticUtil.setTitleAndStatic(model, SystemConstant.TITLE_CLAZZ);
		return "clazzes/clazzes";
	}
	
	@GetMapping("/clazzes/new")
	public String newClazz(Model model) {
		Clazz clazz = new Clazz();
		Optional<List<Grade>> opGrade = Optional.ofNullable(gradeService.listAll());
		if (opGrade.isPresent()) {
			model.addAttribute("clazz", clazz);
			model.addAttribute("listGrades", opGrade.get());
			model.addAttribute(SystemConstant.LINK, "clazzes");
			StaticUtil.setTitleAndStatic(model, SystemConstant.TITLE_CREATE_NEW_CLAZZ);
			return "clazzes/clazz_form";
		}
		return "redirect:/clazzes";
	}
	
	@PostMapping("/clazzes/save")
	public String saveClazz(Clazz clazz, RedirectAttributes redirectAttributes) throws IOException {
		service.save(clazz);
		redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE, SystemConstant.ATTR_CONTENT_CLAZZ_SAVE_SUCCESS);
		return getRedirectURLtoAffectedLevel(clazz);
	}
	
	private String getRedirectURLtoAffectedLevel(Clazz clazz) {
		return "redirect:/clazzes/page/1?sortField=id&sortDir=asc&keyword=" + clazz.getName();
	}
	
	@GetMapping("/clazzes/edit/{id}")
	public String editClazz(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes, Model model) {
		try {
			Optional<Clazz> opClazz = Optional.ofNullable(service.get(id));
			Optional<List<Grade>> opGrade = Optional.ofNullable(gradeService.listAll());
			
			if (opGrade.isPresent() && opGrade.isPresent()) {
				StringBuilder title = new StringBuilder();
				title.append(SystemConstant.TITLE_EDIT_CLAZZ).append(id);

				model.addAttribute("clazz", opClazz.get());
				model.addAttribute("listGrades", opGrade.get());
				model.addAttribute(SystemConstant.LINK, "clazzes");
				StaticUtil.setTitleAndStatic(model, title.toString());
				return "clazzes/clazz_form";
			}
		} catch (EntityNotFoundException e) {
			redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE, e.getMessage());
		}
		return "redirect:/clazzes";
	}
	
	@GetMapping("/clazzes/delete/{id}")
	public String deleteClazz(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
		try {
			service.deleteClazz(id);
			redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE,
					SystemConstant.ATTR_CONTENT_CLAZZ_DEL_SUCCESS(id));
		} catch (EntityNotFoundException e) {
			redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE, e.getMessage());
		}
		return "redirect:/clazzes";
	}
	
}
