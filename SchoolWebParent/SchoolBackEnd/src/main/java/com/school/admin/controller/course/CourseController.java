package com.school.admin.controller.course;

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
import com.school.admin.service.CourseService;
import com.school.admin.util.StaticUtil;
import com.school.common.common.SystemConstant;
import com.school.common.entity.Course;
import com.school.common.entity.Clazz;

@Controller
public class CourseController {

	@Autowired
	private CourseService service;
	
	@GetMapping("/courses")
	public String listFirstPage(Model model) {
		return listByPage(1, model, SystemConstant.NAME, SystemConstant.ASC, null);
	}
	
	@GetMapping("/courses/page/{pageNum}")
	public String listByPage(@PathVariable(name="pageNum") int pageNum, Model model,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir, 
			@Param("keyword") String keyword) {
		
		Page<Course> page = service.listByPage(pageNum, sortField, sortDir, keyword);
		List<Course> listCourses = page.getContent();
		
		long startCount = (pageNum - 1) * CourseService.COURSE_PER_PAGE + 1;
		long endCount = startCount + CourseService.COURSE_PER_PAGE - 1;
		
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		String reverseSortDir = sortDir.equals(SystemConstant.ASC) ? SystemConstant.DESC : SystemConstant.ASC;
		
		model.addAttribute(SystemConstant.CURRENT_PAGE, pageNum);
		model.addAttribute(SystemConstant.TOTAL_PAGE, page.getTotalPages());
		model.addAttribute(SystemConstant.START_COUNT, startCount);
		model.addAttribute(SystemConstant.END_COUNT, endCount);
		model.addAttribute(SystemConstant.TOTAL_ITEM, page.getTotalElements());
		model.addAttribute("listCourses", listCourses);
		model.addAttribute(SystemConstant.SORT_FILED, sortField);
		model.addAttribute(SystemConstant.SORT_DIR, sortDir);
		model.addAttribute(SystemConstant.REVERSE_SORT_DIR, reverseSortDir);
		model.addAttribute(SystemConstant.KEYWORD, keyword);
		model.addAttribute(SystemConstant.LINK, "courses");
		StaticUtil.setTitleAndStatic(model, SystemConstant.TITLE_COURSE);
		return "courses/courses";
	}
	
	@GetMapping("/courses/new")
	public String newLevel(Model model) {
		Course course = new Course();
		model.addAttribute("course", course);
		model.addAttribute(SystemConstant.LINK, "courses");
		StaticUtil.setTitleAndStatic(model, SystemConstant.TITLE_CREATE_NEW_COURSE);
		return "courses/course_form";
	}
	
	@PostMapping("/courses/save")
	public String saveClassroom(Course course, RedirectAttributes redirectAttributes) throws IOException {
		service.save(course);
		redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE, 
				SystemConstant.ATTR_CONTENT_COURSE_SAVE_SUCCESS);
		return getRedirectURLtoAffectedClassroom(course);
	}
	
	private String getRedirectURLtoAffectedClassroom(Course course) {
		return "redirect:/courses/page/1?sortField=id&sortDir=asc&keyword=" + course.getName();
	}
	
	@GetMapping("/courses/edit/{id}")
	public String editLevel(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes, Model model) {
		try {
			Optional<Course> opCourse = Optional.ofNullable(service.get(id));
			if (opCourse.isPresent()) {
				StringBuilder title = new StringBuilder();
				title.append(SystemConstant.TITLE_EDIT_COURSE).append(id);

				model.addAttribute("course", opCourse.get());
				model.addAttribute(SystemConstant.LINK, "courses");
				StaticUtil.setTitleAndStatic(model, title.toString());
				return "courses/course_form";
			}
		} catch (EntityNotFoundException e) {
			redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE, e.getMessage());
		}
		return "redirect:/courses";
	}
	
	@GetMapping("/courses/delete/{id}")
	public String deleteClassroom(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
		try {
			service.deleteCourse(id);
			redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE,
					SystemConstant.ATTR_CONTENT_COURSE_DEL_SUCCESS(id));
		} catch (EntityNotFoundException e) {
			redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE, e.getMessage());
		}
		return "redirect:/courses";
	}
	
}
