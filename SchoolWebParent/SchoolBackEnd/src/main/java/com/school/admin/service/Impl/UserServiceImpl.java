package com.school.admin.service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.admin.exception.EntityNotFoundException;
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
		Optional<List<User>> list = Optional.ofNullable(userRepo.findAll(Sort.by(SystemConstant.FIRST_NAME).ascending()));
		return list.orElse(null);
	}

	@Override
	public List<Role> listRoles() {
		Optional<List<Role>> list = Optional.ofNullable(roleRepo.findAll());
		return list.orElse(null);
	}

	@Override
	public Page<User> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals(SystemConstant.ASC) ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNum-1, USER_PER_PAGE, sort);
		
		if (keyword != null) {
			Optional<Page<User>> listUser = Optional.ofNullable(userRepo.findAll(keyword, pageable));
			if (listUser.isPresent()) {
				return listUser.get();
			}
		}
		
		return userRepo.findAll(pageable);
	}

	@Override
	public User save(User user) {
		boolean isUpdatingUser = (user.getId() != null);
		LocalDateTime dateNow = LocalDateTime.now();
		Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
		if (auth.isPresent()) {
			StringBuilder adminControl = new StringBuilder(auth.get().getName());
			if (isUpdatingUser) {
				Optional<User> opExist = userRepo.findById(user.getId());
				if (opExist.isPresent()) {
					User exstingUser = opExist.get();
					if (user.getPassword().isEmpty()) {
						user.setPassword(exstingUser.getPassword());
					} else {
						encodePassword(user);
					}
					user.setCreatedDate(exstingUser.getCreatedDate());
					user.setCreatedBy(exstingUser.getCreatedBy());
					user.setModifiedDate(dateNow);
					user.setModifiedBy(adminControl.toString());
				}
				else return null;
			} else {
				encodePassword(user);
				user.setCreatedDate(dateNow);
				user.setCreatedBy(adminControl.toString());
			}
			return userRepo.save(user);
		}
		return null;
	}
	
	@Override
	public User updateAccount(User userInForm) {
		LocalDateTime dateNow = LocalDateTime.now();
		Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
		if (auth.isPresent()) {
			StringBuilder adminControl = new StringBuilder(auth.get().getName());
			Optional<User> opUser = userRepo.findById(userInForm.getId());
			if (opUser.isPresent()) {
				User userInDB = opUser.get();
				if (!userInForm.getPassword().isEmpty()) {
					userInDB.setPassword(userInForm.getPassword());
					encodePassword(userInDB);
				}
				
				if (userInForm.getPhotos() != null) {
					userInDB.setPhotos(userInForm.getPhotos());
				}
				
				userInDB.setFirstName(userInForm.getFirstName());
				userInDB.setLastName(userInDB.getLastName());
				userInDB.setModifiedDate(dateNow);
				userInDB.setModifiedBy(adminControl.toString());
				
				return userRepo.save(userInDB);
			}
			else return null;
		}
		return null;
	}

	private void encodePassword(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
	}

	@Override
	public boolean isEmailUnique(Long id, String email) {
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
	public User get(Long id) throws EntityNotFoundException {
		try {
			Optional<User> opUser = userRepo.findById(id);
			return opUser.orElse(null);
		} catch (NoSuchElementException e) {
			StringBuilder msg = new StringBuilder();
			msg.append(SystemConstant.NOT_FOUND_ID).append(id);
			throw new EntityNotFoundException(msg.toString());
		}
	}
	
	@Override
	public void deleteUser(Long id) throws EntityNotFoundException {
		LocalDateTime dateNow = LocalDateTime.now();
		Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
		if (auth.isPresent()) {
			StringBuilder adminControl = new StringBuilder(auth.get().getName());
			Long countById = userRepo.countById(id);
			if (countById == null || countById == 0) {
				StringBuilder msg = new StringBuilder();
				msg.append(SystemConstant.NOT_FOUND_ID).append(id);
				throw new EntityNotFoundException(msg.toString());
			}
			Optional<User> opExist = Optional.ofNullable(userRepo.getById(id));
			if (opExist.isPresent()) {
				User exsting = opExist.get();
				exsting.setModifiedDate(dateNow);
				exsting.setModifiedBy(adminControl.toString());
				userRepo.deleteById(id);
				StringBuilder uploadDir = new StringBuilder();
				uploadDir.append(SystemConstant.PHOTOS_OF_USERS_FOLDER)
				.append(SystemConstant.FORWARD_SLASH)
				.append(id);
				FileUploadUtil.cleanDir(uploadDir.toString());
			}
		}
	}
	
	public void updateUserEnableStatus(Long id, boolean enabled) {
		userRepo.updateEnableStatus(id, enabled);
	}

}
