package com.school.admin.controller.clazz;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.school.admin.service.GradeService;
import com.school.admin.service.UserService;
import com.school.admin.util.StaticUtil;
import com.school.common.common.SystemConstant;
import com.school.common.entity.Classroom;
import com.school.common.entity.Clazz;
import com.school.common.entity.Grade;
import com.school.common.entity.Role;
import com.school.common.entity.User;

@Controller
public class ClazzController {

	@Autowired
	private ClazzService service;

	@Autowired
	private GradeService gradeService;
	
	@Autowired
	private ClassroomService classroomService;
	
	@Autowired
	private UserService userService;

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
		Optional<List<Classroom>> opClassroom = Optional.ofNullable(classroomService.listAll());
		if (opGrade.isPresent() && opClassroom.isPresent()) {
			model.addAttribute("clazz", clazz);
			model.addAttribute("listGrades", opGrade.get());
			model.addAttribute("listClassrooms", opClassroom.get());
			model.addAttribute(SystemConstant.LINK, "clazzes");
			StaticUtil.setTitleAndStatic(model, SystemConstant.TITLE_CREATE_NEW_CLAZZ, null, List.of("clazz_form.css"));
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
			Optional<List<Classroom>> opClassroom = Optional.ofNullable(classroomService.listAll());
			if (opGrade.isPresent() && opClassroom.isPresent() && opClazz.isPresent()) {
				List<User> listTeacher = opClazz.get().getUsers().stream()
						.filter(u -> u.getRoles().stream().map(Role::getName).anyMatch(name -> name.toLowerCase().equals(SystemConstant.TEACHER)))
						.collect(Collectors.toList());
				List<User> listStudent = opClazz.get().getUsers().stream()
						.filter(u -> u.getRoles().stream().map(Role::getName).anyMatch(name -> name.toLowerCase().equals(SystemConstant.STUDENT)))
						.collect(Collectors.toList());
				StringBuilder title = new StringBuilder();
				title.append(SystemConstant.TITLE_EDIT_CLAZZ).append(id);

				model.addAttribute("listTeacher", listTeacher);
				model.addAttribute("listStudent", listStudent);
				model.addAttribute("clazz", opClazz.get());
				model.addAttribute("listGrades", opGrade.get());
				model.addAttribute("listClassrooms", opClassroom.get());
				model.addAttribute(SystemConstant.LINK, "clazzes");
				StaticUtil.setTitleAndStatic(model, title.toString(), null, List.of("clazz_form.css"));
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
	
	/*
	 * FOR MANAGE SETUP CLASS WITH USER.
	 */
	
	@GetMapping("clazzes/detail/{id}")
	public String setupClazzWithUser(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes, Model model,
			@Param("pageNum") int pageNum, @Param("sortField") String sortField, 
			@Param("sortDir") String sortDir, @Param("keyword") String keyword) {
		
		try {
			Optional<Clazz> opClazz = Optional.ofNullable(service.get(id));
			Optional<List<Grade>> opGrade = Optional.ofNullable(gradeService.listAll());
			Optional<List<Classroom>> opClassroom = Optional.ofNullable(classroomService.listAll());
			Optional<List<User>> opUser = Optional.ofNullable(userService.listAll());
			if (opClazz.isPresent() && opGrade.isPresent() && opClassroom.isPresent() && opUser.isPresent()) {
				StringBuilder title = new StringBuilder();
				title.append(SystemConstant.TITLE_EDIT_CLAZZ).append(id);
				List<User> listTeacher = opClazz.get().getUsers().stream()
						.filter(u -> u.getRoles().stream().map(Role::getName).anyMatch(name -> name.toLowerCase().equals(SystemConstant.TEACHER)))
						.collect(Collectors.toList());
				List<User> listStudent = opClazz.get().getUsers().stream()
						.filter(u -> u.getRoles().stream().map(Role::getName).anyMatch(name -> name.toLowerCase().equals(SystemConstant.STUDENT)))
						.collect(Collectors.toList());
				model.addAttribute("clazz", opClazz.get());
				model.addAttribute("listTeacher", listTeacher);
				model.addAttribute("listStudent", listStudent);
				model.addAttribute("listGrades", opGrade.get());
				model.addAttribute("listClassrooms", opClassroom.get());
				model.addAttribute("listUsers", opUser.get());
				model.addAttribute("classname", opClazz.get().getName());
				model.addAttribute(SystemConstant.LINK, "clazzes");
				
				StringBuilder linkGoBack = new StringBuilder();
				linkGoBack.append(model.getAttribute(SystemConstant.LINK))
				.append("/").append("page/").append(pageNum).append("?sortField=").append(sortField)
				.append("&sortDir=").append(sortDir);
				if (keyword != null) {
					linkGoBack.append("&keyword=").append(keyword);
				}
				model.addAttribute(SystemConstant.LINK_GOBACK, linkGoBack);
				StaticUtil.setTitleAndStatic(model, title.toString(), List.of("clazzes_details.js"), List.of("clazz_form.css"));
			}
			return "clazzes/clazzes_details";
		} catch (EntityNotFoundException e) {
			redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE, e.getMessage());
		}
		return "redirect:/clazzes";
	}
	
	@GetMapping("clazzes/edit/{id}/classroom")
	public String editClassroom(@PathVariable(name="id") Long id, Model model, RedirectAttributes redirectAttributes) {
		try {
			Clazz clazz = service.get(id);
			List<Classroom> classrooms = classroomService.listAll();
			StringBuilder title = new StringBuilder();
			title.append(SystemConstant.TITLE_EDIT_CLAZZ_CLASSROOM).append(id);
			List<User> listTeacher = clazz.getUsers().stream()
					.filter(u -> u.getRoles().stream().map(Role::getName).anyMatch(name -> name.toLowerCase().equals(SystemConstant.TEACHER)))
					.collect(Collectors.toList());
			List<User> listStudent = clazz.getUsers().stream()
					.filter(u -> u.getRoles().stream().map(Role::getName).anyMatch(name -> name.toLowerCase().equals(SystemConstant.STUDENT)))
					.collect(Collectors.toList());
			model.addAttribute("clazz", clazz);
			model.addAttribute("listClassroom", classrooms);
			model.addAttribute("classname", clazz.getName());
			model.addAttribute("listTeacher", listTeacher);
			model.addAttribute("listStudent", listStudent);
			model.addAttribute(SystemConstant.LINK, "clazzes");
			model.addAttribute(SystemConstant.LINK_GOBACK,  new StringBuilder("clazzes/edit/").append(id));
			StaticUtil.setTitleAndStatic(model, title.toString(), null, List.of("clazz_form.css"));
			return "clazzes/edit_classroom";
		} catch (EntityNotFoundException e) {
			redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE, e.getMessage());
		}
		return "redirect:/clazzes";
	}
	@PostMapping("/clazzes/save-classroom")
	public String saveClassroom(Clazz clazz, RedirectAttributes redirectAttributes, Model model) throws IOException {
		service.saveClassroom(clazz);
		StringBuilder link = new StringBuilder();
		link.append("redirect:/clazzes/edit/").append(clazz.getId()).append("/classroom");
		return link.toString();
	}
	
