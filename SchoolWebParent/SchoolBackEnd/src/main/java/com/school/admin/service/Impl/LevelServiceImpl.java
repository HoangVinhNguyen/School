package com.school.admin.service.Impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.school.admin.exception.LevelNotFoundException;
import com.school.admin.repository.LevelRepository;
import com.school.admin.service.LevelService;
import com.school.common.common.SystemConstant;
import com.school.common.entity.Level;

@Service
@Transactional
public class LevelServiceImpl implements LevelService {

	@Autowired
	private LevelRepository repo;

	@Override
	public List<Level> listAll() {
		Optional<List<Level>> list = Optional.ofNullable(repo.findAll(Sort.by(SystemConstant.NAME).ascending()));
		return list.orElse(null);
	}

	@Override
	public Page<Level> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals(SystemConstant.ASC) ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNum-1, LevelService.LEVEL_PER_PAGE, sort);
		
		if (keyword != null) {
			Optional<Page<Level>> listLevel = Optional.ofNullable(repo.findAll(keyword, pageable));
			if (listLevel.isPresent()) {
				return listLevel.get();
			}
		}
		
		return repo.findAll(pageable);
	}

	@Override
	public Level save(Level level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isNameAndCodeUnique(Long id, String name, String code) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Level get(Long id) throws LevelNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteLevel(Long id) throws LevelNotFoundException {
		// TODO Auto-generated method stub
		
	}
	
	
}
