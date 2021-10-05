package com.school.admin.service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.school.admin.exception.EntityNotFoundException;
import com.school.admin.repository.ClazzRepository;
import com.school.admin.service.ClazzService;
import com.school.admin.service.UserService;
import com.school.common.common.SystemConstant;
import com.school.common.dto.UserDto;
import com.school.common.entity.Clazz;
import com.school.common.entity.Role;
import com.school.common.entity.User;

@Service
@Transactional
public class ClazzServiceImpl implements ClazzService {

	@Autowired
	private ClazzRepository repo;
	
	@Autowired
	private UserService userService;

	@Override
	public List<Clazz> listAll() {
		Optional<List<Clazz>> list = Optional.ofNullable(repo.findAll(Sort.by(SystemConstant.NAME).ascending()));
		return list.orElse(null);
	}
	
	@Override
	public List<Clazz> listAllByOrderGradeId() {
		Optional<List<Clazz>> list = Optional.ofNullable(repo.findAll(Sort.by("grade").ascending()));
		return list.orElse(null);
	}

	@Override
	public Page<Clazz> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals(SystemConstant.ASC) ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNum-1, ClazzService.CLAZZ_PER_PAGE, sort);
		
		if (keyword != null) {
			Optional<Page<Clazz>> list = Optional.ofNullable(repo.findAll(keyword, pageable));
			if (list.isPresent()) {
				return list.get();
			}
		}
		
		return repo.findAll(pageable);
	}

	@Override
	public Clazz save(Clazz clazz) {
		boolean isUpdating = (clazz.getId() != null);
		LocalDateTime dateNow = LocalDateTime.now();
		Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
		if (auth.isPresent()) {
			StringBuilder adminControl = new StringBuilder(auth.get().getName());
			if (isUpdating) {
				Optional<Clazz> opExist = repo.findById(clazz.getId());
				if (opExist.isPresent()) {
					Clazz exsting = opExist.get();
					clazz.setCreatedDate(exsting.getCreatedDate());
					clazz.setCreatedBy(exsting.getCreatedBy());
					clazz.setModifiedDate(dateNow);
					clazz.setModifiedBy(adminControl.toString());
				}
				else return null;
			} else {
				clazz.setCreatedDate(dateNow);
				clazz.setCreatedBy(adminControl.toString());
			}
			return repo.save(clazz);
		}
		return null;
	}
	
	@Override
	public UserDto addUserToClass(Long id, String email, String role) {
		boolean checkRole = false;
		Optional<Clazz> opClazz = repo.findById(id);
		Optional<User> opUser = Optional.ofNullable(userService.getByEmail(email));
		if (opClazz.isPresent() && opUser.isPresent() && role != null) {
			Clazz clazz = opClazz.get();
			User user = opUser.get();
			checkRole = checkRoleUser(user, role);
			if (checkRole) {
				clazz.addUser(user);
				repo.save(clazz);
				return new UserDto(user);
			}
			return null;
		}
		return null;
	}
	private boolean checkRoleUser(User user, String role) {
		if (role != null) {
			for (Role r : user.getRoles()) {
				if (r.getName().toLowerCase().equals(role)) return true;
			}
		}
		return false;
	}
	
	@Override
	public UserDto deleteUserInClass(Long id, String email) {
		Optional<Clazz> opClazz = repo.findById(id);
		Optional<User> opUser = Optional.ofNullable(userService.getByEmail(email));
		if (opClazz.isPresent() && opUser.isPresent()) {
			Clazz clazz = opClazz.get();
			User user = opUser.get();
			clazz.removeUser(user);
			repo.save(clazz);
			return new UserDto(user);
		}
		return null;
	}

	@Override
	public boolean checkUserInClass(Long id, String email) {
		Optional<Clazz> opClazz = repo.findById(id);
		Optional<User> opUser = Optional.ofNullable(userService.getByEmail(email));
		if (opClazz.isPresent() && opUser.isPresent()) {
			Clazz clazz = opClazz.get();
			User user = opUser.get();
			if (clazz.getUsers().contains(user)) return true;
		}
		return false;
	}

	@Override
	public boolean isNameUnique(Long id, String name) {
		Optional<Clazz> op = Optional.ofNullable(repo.getClazzByName(name));
		Clazz clazz = op.orElse(null);
		if (clazz == null) return true;
		boolean isCreatingNew = (id == null);
		if (isCreatingNew) {
			if (clazz != null) return false;
		} else {
			if (clazz.getId() != id)
				return false;
		}
		return true;
	}

	@Override
	public boolean isCodeUnique(Long id, String code) {
		Optional<Clazz> op = Optional.ofNullable(repo.getClazzByCode(code));
		Clazz clazz = op.orElse(null);
		if (clazz == null) return true;
		boolean isCreatingNew = (id == null);
		if (isCreatingNew) {
			if (clazz != null) return false;
		} else {
			if (clazz.getId() != id)
				return false;
		}
		return true;
	}

	@Override
	public Clazz get(Long id) throws EntityNotFoundException {
		try {
			Optional<Clazz> op = repo.findById(id);
			return op.orElse(null);
		} catch (NoSuchElementException e) {
			StringBuilder msg = new StringBuilder();
			msg.append(SystemConstant.NOT_FOUND_ID).append(id);
			throw new EntityNotFoundException(msg.toString());
		}
	}

	@Override
	public void deleteClazz(Long id) throws EntityNotFoundException {
		LocalDateTime dateNow = LocalDateTime.now();
		Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
		if (auth.isPresent()) {
			StringBuilder adminControl = new StringBuilder(auth.get().getName());
			Long countById = repo.countById(id);
			if (countById == null || countById == 0) {
				StringBuilder msg = new StringBuilder();
				msg.append(SystemConstant.NOT_FOUND_ID).append(id);
				throw new EntityNotFoundException(msg.toString());
			}
			Optional<Clazz> opExist = Optional.ofNullable(repo.getById(id));
			if (opExist.isPresent()) {
				Clazz exsting = opExist.get();
				exsting.setModifiedDate(dateNow);
				exsting.setModifiedBy(adminControl.toString());
				repo.deleteById(id);
			}
		}
	}

}
