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
import org.springframework.web.multipart.MultipartFile;

import com.school.admin.exception.EntityNotFoundException;
import com.school.admin.repository.ClazzRepository;
import com.school.admin.service.ClazzService;
import com.school.admin.service.GradeService;
import com.school.admin.service.UserService;
import com.school.common.common.SystemConstant;
import com.school.common.dto.UserDto;
import com.school.common.entity.Clazz;
import com.school.common.entity.Grade;
import com.school.common.entity.Role;
import com.school.common.entity.User;

@Service
@Transactional
public class ClazzServiceImpl implements ClazzService {

	@Autowired
	private ClazzRepository repo;

	@Autowired
	private UserService userService;
	
	@Autowired
	private GradeService gradeService;

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
		Pageable pageable = PageRequest.of(pageNum - 1, ClazzService.CLAZZ_PER_PAGE, sort);

		if (keyword != null) {
			Optional<Page<Clazz>> list = Optional.ofNullable(repo.findAll(keyword, pageable));
			if (list.isPresent()) {
				return list.get();
			}
		}

		return repo.findAll(pageable);
	}

	@Override
	public Page<User> listByPageTeacher(Long id, int pageNum, String sortField, String sortDir, String keyword) {
		Sort sort = Sort.by((new StringBuilder("u.").append(sortField)).toString());
		sort = sortDir.equals(SystemConstant.ASC) ? sort.ascending() : sort.descending();
		//sort = Sort.sort(User.class);
		Pageable pageable = PageRequest.of(pageNum - 1, ClazzService.CLAZZ_PER_PAGE, sort);
		if (keyword != null) {
			Optional<Page<User>> list = Optional.ofNullable(repo.findAllTeacher(id, keyword, pageable));
			if (list.isPresent()) {
				return list.get();
			}
		}

		return repo.findAllTeacher(id, pageable);
	}
	
	@Override
	public Page<User> listByPageStudent(Long id, int pageNum, String sortField, String sortDir, String keyword) {
		Sort sort = Sort.by((new StringBuilder("u.").append(sortField)).toString());
		sort = sortDir.equals(SystemConstant.ASC) ? sort.ascending() : sort.descending();
		//sort = Sort.sort(User.class);
		Pageable pageable = PageRequest.of(pageNum - 1, ClazzService.CLAZZ_PER_PAGE, sort);
		if (keyword != null) {
			Optional<Page<User>> list = Optional.ofNullable(repo.findAllStudent(id, keyword, pageable));
			if (list.isPresent()) {
				return list.get();
			}
		}
		
		return repo.findAllStudent(id, pageable);
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
					exsting.setName(clazz.getName());
					exsting.setCode(clazz.getCode());
					exsting.setDescription(clazz.getDescription());
					exsting.setGrade(clazz.getGrade());
					exsting.setModifiedDate(dateNow);
					exsting.setModifiedBy(adminControl.toString());
					return repo.save(exsting);
				} else
					return null;
			} else {
				clazz.setCreatedDate(dateNow);
				clazz.setCreatedBy(adminControl.toString());
			}
			return repo.save(clazz);
		}
		return null;
	}

	@Override
	public Clazz saveClassroom(Clazz clazz) {
		boolean isUpdating = (clazz.getId() != null);
		LocalDateTime dateNow = LocalDateTime.now();
		Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
		if (auth.isPresent() && !clazz.getClassrooms().isEmpty()) {
			StringBuilder adminControl = new StringBuilder(auth.get().getName());
			if (isUpdating) {
				Optional<Clazz> opExist = repo.findById(clazz.getId());
				if (opExist.isPresent()) {
					Clazz exsting = opExist.get();
					exsting.setModifiedDate(dateNow);
					exsting.setModifiedBy(adminControl.toString());
					exsting.setClassrooms(clazz.getClassrooms());
					return repo.save(exsting);
				} else
					return null;
			}
		}
		return null;
	}

	@Override
	public Clazz addTeacher(Long idClazz, Long idTeacher) {
		boolean isUpdating = (idClazz != null && idTeacher!= null);
		LocalDateTime dateNow = LocalDateTime.now();
		Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
		if (auth.isPresent() && idClazz != null) {
			StringBuilder adminControl = new StringBuilder(auth.get().getName());
			if (isUpdating) {
				Optional<Clazz> opExist = repo.findById(idClazz);
				Optional<User> opUser = Optional.ofNullable(userService.getById(idTeacher));
				if (opExist.isPresent() && opUser.isPresent()) {
					Clazz exsting = opExist.get();
					exsting.setModifiedDate(dateNow);
					exsting.setModifiedBy(adminControl.toString());
					exsting.addUser(opUser.get());
					return repo.save(exsting);
				} else
					return null;
			}
		}
		return null;
	}

	@Override
	public Clazz deleteTeacher(Long idClazz, Long idTeacher) {
		boolean isUpdating = (idClazz != null && idTeacher!= null);
		LocalDateTime dateNow = LocalDateTime.now();
		Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
		if (auth.isPresent() && idClazz != null) {
			StringBuilder adminControl = new StringBuilder(auth.get().getName());
			if (isUpdating) {
				Optional<Clazz> opExist = repo.findById(idClazz);
				Optional<User> opUser = Optional.ofNullable(userService.getById(idTeacher));
				if (opExist.isPresent() && opUser.isPresent()) {
					Clazz exsting = opExist.get();
					exsting.setModifiedDate(dateNow);
					exsting.setModifiedBy(adminControl.toString());
					exsting.getUsers().remove(opUser.get());
					return repo.save(exsting);
				} else
					return null;
			}
		}
		return null;
	}
	
	@Override
	public Clazz addStudent(Long idClazz, Long idStudent) {
		boolean isUpdating = (idClazz != null && idStudent!= null);
		LocalDateTime dateNow = LocalDateTime.now();
		Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
		if (auth.isPresent() && idClazz != null) {
			StringBuilder adminControl = new StringBuilder(auth.get().getName());
			if (isUpdating) {
				Optional<Clazz> opExist = repo.findById(idClazz);
				Optional<User> opUser = Optional.ofNullable(userService.getById(idStudent));
				if (opExist.isPresent() && opUser.isPresent()) {
					Clazz exsting = opExist.get();
					exsting.setModifiedDate(dateNow);
					exsting.setModifiedBy(adminControl.toString());
					exsting.addUser(opUser.get());
					return repo.save(exsting);
				} else
					return null;
			}
		}
		return null;
	}
	
	@Override
	public Clazz deleteStudent(Long idClazz, Long idStudent) {
		boolean isUpdating = (idClazz != null && idStudent!= null);
		LocalDateTime dateNow = LocalDateTime.now();
		Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
		if (auth.isPresent() && idClazz != null) {
			StringBuilder adminControl = new StringBuilder(auth.get().getName());
			if (isUpdating) {
				Optional<Clazz> opExist = repo.findById(idClazz);
				Optional<User> opUser = Optional.ofNullable(userService.getById(idStudent));
				if (opExist.isPresent() && opUser.isPresent()) {
					Clazz exsting = opExist.get();
					exsting.setModifiedDate(dateNow);
					exsting.setModifiedBy(adminControl.toString());
					exsting.getUsers().remove(opUser.get());
					return repo.save(exsting);
				} else
					return null;
			}
		}
		return null;
	}

	@Override
	public UserDto addUserToClass(Long id, String email) {
		Optional<Clazz> opClazz = repo.findById(id);
		Optional<User> opUser = Optional.ofNullable(userService.getByEmail(email));
		if (opClazz.isPresent() && opUser.isPresent()) {
			Clazz clazz = opClazz.get();
			User user = opUser.get();
			clazz.addUser(user);
			repo.save(clazz);
			return new UserDto(user);
		}
		return null;
	}

	@Override
	public UserDto addTeacherToClass(Long id, String email) {
		Optional<Clazz> opClazz = repo.findById(id);
		Optional<User> opUser = Optional.ofNullable(userService.getByEmail(email));
		if (opClazz.isPresent() && opUser.isPresent()) {
			Clazz clazz = opClazz.get();
			User user = opUser.get();
			if (checkRoleUser(user, SystemConstant.TEACHER)) {
				if (clazz.getUsers().contains(user)) {
					return null;
				}
				clazz.addUser(user);
				repo.save(clazz);
				return new UserDto(user);
			}
		}
		return null;
	}

	@Override
	public UserDto addStudentToClass(Long id, String email) {
		Optional<Clazz> opClazz = repo.findById(id);
		Optional<User> opUser = Optional.ofNullable(userService.getByEmail(email));
		if (opClazz.isPresent() && opUser.isPresent()) {
			Clazz clazz = opClazz.get();
			User user = opUser.get();
			if (checkRoleUser(user, SystemConstant.STUDENT)) {
				if (clazz.getUsers().contains(user)) {
					return null;
				}
				clazz.addUser(user);
				repo.save(clazz);
				return new UserDto(user);
			}
		}
		return null;
	}

	private boolean checkRoleUser(User user, String role) {
		if (role != null) {
			for (Role r : user.getRoles()) {
				if (r.getName().toLowerCase().equals(role))
					return true;
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
			if (clazz.getUsers().contains(user))
				return true;
		}
		return false;
	}

	@Override
	public boolean isNameUnique(Long id, String name) {
		Optional<Clazz> op = Optional.ofNullable(repo.getClazzByName(name));
		Clazz clazz = op.orElse(null);
		if (clazz == null)
			return true;
		boolean isCreatingNew = (id == null);
		if (isCreatingNew) {
			if (clazz != null)
				return false;
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
		if (clazz == null)
			return true;
		boolean isCreatingNew = (id == null);
		if (isCreatingNew) {
			if (clazz != null)
				return false;
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

	@Override
	public boolean saveListClassFile(MultipartFile multipartFile, int numberOfClass, int grade) {
		List<User> listStudent = userService.saveListStudentFile(multipartFile);
		int sizeListStudent = listStudent.size();
		int pointBreak = sizeListStudent / numberOfClass;
		int studentMod = sizeListStudent - pointBreak * numberOfClass;
		for (int i = 0; i < numberOfClass; i++) {
			int addStudentMod = 0;
			if (i == numberOfClass - 1) {
				addStudentMod += studentMod;
			}
			Grade grd = new Grade();
			try {
				grd = gradeService.getByCode(grade);
			} catch (EntityNotFoundException e) {
				e.printStackTrace();
			}
			StringBuilder className = new StringBuilder();
			className.append(grade).append("A").append(i+1);
			Clazz clazz = new Clazz();
			clazz.setName(className.toString());
			clazz.setCode(className.toString().toLowerCase());
			clazz.setGrade(grd);
			clazz.setDescription("For class " + className);
			for (int j = 0 + pointBreak * i; j < pointBreak * i + pointBreak + addStudentMod; j++) {
				clazz.addUser(listStudent.get(j));
			}
			save(clazz);
		}
		return false;
	}

}