	@GetMapping("clazzes/edit/{id}/teacher")
	public String editTeacher(@PathVariable(name="id") Long id, Model model, RedirectAttributes redirectAttributes) {
		return listByPageTeacher(id, 1, model, SystemConstant.ID, SystemConstant.ASC, null, redirectAttributes);
	}
	
	@GetMapping("/clazzes/edit/{id}/teacher/page/{pageNum}")
	public String listByPageTeacher(@PathVariable(name="id") Long id, @PathVariable(name="pageNum") int pageNum, 
			Model model, @Param("sortField") String sortField, @Param("sortDir") String sortDir, 
			@Param("keyword") String keyword, RedirectAttributes redirectAttributes) {
		try {
			Page<User> page = service.listByPageTeacher(id, pageNum, sortField, sortDir, keyword);
			List<User> listTeachers = page.getContent();
			
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
			model.addAttribute("listTeachers", listTeachers);
			model.addAttribute(SystemConstant.SORT_FILED, sortField);
			model.addAttribute(SystemConstant.SORT_DIR, sortDir);
			model.addAttribute(SystemConstant.REVERSE_SORT_DIR, reverseSortDir);
			model.addAttribute(SystemConstant.KEYWORD, keyword);
			
			Clazz clazz = service.get(id);
			StringBuilder title = new StringBuilder();
			title.append(SystemConstant.TITLE_EDIT_CLAZZ_TEACHER).append(id);
			List<User> listTeacher = clazz.getUsers().stream()
					.filter(u -> u.getRoles().stream().map(Role::getName).anyMatch(name -> name.toLowerCase().equals(SystemConstant.TEACHER)))
					.collect(Collectors.toList());
			List<User> listStudent = clazz.getUsers().stream()
					.filter(u -> u.getRoles().stream().map(Role::getName).anyMatch(name -> name.toLowerCase().equals(SystemConstant.STUDENT)))
					.collect(Collectors.toList());
			model.addAttribute("clazz", clazz);
			model.addAttribute("classname", clazz.getName());
			model.addAttribute("listTeacher", listTeacher);
			model.addAttribute("listStudent", listStudent);
			model.addAttribute(SystemConstant.LINK, "clazzes");
			model.addAttribute(SystemConstant.LINK_GOBACK,  new StringBuilder("clazzes/edit/").append(id));
			StaticUtil.setTitleAndStatic(model, title.toString(), null, List.of("clazz_form.css"));
			return "clazzes/edit_teacher";
		} catch (EntityNotFoundException e) {
			redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE, e.getMessage());
		}
		return "redirect:/clazzes";
	}
	@PostMapping("/clazzes/add-teacher")
	public String addTeacher(Long id, Long idTeacher, RedirectAttributes redirectAttributes, 
			Model model) throws IOException {
		service.addTeacher(id, idTeacher);
		StringBuilder link = new StringBuilder();
		link.append("redirect:/clazzes/edit/").append(id).append("/teacher");
		return link.toString();
	}
	@GetMapping("/clazzes/{id}/delete-teacher/{idTeacher}")
	public String deleteTeacher(@PathVariable("id") Long id, @PathVariable("idTeacher") Long idTeacher, RedirectAttributes redirectAttributes, 
			Model model) throws IOException {
		service.deleteTeacher(id, idTeacher);
		StringBuilder link = new StringBuilder();
		link.append("redirect:/clazzes/edit/").append(id).append("/teacher");
		return link.toString();
	}
	
	@GetMapping("clazzes/edit/{id}/student")
	public String editStudent(@PathVariable(name="id") Long id, Model model, RedirectAttributes redirectAttributes) {
		return listByPageStudent(id, 1, model, SystemConstant.ID, SystemConstant.ASC, null, redirectAttributes);
	}
	
	@GetMapping("/clazzes/edit/{id}/student/page/{pageNum}")
	public String listByPageStudent(@PathVariable(name="id") Long id, @PathVariable(name="pageNum") int pageNum, 
			Model model, @Param("sortField") String sortField, @Param("sortDir") String sortDir, 
			@Param("keyword") String keyword, RedirectAttributes redirectAttributes) {
		try {
			Page<User> page = service.listByPageStudent(id, pageNum, sortField, sortDir, keyword);
			List<User> listStudents = page.getContent();
			
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
			model.addAttribute("listStudents", listStudents);
			model.addAttribute(SystemConstant.SORT_FILED, sortField);
			model.addAttribute(SystemConstant.SORT_DIR, sortDir);
			model.addAttribute(SystemConstant.REVERSE_SORT_DIR, reverseSortDir);
			model.addAttribute(SystemConstant.KEYWORD, keyword);
			
			Clazz clazz = service.get(id);
			StringBuilder title = new StringBuilder();
			title.append(SystemConstant.TITLE_EDIT_CLAZZ_STUDENT).append(id);
			List<User> listTeacher = clazz.getUsers().stream()
					.filter(u -> u.getRoles().stream().map(Role::getName).anyMatch(name -> name.toLowerCase().equals(SystemConstant.TEACHER)))
					.collect(Collectors.toList());
			List<User> listStudent = clazz.getUsers().stream()
					.filter(u -> u.getRoles().stream().map(Role::getName).anyMatch(name -> name.toLowerCase().equals(SystemConstant.STUDENT)))
					.collect(Collectors.toList());
			model.addAttribute("clazz", clazz);
			model.addAttribute("classname", clazz.getName());
			model.addAttribute("listTeacher", listTeacher);
			model.addAttribute("listStudent", listStudent);
			model.addAttribute(SystemConstant.LINK, "clazzes");
			model.addAttribute(SystemConstant.LINK_GOBACK,  new StringBuilder("clazzes/edit/").append(id));
			StaticUtil.setTitleAndStatic(model, title.toString(), null, List.of("clazz_form.css"));
			return "clazzes/edit_student";
		} catch (EntityNotFoundException e) {
			redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE, e.getMessage());
		}
		return "redirect:/clazzes";
	}
	@PostMapping("/clazzes/add-student")
	public String addStudent(Long id, Long idStudent, RedirectAttributes redirectAttributes, 
			Model model) throws IOException {
		service.addStudent(id, idStudent);
		StringBuilder link = new StringBuilder();
		link.append("redirect:/clazzes/edit/").append(id).append("/student");
		return link.toString();
	}
	@GetMapping("/clazzes/{id}/delete-student/{idStudent}")
	public String deleteStudent(@PathVariable("id") Long id, @PathVariable("idStudent") Long idStudent, RedirectAttributes redirectAttributes, 
			Model model) throws IOException {
		service.deleteStudent(id, idStudent);
		StringBuilder link = new StringBuilder();
		link.append("redirect:/clazzes/edit/").append(id).append("/student");
		return link.toString();
	}
}
