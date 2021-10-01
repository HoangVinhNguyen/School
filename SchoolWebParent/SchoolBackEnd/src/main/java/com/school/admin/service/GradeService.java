package com.school.admin.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.school.admin.exception.EntityNotFoundException;
import com.school.common.entity.Grade;

public interface GradeService {

	public static int GRADE_PER_PAGE = 5;

	public List<Grade> listAll();

	public Page<Grade> listByPage(int pageNum, String sortField, String sortDir, String keyword);

	public Grade save(Grade level);

	public boolean isNameUnique(Long id, String name);
	
	public boolean isCodeUnique(Long id, Integer code);
	
	public Grade get(Long id) throws EntityNotFoundException;

	public void deleteGrade(Long id) throws EntityNotFoundException;
}
