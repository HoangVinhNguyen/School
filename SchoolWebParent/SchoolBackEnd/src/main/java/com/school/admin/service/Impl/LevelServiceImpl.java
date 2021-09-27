package com.school.admin.service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
		boolean isUpdating = (level.getId() != null);
		LocalDateTime dateNow = LocalDateTime.now();
		Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
		if (auth.isPresent()) {
			StringBuilder adminControl = new StringBuilder(auth.get().getName());
			if (isUpdating) {
				Optional<Level> opExist = repo.findById(level.getId());
				if (opExist.isPresent()) {
					Level exsting = opExist.get();
					level.setCreatedDate(exsting.getCreatedDate());
					level.setCreatedBy(exsting.getCreatedBy());
					level.setModifiedDate(dateNow);
					level.setModifiedBy(adminControl.toString());
				}
				else return null;
			} else {
				level.setCreatedDate(dateNow);
				level.setCreatedBy(adminControl.toString());
			}
			return repo.save(level);
		}
		return null;
	}

	@Override
	public boolean isNameUnique(Long id, String name) {
		Optional<Level> op = Optional.ofNullable(repo.getLevelByName(name));
		Level level = op.orElse(null);
		if (level == null) return true;
		boolean isCreatingNew = (id == null);
		if (isCreatingNew) {
			if (level != null) return false;
		} else {
			if (level.getId() != id)
				return false;
		}
		return true;
	}

	@Override
	public boolean isCodeUnique(Long id, String code) {
		Optional<Level> op = Optional.ofNullable(repo.getLevelByCode(code));
		Level level = op.orElse(null);
		if (level == null) return true;
		boolean isCreatingNew = (id == null);
		if (isCreatingNew) {
			if (level != null) return false;
		} else {
			if (level.getId() != id)
				return false;
		}
		return true;
	}

	@Override
	public Level get(Long id) throws LevelNotFoundException {
		try {
			Optional<Level> op = repo.findById(id);
			return op.orElse(null);
		} catch (NoSuchElementException e) {
			StringBuilder msg = new StringBuilder();
			msg.append(SystemConstant.NOT_FOUND_ID).append(id);
			throw new LevelNotFoundException(msg.toString());
		}
	}

	@Override
	public void deleteLevel(Long id) throws LevelNotFoundException {
		Long countById = repo.countById(id);
		if (countById == null || countById == 0) {
			StringBuilder msg = new StringBuilder();
			msg.append(SystemConstant.NOT_FOUND_ID).append(id);
			throw new LevelNotFoundException(msg.toString());
		}
		repo.deleteById(id);
	}
	
	
}
