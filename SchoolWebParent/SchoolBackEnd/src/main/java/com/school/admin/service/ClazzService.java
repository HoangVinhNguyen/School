package com.school.admin.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.school.admin.exception.EntityNotFoundException;
import com.school.common.dto.UserDto;
import com.school.common.entity.Clazz;

public interface ClazzService {

	public static int CLAZZ_PER_PAGE = 5;

	public List<Clazz> listAll();
	
	public List<Clazz> listAllByOrderGradeId();

	public Page<Clazz> listByPage(int pageNum, String sortField, String sortDir, String keyword);

	public Clazz save(Clazz level);
	
	public UserDto addUserToClass(Long id, String email, String role);
	
	public UserDto deleteUserInClass(Long id, String email);

	public boolean checkUserInClass(Long id, String email);

	public boolean isNameUnique(Long id, String name);
	
	public boolean isCodeUnique(Long id, String code);
	
	public Clazz get(Long id) throws EntityNotFoundException;

	public void deleteClazz(Long id) throws EntityNotFoundException;
}
