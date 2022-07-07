package com.school.service;

import com.school.common.dto.UserDto;
import com.school.common.entity.User;

public interface UserService {

	public User findUserByEmail(String email);
	public User findUserById(Long id);
	public UserDto findUserByIdDto(Long id);
}
