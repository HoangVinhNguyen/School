package com.school.admin.controller.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.school.admin.exception.EntityNotFoundException;
import com.school.admin.service.CourseService;
import com.school.admin.service.UserService;
import com.school.admin.util.FileUploadUtil;
import com.school.admin.util.StaticUtil;
import com.school.admin.util.export.UserCsvExporter;
import com.school.admin.util.export.UserExcelExporter;
import com.school.admin.util.export.UserPdfExporter;
import com.school.common.common.SystemConstant;
import com.school.common.entity.Course;
import com.school.common.entity.Role;
import com.school.common.entity.User;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private CourseService courseService;

	@GetMapping("/users")
	public String listFirstPage(Model model) {
		return listByPage(1, model, SystemConstant.FIRST_NAME, SystemConstant.ASC, null, null);
	}
	
	@GetMapping("/users/page/{pageNum}")
	public String listByPage(@PathVariable(name="pageNum") int pageNum, Model model,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir, 
			@Param("keyword") String keyword, @Param("typeFilter") String typeFilter) {
		List<String> listTypes = new ArrayList<String>();
		listTypes.add(SystemConstant.ALL_SELECT);
		listTypes.add(SystemConstant.TEACHER_SELECT);
		listTypes.add(SystemConstant.STUDENT_SELECT);
		Page<User> page = service.listByPage(pageNum, sortField, sortDir, keyword, typeFilter);
		List<User> listUsers = page.getContent();
		
		long startCount = (pageNum - 1) * UserService.USER_PER_PAGE + 1;
		long endCount = startCount + UserService.USER_PER_PAGE - 1;
		
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		String reverseSortDir = sortDir.equals(SystemConstant.ASC) ? SystemConstant.DESC : SystemConstant.ASC;
		
		model.addAttribute(SystemConstant.CURRENT_PAGE, pageNum);
		model.addAttribute(SystemConstant.TOTAL_PAGE, page.getTotalPages());
		model.addAttribute(SystemConstant.START_COUNT, startCount);
		model.addAttribute(SystemConstant.END_COUNT, endCount);
		model.addAttribute(SystemConstant.TOTAL_ITEM, page.getTotalElements());
		model.addAttribute("listUsers", listUsers);
		model.addAttribute("listTypes", listTypes);
		model.addAttribute("listType", typeFilter != null ? typeFilter : SystemConstant.ALL_SELECT);
		model.addAttribute(SystemConstant.SORT_FILED, sortField);
		model.addAttribute(SystemConstant.SORT_DIR, sortDir);
		model.addAttribute(SystemConstant.REVERSE_SORT_DIR, reverseSortDir);
		model.addAttribute(SystemConstant.KEYWORD, keyword);
		model.addAttribute(SystemConstant.LINK, "users");
		StaticUtil.setTitleAndStatic(model, SystemConstant.TITLE_USERS);
		return "users/users";
	}

	@GetMapping("/users/new")
	public String newUser(Model model) {
		List<Course> listCourse =  courseService.listAll();
		List<Role> listRoleDtos = service.listRoles();
		User user = new User();
		user.setEnabled(true);
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoleDtos);
		model.addAttribute("listCourse", listCourse);
		model.addAttribute(SystemConstant.LINK, "users");
		StaticUtil.setTitleAndStatic(model, SystemConstant.TITLE_CREATE_NEW_USER, null, List.of("user_form.css"));
		return "users/user_form";
	}
	
	@GetMapping("/users/new-list")
	public String newListUser(Model model) {
		StaticUtil.setTitleAndStatic(model, SystemConstant.TITLE_CREATE_NEW_LIST_USER, null, List.of("user_form.css"));
		return "users/create_teacher_list";
	}

	@PostMapping("/users/save")
	public String saveUser(User user, RedirectAttributes redirectAttributes, 
			@RequestParam("image") MultipartFile multipartFile) throws IOException {
		if (!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			user.setPhotos(fileName);
			User savedUser = service.save(user);
			StringBuilder uploadDir = new StringBuilder();
			uploadDir.append(SystemConstant.PHOTOS_OF_USERS_FOLDER)
				.append(SystemConstant.FORWARD_SLASH)
				.append(savedUser.getId());
			FileUploadUtil.cleanDir(uploadDir.toString());
			FileUploadUtil.saveFile(uploadDir.toString(), fileName, multipartFile);
		} else {
			if (user.getPhotos().isEmpty()) user.setPhotos(null);
			service.save(user);
		}
		
		redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE, SystemConstant.ATTR_CONTENT_USER_SAVE_SUCCESS);
		return getRedirectURLtoAffectedUser(user);
	}

	private String getRedirectURLtoAffectedUser(User user) {
		String firstPArtOfEmail = user.getEmail().split("@")[0];
		return "redirect:/users/page/1?sortField=id&sortDir=asc&keyword=" + firstPArtOfEmail;
	}

	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes, Model model) {
		try {
			User user = service.get(id);
			List<Role> listRoleDtos = service.listRoles();
			List<Course> listCourse =  courseService.listAll();
			StringBuilder title = new StringBuilder();
			title.append(SystemConstant.TITLE_EDIT_USER).append(id);

			model.addAttribute("listRoles", listRoleDtos);
			model.addAttribute("user", user);
			model.addAttribute("listCourse", listCourse);
			model.addAttribute(SystemConstant.LINK, "users");
			StaticUtil.setTitleAndStatic(model, title.toString(), null, List.of("user_form.css"));
			return "users/user_form";
		} catch (EntityNotFoundException e) {
			redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE, e.getMessage());
		}
		return "redirect:/users";
	}

	@GetMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
		try {
			service.deleteUser(id);
			redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE,
					SystemConstant.ATTR_CONTENT_USER_DEL_SUCCESS(id));
		} catch (EntityNotFoundException e) {
			redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE, e.getMessage());
		}
		return "redirect:/users";
	}

	@GetMapping("/users/{id}/enabled/{status}")
	public String updateUserEnableStatus(@PathVariable(name="id") Long id, 
			@PathVariable(name = "status") boolean enabled, RedirectAttributes redirectAttributes) {
		service.updateUserEnableStatus(id, enabled);
		String status = enabled ? SystemConstant.ENABLED : SystemConstant.DISABLED;
		redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE, 
				SystemConstant.ATTR_CONTENT_USER_STATUS_SUCCESS(id, status));
		
		return "redirect:/users";
	}
	
	@PostMapping("users/generate-list-teacher")
	public String generateListTeacher(RedirectAttributes redirectAttributes, 
			@RequestParam("fileListTeacher") MultipartFile multipartFile) {
		
		if (!multipartFile.isEmpty()) {
			boolean result = service.saveListTeacherFile(multipartFile);
			if (result)
				redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE, SystemConstant.ATTR_CONTENT_TEACHER_GENERATE_SUCCESS);
			else
				redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE, SystemConstant.ATTR_CONTENT_TEACHER_GENERATE_ERROR);
		} else {
			redirectAttributes.addFlashAttribute(SystemConstant.ATTR_MESSAGE, SystemConstant.ATTR_CONTENT_TEACHER_GENERATE_ERROR);
		}
		return "redirect:/users";
	}
	
	@GetMapping("/users/export/csv")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		List<User> listUsers = service.listAll();
		UserCsvExporter exporter = new UserCsvExporter();
		exporter.export(listUsers, response);
	}
	
	@GetMapping("/users/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		List<User> listUsers = service.listAll();
		UserExcelExporter exporter = new UserExcelExporter();
		exporter.export(listUsers, response);
	}
	
	@GetMapping("/users/export/pdf")
	public void exportToPdf(HttpServletResponse response) throws IOException {
		List<User> listUsers = service.listAll();
		UserPdfExporter exporter = new UserPdfExporter();
		exporter.export(listUsers, response);
	}
}
