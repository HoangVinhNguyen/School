package com.school.admin.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.school.admin.exception.LevelNotFoundException;
import com.school.common.entity.Level;

public interface LevelService {

	public static int LEVEL_PER_PAGE = 5;

	public List<Level> listAll();

	public Page<Level> listByPage(int pageNum, String sortField, String sortDir, String keyword);

	public Level save(Level level);

	public boolean isNameUnique(Long id, String name);
	
	public boolean isCodeUnique(Long id, String code);
	
	public Level get(Long id) throws LevelNotFoundException;

	public void deleteLevel(Long id) throws LevelNotFoundException;
}
