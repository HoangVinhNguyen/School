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

import com.school.admin.exception.EntityNotFoundException;
import com.school.admin.repository.GradeRepository;
import com.school.admin.service.GradeService;
import com.school.common.common.SystemConstant;
import com.school.common.entity.Grade;

@Service
@Transactional
public class GradeServiceImpl implements GradeService {

	@Autowired
	private GradeRepository repo;

	@Override
	public List<Grade> listAll() {
		Optional<List<Grade>> list = Optional.ofNullable(repo.findAll(Sort.by(SystemConstant.CODE).ascending()));
		return list.orElse(null);
	}

	@Override
	public Page<Grade> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals(SystemConstant.ASC) ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNum-1, GradeService.GRADE_PER_PAGE, sort);
		
		if (keyword != null) {
			Optional<Page<Grade>> list = Optional.ofNullable(repo.findAll(keyword, pageable));
			if (list.isPresent()) {
				return list.get();
			}
		}
		
		return repo.findAll(pageable);
	}

	@Override
	public Grade save(Grade grade) {
		boolean isUpdating = (grade.getId() != null);
		LocalDateTime dateNow = LocalDateTime.now();
		Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
		if (auth.isPresent()) {
			StringBuilder adminControl = new StringBuilder(auth.get().getName());
			if (isUpdating) {
				Optional<Grade> opExist = repo.findById(grade.getId());
				if (opExist.isPresent()) {
					Grade exsting = opExist.get();
					grade.setCreatedDate(exsting.getCreatedDate());
					grade.setCreatedBy(exsting.getCreatedBy());
					grade.setModifiedDate(dateNow);
					grade.setModifiedBy(adminControl.toString());
				}
				else return null;
			} else {
				grade.setCreatedDate(dateNow);
				grade.setCreatedBy(adminControl.toString());
			}
			return repo.save(grade);
		}
		return null;
	}

	@Override
	public boolean isNameUnique(Long id, String name) {
		Optional<Grade> op = Optional.ofNullable(repo.getGradeByName(name));
		Grade grade = op.orElse(null);
		if (grade == null) return true;
		boolean isCreatingNew = (id == null);
		if (isCreatingNew) {
			if (grade != null) return false;
		} else {
			if (grade.getId() != id)
				return false;
		}
		return true;
	}

	@Override
	public boolean isCodeUnique(Long id, Integer code) {
		Optional<Grade> op = Optional.ofNullable(repo.getGradeByCode(code));
		Grade grade = op.orElse(null);
		if (grade == null) return true;
		boolean isCreatingNew = (id == null);
		if (isCreatingNew) {
			if (grade != null) return false;
		} else {
			if (grade.getId() != id)
				return false;
		}
		return true;
	}

	@Override
	public Grade get(Long id) throws EntityNotFoundException {
		try {
			Optional<Grade> op = repo.findById(id);
			return op.orElse(null);
		} catch (NoSuchElementException e) {
			StringBuilder msg = new StringBuilder();
			msg.append(SystemConstant.NOT_FOUND_ID).append(id);
			throw new EntityNotFoundException(msg.toString());
		}
	}

	@Override
	public void deleteGrade(Long id) throws EntityNotFoundException {
		LocalDateTime dateNow = LocalDateTime.now();
		Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
		if (auth.isPresent()) {
			StringBuilder adminControl = new StringBuilder(auth.get().getName());
			Long countById = repo.countById(id);
			if (countById == null || countById == 0) {
				StringBuilder msg = new StringBuilder();
				msg.append(SystemConstant.NOT_FOUND_ID).append(id);
				throw new EntityNotFoundException(msg.toString());
			}
			Optional<Grade> opExist = Optional.ofNullable(repo.getById(id));
			if (opExist.isPresent()) {
				Grade exsting = opExist.get();
				exsting.setModifiedDate(dateNow);
				exsting.setModifiedBy(adminControl.toString());
				repo.deleteById(id);
			}
		}
	}
	
}
