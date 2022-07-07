package com.school.admin.service.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import org.springframework.web.multipart.MultipartFile;

import com.school.admin.exception.EntityNotFoundException;
import com.school.admin.repository.RoleRepository;
import com.school.admin.repository.UserRepository;
import com.school.admin.service.UserService;
import com.school.admin.util.FileUploadUtil;
import com.school.admin.util.NamePersonUtils;
import com.school.common.common.SystemConstant;
import com.school.common.dto.UserDto;
import com.school.common.entity.Course;
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
	public List<User> listAllTeacher() {
		Optional<List<User>> list = Optional.ofNullable(userRepo.findAllTeacher(Sort.by(SystemConstant.FIRST_NAME).ascending()));
		return list.orElse(null);
	}

	@Override
	public List<User> listAllStudent() {
		Optional<List<User>> list = Optional.ofNullable(userRepo.findAllStudent(Sort.by(SystemConstant.FIRST_NAME).ascending()));
		return list.orElse(null);
	}

	@Override
	public List<Role> listRoles() {
		Optional<List<Role>> list = Optional.ofNullable(roleRepo.findAll());
		return list.orElse(null);
	}

	@Override
	public Page<User> listByPage(int pageNum, String sortField, String sortDir, String keyword, String typeFilter) {
		Optional<Page<User>> listUser = null;
		if (typeFilter != null) {
			switch (typeFilter) {
			case SystemConstant.TEACHER_SELECT:
				listUser = Optional.ofNullable(listByPageTeacher(pageNum, sortField, sortDir, keyword));
				break;
			case SystemConstant.STUDENT_SELECT:
				listUser = Optional.ofNullable(listByPageStudent(pageNum, sortField, sortDir, keyword));
				break;
			default:
				listUser = Optional.ofNullable(listByPageAll(pageNum, sortField, sortDir, keyword));
				break;
			}
		}
		else {
			listUser = Optional.ofNullable(listByPageAll(pageNum, sortField, sortDir, keyword));
		}
		return listUser.orElse(null);
	}
	@Override
	public Page<User> listByPageAll(int pageNum, String sortField, String sortDir, String keyword) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals(SystemConstant.ASC) ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNum - 1, UserService.USER_PER_PAGE, sort);

		if (keyword != null && !keyword.equals("")) {
			Optional<Page<User>> list = Optional.ofNullable(userRepo.findAll(keyword, pageable));
			if (list.isPresent()) {
				return list.get();
			}
		}

		return userRepo.findAll(pageable);
	}
	@Override
	public Page<User> listByPageTeacher(int pageNum, String sortField, String sortDir, String keyword) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals(SystemConstant.ASC) ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNum - 1, UserService.USER_PER_PAGE, sort);

		if (keyword != null && !keyword.equals("")) {
			Optional<Page<User>> list = Optional.ofNullable(userRepo.findAllTeacher(keyword, pageable));
			if (list.isPresent()) {
				return list.get();
			}
		}

		return userRepo.findAllTeacher(pageable);
	}
	@Override
	public Page<User> listByPageStudent(int pageNum, String sortField, String sortDir, String keyword) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals(SystemConstant.ASC) ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNum - 1, UserService.USER_PER_PAGE, sort);
		
		if (keyword != null && !keyword.equals("")) {
			Optional<Page<User>> list = Optional.ofNullable(userRepo.findAllStudent(keyword, pageable));
			if (list.isPresent()) {
				return list.get();
			}
		}
		
		return userRepo.findAllStudent(pageable);
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
					user.setClazzes(exstingUser.getClazzes());
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
		User userByEmail = userRepo.findUserByEmail(email);
		if (userByEmail == null) return true;
		boolean isCreatingNew = (id == null);
		if (isCreatingNew) {
			if (userByEmail != null) return false;
		} else {
			if (userByEmail.getId().longValue() != id.longValue())
				return false;
		}
		return true;
	}
	
	@Override
	public User getByEmail(String email) {
		Optional<User> op = Optional.ofNullable(userRepo.findUserByEmail(email));
		return op.orElse(null);
	}
	@Override
	public User getById(Long id) {
		Optional<User> op = Optional.ofNullable(userRepo.findUserById(id));
		return op.orElse(null);
	}
	
	@Override
	public UserDto getByEmailRest(String email) {
		Optional<User> op = Optional.ofNullable(userRepo.findUserByEmail(email));
		return new UserDto(op.orElse(null));
	}

	@Override
	public UserDto getByIdRest(Long id) throws EntityNotFoundException {
		try {
			Optional<User> op = Optional.ofNullable(userRepo.findUserById(id));
			return new UserDto(op.orElse(null));
		} catch (NoSuchElementException e) {
			StringBuilder msg = new StringBuilder();
			msg.append(SystemConstant.NOT_FOUND_ID).append(id);
			throw new EntityNotFoundException(msg.toString());
		}
	}

	@Override
	public UserDto getByIdTeacherRest(Long id) throws EntityNotFoundException {
		try {
			Optional<User> op = Optional.ofNullable(userRepo.findByIdTeacherRest(id));
			return new UserDto(op.orElse(null));
		} catch (NoSuchElementException e) {
			StringBuilder msg = new StringBuilder();
			msg.append(SystemConstant.NOT_FOUND_ID).append(id);
			throw new EntityNotFoundException(msg.toString());
		}
	}
	
	@Override
	public UserDto getByIdStudentRest(Long id) throws EntityNotFoundException {
		try {
			Optional<User> op = Optional.ofNullable(userRepo.findByIdStudentRest(id));
			return new UserDto(op.orElse(null));
		} catch (NoSuchElementException e) {
			StringBuilder msg = new StringBuilder();
			msg.append(SystemConstant.NOT_FOUND_ID).append(id);
			throw new EntityNotFoundException(msg.toString());
		}
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

	@Override
	public boolean saveListTeacherFile(MultipartFile file) {
		String extensionFile = FilenameUtils.getExtension(file.getOriginalFilename());
		try {
			InputStream contentFile = file.getInputStream();
			if (SystemConstant.EXTENSION_XLSX.equals(extensionFile)) {
				XSSFWorkbook wb = new XSSFWorkbook(contentFile);
				XSSFSheet sheet = wb.getSheetAt(0);
				for (Row row : sheet)
				{
					if (row.getRowNum() > 1) {
						
						User teacher = new User();
						for (Cell cell : row)
						{
							/*
							 * _NONE(-1), NUMERIC(0), STRING(1), FORMULA(2), BLANK(3), BOOLEAN(4), ERROR(5);
							 */
							
							switch (cell.getColumnIndex()) {
							case 0: // STT.
								break;
							case 1: // FullName.
								teacher.setFirstName(NamePersonUtils.getFirstName(cell.getStringCellValue()));
								teacher.setLastName(NamePersonUtils.getLastName(cell.getStringCellValue()));
								teacher.setEmail(NamePersonUtils.getMailName(cell.getStringCellValue()));
								break;
							case 2: // Dob.
								String dayString = cell.getStringCellValue();
								String[] dobS = dayString.split("/");
								int year = Integer.valueOf(dobS[dobS.length-1]);
								int month = Integer.valueOf(dobS[dobS.length-2]);
								int day = Integer.valueOf(dobS[dobS.length-3]);
								StringBuilder mail = new StringBuilder(teacher.getEmail());
								LocalDate dob = LocalDate.of(year, month, day);
								teacher.setDob(dob);
								mail.append(day).append(month).append(SystemConstant.DOMAIN_EMAIL);
								String email = mail.toString();
								teacher.setEmail(email);
								break;
							default:
								break;
							}
						}
						// save user.
						teacher.setPassword(SystemConstant.DEFAULT_PASSWORD);
						teacher.addRole(new Role(2L));
						teacher.setEnabled(true);
						save(teacher);
					}
				}
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<User> saveListStudentFile(MultipartFile file) {
		String extensionFile = FilenameUtils.getExtension(file.getOriginalFilename());
		List<User> listStudents = new ArrayList<>();
		try {
			InputStream contentFile = file.getInputStream();
			if (SystemConstant.EXTENSION_XLSX.equals(extensionFile)) {
				XSSFWorkbook wb = new XSSFWorkbook(contentFile);
				XSSFSheet sheet = wb.getSheetAt(0);
				for (Row row : sheet)
				{
					if (row.getRowNum() > 1) {
						
						User student = new User();
						for (Cell cell : row)
						{
							/*
							 * _NONE(-1), NUMERIC(0), STRING(1), FORMULA(2), BLANK(3), BOOLEAN(4), ERROR(5);
							 */
							
							switch (cell.getColumnIndex()) {
							case 0: // STT.
								break;
							case 1: // FullName.
								student.setFirstName(NamePersonUtils.getFirstName(cell.getStringCellValue()));
								student.setLastName(NamePersonUtils.getLastName(cell.getStringCellValue()));
								student.setEmail(NamePersonUtils.getMailName(cell.getStringCellValue()));
								break;
							case 2: // Dob.
								String dayString = cell.getStringCellValue();
								String[] dobS = dayString.split("/");
								int year = Integer.valueOf(dobS[dobS.length-1]);
								int month = Integer.valueOf(dobS[dobS.length-2]);
								int day = Integer.valueOf(dobS[dobS.length-3]);
								StringBuilder mail = new StringBuilder(student.getEmail());
								LocalDate dob = LocalDate.of(year, month, day);
								student.setDob(dob);
								mail.append(day).append(month).append(SystemConstant.DOMAIN_EMAIL_STUDENT);
								String email = mail.toString();
								student.setEmail(email);
								break;
							default:
								break;
							}
						}
						// save user.
						student.setPassword(SystemConstant.DEFAULT_PASSWORD);
						student.addRole(new Role(3L));
						student.setEnabled(true);
						User user = save(student);
						listStudents.add(user);
					}
				}
				return listStudents;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listStudents;
	}

	@Override
	public String getNamePrinciple() {
		Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
		if (auth.isPresent()) {
			return auth.get().getName();
		}
		return null;
	}

	@Override
	public List<User> getListTeacherByClass(Long classId) {
		Optional<List<User>> list = Optional.ofNullable(userRepo.findAllTeacherByClassId(classId));
		return list.orElse(null);
	}
	@Override
	public List<User> getListStudentByClass(Long classId) {
		Optional<List<User>> list = Optional.ofNullable(userRepo.findAllStudentByClassId(classId));
		return list.orElse(null);
	}

	@Override
	public boolean saveCourseToStudentsByClass(Long classId) {
		List<User> listTeacherInClass = userRepo.findAllTeacherByClassId(classId);
		List<User> listStudentInClass = userRepo.findAllStudentByClassId(classId);
		Set<Course> courses = new HashSet<>();
		
		if (listTeacherInClass.size() > 0) {
			listTeacherInClass.stream().map(teacher -> courses.addAll(teacher.getCourses())).collect(Collectors.toSet());
			listStudentInClass.stream().forEach(student -> student.setCourses(courses));
			userRepo.saveAll(listStudentInClass);
			return true;
		}
		return false;
	}

	@Override
	public boolean saveCourseToStudentByClass(Long classId, Long studenId) {
		List<User> listTeacherInClass = userRepo.findAllTeacherByClassId(classId);
		User student = userRepo.findUserById(studenId);
		Set<Course> courses = new HashSet<>();
		
		if (listTeacherInClass.size() > 0 && student != null) {
			listTeacherInClass.stream().map(teacher -> courses.addAll(teacher.getCourses())).collect(Collectors.toSet());
			student.setCourses(courses);
			userRepo.save(student);
			return true;
		}
		return false;
	}

}
