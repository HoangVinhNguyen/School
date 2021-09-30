package com.school.admin.controller.classroom;

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
import com.school.admin.service.ClassroomService;
import com.school.admin.service.ClazzService;
import com.school.admin.util.StaticUtil;
import com.school.common.common.SystemConstant;
import com.school.common.entity.Classroom;
import com.school.common.entity.Clazz;

@Controller
public class ClassroomController {

	@Autowired
	private ClassroomService service;

	@Autowired
	private ClazzService clazzService;
	
	
	@GetMapping("/classrooms")
	public String listFirstPage(Model model) {
		return listByPage(1, model, SystemConstant.NAME, SystemConstant.ASC, null);
	}
	
	@GetMapping("/classrooms/page/{pageNum}")
	public String listByPage(@PathVariable(name="pageNum") int pageNum, Model model,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir, 
			@Param("keyword") String keyword) {
		
		Page<Classroom> page = service.listByPage(pageNum, sortField, sortDir, keyword);
		List<Classroom> listClassrooms = page.getContent();
		
		long startCount = (pageNum - 1) * ClassroomService.CLASSROOM_PER_PAGE + 1;
		long endCount = startCount + ClassroomService.CLASSROOM_PER_PAGE - 1;
		
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		String reverseSortDir = sortDir.equals(SystemConstant.ASC) ? SystemConstant.DESC : SystemConstant.ASC;
		
		model.addAttribute(SystemConstant.CURRENT_PAGE, pageNum);
		model.addAttribute(SystemConstant.TOTAL_PAGE, page.getTotalPages());
		model.addAttribute(SystemConstant.START_COUNT, startCount);
		model.addAttribute(SystemConstant.END_COUNT, endCount);
		model.addAttribute(SystemConstant.TOTAL_ITEM, page.getTotalElements());
		model.addAttribute("listClassrooms", listClassrooms);
		model.addAttribute(SystemConstant.SORT_FILED, sortField);
		model.addAttribute(SystemConstant.SORT_DIR, sortDir);
		model.addAttribute(SystemConstant.REVERSE_SORT_DIR, reverseSortDir);
		model.addAttribute(SystemConstant.KEYWORD, keyword);
		model.addAttribute(SystemConstant.LINK, "classrooms");
		StaticUtil.setTitleAndStatic(model, SystemConstant.TITLE_CLASSROOM);
		return "classrooms/classrooms";
	}
	
	@GetMapping("/classrooms/new")
	public String newLevel(Model model) {
		Classroom classroom = new Classroom();
		Optional<List<Clazz>> opClazz = Optional.ofNullable(clazzService.listAll());
		if (opClazz.isPresent()) {
			model.addAttribute("classroom", classroom);
			model.addAttribute("listClazzs", opClazz.get());
			model.addAttribute(SystemConstant.LINK, "classrooms");
			StaticUtil.setTitleAndStatic(model, SystemConstant.TITLE_CREATE_NEW_CLASSROOM);
			return "classrooms/classroom_form";
		}
		return "redirect:/classrooms";
	}
	
	@PostMapping("/classrooms/save")
	public String saveClassroom(Classroom classroom, RedirectAttributes redirectAttributes) throws IOException {
		service.save(classroom);
		redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE, 
				SystemConstant.ATTR_CONTENT_CLASSROOM_SAVE_SUCCESS);
		return getRedirectURLtoAffectedClassroom(classroom);
	}
	
	private String getRedirectURLtoAffectedClassroom(Classroom classroom) {
		return "redirect:/classrooms/page/1?sortField=id&sortDir=asc&keyword=" + classroom.getName();
	}
	
	@GetMapping("/classrooms/edit/{id}")
	public String editLevel(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes, Model model) {
		try {
			Optional<Classroom> opClassroom = Optional.ofNullable(service.get(id));
			Optional<List<Clazz>> opClazz = Optional.ofNullable(clazzService.listAll());
			
			if (opClazz.isPresent() && opClazz.isPresent()) {
				StringBuilder title = new StringBuilder();
				title.append(SystemConstant.TITLE_EDIT_CLAZZ).append(id);

				model.addAttribute("classroom", opClassroom.get());
				model.addAttribute("listClazzs", opClazz.get());
				model.addAttribute(SystemConstant.LINK, "classrooms");
				StaticUtil.setTitleAndStatic(model, title.toString());
				return "classrooms/classroom_form";
			}
		} catch (EntityNotFoundException e) {
			redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE, e.getMessage());
		}
		return "redirect:/classrooms";
	}
	
	@GetMapping("/classrooms/delete/{id}")
	public String deleteClassroom(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
		try {
			service.deleteClassroom(id);
			redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE,
					SystemConstant.ATTR_CONTENT_CLASSROOM_DEL_SUCCESS(id));
		} catch (EntityNotFoundException e) {
			redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE, e.getMessage());
		}
		return "redirect:/classrooms";
	}
	
}
