package com.school.admin.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.school.admin.exception.EntityNotFoundException;
import com.school.common.entity.Classroom;

public interface ClassroomService {

	public static int CLASSROOM_PER_PAGE = 5;

	public List<Classroom> listAll();
	public List<Classroom> searchKeyWord(String keyword);
	
	public Page<Classroom> listByPage(int pageNum, String sortField, String sortDir, String keyword);

	public Classroom save(Classroom classroom);

	public boolean isNameUnique(Long id, String name);
	
	public boolean isCodeUnique(Long id, String code);
	
	public Classroom get(Long id) throws EntityNotFoundException;

	public void deleteClassroom(Long id) throws EntityNotFoundException;
}
