package com.school.admin.controller.user;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.school.admin.security.SchoolUserDetails;
import com.school.admin.service.UserService;
import com.school.admin.util.FileUploadUtil;
import com.school.common.common.SystemConstant;
import com.school.common.entity.User;

@Controller
public class AccountController {

	@Autowired
	private UserService service;
	
	@GetMapping("/account")
	public String viewDetails(@AuthenticationPrincipal SchoolUserDetails loggedUser, Model model) {
		String email = loggedUser.getUsername();
		User user = service.getByEmail(email);
		model.addAttribute("user", user);
		model.addAttribute(SystemConstant.PAGE_TILE, SystemConstant.TITLE_ACCOUNT_DETAILS);
		model.addAttribute(SystemConstant.JS_FILE, List.of("common.js"));
		model.addAttribute(SystemConstant.CSS_FILE, List.of("style.css"));
		return "users/account_form";
	}
	
	@PostMapping("/account/update")
	public String saveUser(User user, RedirectAttributes redirectAttributes,
			@AuthenticationPrincipal SchoolUserDetails loggedUser,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {
		
		if (!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			user.setPhotos(fileName);
			User savedUser = service.updateAccount(user);
			StringBuilder uploadDir = new StringBuilder();
			uploadDir.append(SystemConstant.PHOTOS_OF_USERS_FOLDER);
			uploadDir.append(SystemConstant.FORWARD_SLASH);
			uploadDir.append(savedUser.getId());
			FileUploadUtil.cleanDir(uploadDir.toString());
			FileUploadUtil.saveFile(uploadDir.toString(), fileName, multipartFile);
		} else {
			if (user.getPhotos().isEmpty()) user.setPhotos(null);
			service.updateAccount(user);
		}
		
		loggedUser.setFirstName(user.getFirstName());
		loggedUser.setLastName(user.getLastName());
		
		redirectAttributes.addFlashAttribute("message", "Your account details have been updated");
		
		return "redirect:/account";
	}
	
}
