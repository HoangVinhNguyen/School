package com.school.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.common.dto.UserDto;
import com.school.common.entity.User;
import com.school.repository.UserRepository;
import com.school.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repo;

	@Override
	public User findUserByEmail(String email) {
		Optional<User> op = Optional.ofNullable(repo.findUserByEmail(email));
		return op.orElseGet(null);
	}

	@Override
	public User findUserById(Long id) {
		Optional<User> op = Optional.ofNullable(repo.findUserById(id));
		return op.orElseGet(null);
	}

	@Override
	public UserDto findUserByIdDto(Long id) {
		Optional<User> op = Optional.ofNullable(repo.findUserById(id));
		if (op.isPresent()) {
			return new UserDto(op.get());
		}
		return null;
	}

}
