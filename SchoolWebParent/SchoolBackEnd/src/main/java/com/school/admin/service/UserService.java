package com.school.admin.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.school.admin.exception.EntityNotFoundException;
import com.school.common.dto.UserDto;
import com.school.common.entity.Role;
import com.school.common.entity.User;

public interface UserService {

	public static int USER_PER_PAGE = 5;

	public List<User> listAll();
	public List<User> listAllTeacher();
	public List<User> listAllStudent();

	public List<Role> listRoles();
	
	public Page<User> listByPage(int pageNum, String sortField, String sortDir, String keyword);
	public Page<User> listByPageTeacher(Long id, int pageNum, String sortField, String sortDir, String keyword);

	public User save(User user);

	public User updateAccount(User userInForm);

	public boolean isEmailUnique(Long id, String email);

	public User getByEmail(String email);
	public User getById(Long id);
	public UserDto getByEmailRest(String email);
	public UserDto getByIdRest(Long id) throws EntityNotFoundException;
	public UserDto getByIdTeacherRest(Long id) throws EntityNotFoundException;
	public UserDto getByIdStudentRest(Long id) throws EntityNotFoundException;
	
	public User get(Long id) throws EntityNotFoundException;

	public void deleteUser(Long id) throws EntityNotFoundException;

	public void updateUserEnableStatus(Long id, boolean enabled);
}
