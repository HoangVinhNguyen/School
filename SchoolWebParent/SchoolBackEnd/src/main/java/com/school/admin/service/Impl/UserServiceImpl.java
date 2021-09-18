package com.school.admin.service.Impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.admin.exception.UserNotFoundException;
import com.school.admin.repository.RoleRepository;
import com.school.admin.repository.UserRepository;
import com.school.admin.service.UserService;
import com.school.admin.util.FileUploadUtil;
import com.school.common.common.SystemConstant;
import com.school.common.entity.Role;
import com.school.common.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<User> listAll() {
		return (List<User>) userRepo.findAll(Sort.by("firstName").ascending());
	}

	@Override
	public List<Role> listRoles() {
		return (List<Role>) roleRepo.findAll();
	}
	
	@Override
	public Page<User> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNum-1, USER_PER_PAGE, sort);
		
		if (keyword != null) {
			return userRepo.findAll(keyword, pageable);
		}
		
		return userRepo.findAll(pageable);
	}

	@Override
	public User save(User user) {
		boolean isUpdatingUser = (user.getId() != null);
		if (isUpdatingUser) {
			User exstingUser = userRepo.findById(user.getId()).get();
			if (user.getPassword().isEmpty()) {
				user.setPassword(exstingUser.getPassword());
			} else {
				encodePassword(user);
			}
		} else {
			encodePassword(user);
		}
		return userRepo.save(user);
	}
	
	@Override
	public User updateAccount(User userInForm) {
		User userInDB = userRepo.findById(userInForm.getId()).get();
		if (!userInForm.getPassword().isEmpty()) {
			userInDB.setPassword(userInForm.getPassword());
			encodePassword(userInDB);
		}
		
		if (userInForm.getPhotos() != null) {
			userInDB.setPhotos(userInForm.getPhotos());
		}
		
		userInDB.setFirstName(userInForm.getFirstName());
		userInDB.setLastName(userInDB.getLastName());
		
		return userRepo.save(userInDB);
	}

	private void encodePassword(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
	}

	@Override
	public boolean isEmailUnique(Integer id, String email) {
		User userByEmail = userRepo.getUserByEmail(email);
		if (userByEmail == null) return true;
		boolean isCreatingNew = (id == null);
		if (isCreatingNew) {
			if (userByEmail != null) return false;
		} else {
			if (userByEmail.getId() != id)
				return false;
		}
		return true;
	}
	
	@Override
	public User getByEmail(String email) {
		return userRepo.getUserByEmail(email);
	}

	@Override
	public User get(Integer id) throws UserNotFoundException {
		try {
			return userRepo.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new UserNotFoundException("Could not find any user with ID " + id);
		}
	}
	
	@Override
	public void deleteUser(Integer id) throws UserNotFoundException {
		Long countById = userRepo.countById(id);
		if (countById == null || countById == 0) {
			throw new UserNotFoundException("Could not find any user with ID " + id);
		}
		userRepo.deleteUser(id);
		StringBuilder uploadDir = new StringBuilder();
		uploadDir.append(SystemConstant.PHOTOS_OF_USERS_FOLDER);
		uploadDir.append(SystemConstant.FORWARD_SLASH);
		uploadDir.append(id);
		FileUploadUtil.cleanDir(uploadDir.toString());
	}
	
	public void updateUserEnableStatus(Integer id, boolean enabled) {
		userRepo.updateEnableStatus(id, enabled);
	}
	
}
