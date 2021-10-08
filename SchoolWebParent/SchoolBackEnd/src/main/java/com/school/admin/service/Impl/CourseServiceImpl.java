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
import com.school.admin.repository.CourseRepository;
import com.school.admin.service.ClassroomService;
import com.school.admin.service.CourseService;
import com.school.common.common.SystemConstant;
import com.school.common.entity.Course;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository repo;

	@Override
	public List<Course> listAll() {
		Optional<List<Course>> list = Optional.ofNullable(repo.findAll(Sort.by(SystemConstant.NAME).ascending()));
		return list.orElse(null);
	}

	@Override
	public Page<Course> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals(SystemConstant.ASC) ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNum-1, ClassroomService.CLASSROOM_PER_PAGE, sort);
		
		if (keyword != null) {
			Optional<Page<Course>> list = Optional.ofNullable(repo.findAll(keyword, pageable));
			if (list.isPresent()) {
				return list.get();
			}
		}
		
		return repo.findAll(pageable);
	}

	@Override
	public Course save(Course Course) {
		boolean isUpdating = (Course.getId() != null);
		LocalDateTime dateNow = LocalDateTime.now();
		Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
		if (auth.isPresent()) {
			StringBuilder adminControl = new StringBuilder(auth.get().getName());
			if (isUpdating) {
				Optional<Course> opExist = repo.findById(Course.getId());
				if (opExist.isPresent()) {
					Course exsting = opExist.get();
					Course.setCreatedDate(exsting.getCreatedDate());
					Course.setCreatedBy(exsting.getCreatedBy());
					Course.setModifiedDate(dateNow);
					Course.setModifiedBy(adminControl.toString());
				}
				else return null;
			} else {
				Course.setCreatedDate(dateNow);
				Course.setCreatedBy(adminControl.toString());
			}
			return repo.save(Course);
		}
		return null;
	}

	@Override
	public boolean isNameUnique(Long id, String name) {
		Optional<Course> op = Optional.ofNullable(repo.getCourseByName(name));
		Course Course = op.orElse(null);
		if (Course == null) return true;
		boolean isCreatingNew = (id == null);
		if (isCreatingNew) {
			if (Course != null) return false;
		} else {
			if (Course.getId() != id)
				return false;
		}
		return true;
	}

	@Override
	public boolean isCodeUnique(Long id, String code) {
		Optional<Course> op = Optional.ofNullable(repo.getCourseByCode(code));
		Course Course = op.orElse(null);
		if (Course == null) return true;
		boolean isCreatingNew = (id == null);
		if (isCreatingNew) {
			if (Course != null) return false;
		} else {
			if (Course.getId() != id)
				return false;
		}
		return true;
	}

	@Override
	public Course get(Long id) throws EntityNotFoundException {
		try {
			Optional<Course> op = repo.findById(id);
			return op.orElse(null);
		} catch (NoSuchElementException e) {
			StringBuilder msg = new StringBuilder();
			msg.append(SystemConstant.NOT_FOUND_ID).append(id);
			throw new EntityNotFoundException(msg.toString());
		}
	}

	@Override
	public void deleteCourse(Long id) throws EntityNotFoundException {
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
			Optional<Course> opExist = Optional.ofNullable(repo.getById(id));
			if (opExist.isPresent()) {
				Course exsting = opExist.get();
				exsting.setModifiedDate(dateNow);
				exsting.setModifiedBy(adminControl.toString());
				repo.deleteById(id);
			}
		}
	}
	
	
}
