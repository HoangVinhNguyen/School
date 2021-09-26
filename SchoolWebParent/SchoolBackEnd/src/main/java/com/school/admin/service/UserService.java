package com.school.admin.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.school.admin.exception.UserNotFoundException;
import com.school.common.entity.Role;
import com.school.common.entity.User;

public interface UserService {

	public static int USER_PER_PAGE = 5;

	public List<User> listAll();

	public List<Role> listRoles();

	public Page<User> listByPage(int pageNum, String sortField, String sortDir, String keyword);

	public User save(User userDto);

	public User updateAccount(User userInForm);

	public boolean isEmailUnique(Long id, String email);

	public User getByEmail(String email);

	public User get(Long id) throws UserNotFoundException;

	public void deleteUser(Long id) throws UserNotFoundException;

	public void updateUserEnableStatus(Long id, boolean enabled);
}
