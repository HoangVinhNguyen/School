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
import com.school.admin.repository.ClassroomRepository;
import com.school.admin.service.ClassroomService;
import com.school.admin.service.ClazzService;
import com.school.common.common.SystemConstant;
import com.school.common.entity.Classroom;
import com.school.common.entity.Clazz;

@Service
@Transactional
public class ClassroomServiceImpl implements ClassroomService {

	@Autowired
	private ClassroomRepository repo;
	
	@Autowired
	private ClazzService clazzService;

	@Override
	public List<Classroom> listAll() {
		Optional<List<Classroom>> list = Optional.ofNullable(repo.findAll(Sort.by(SystemConstant.NAME).ascending()));
		return list.orElse(null);
	}

	@Override
	public Page<Classroom> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals(SystemConstant.ASC) ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNum-1, ClassroomService.CLASSROOM_PER_PAGE, sort);
		
		if (keyword != null) {
			Optional<Page<Classroom>> list = Optional.ofNullable(repo.findAll(keyword, pageable));
			if (list.isPresent()) {
				return list.get();
			}
		}
		
		return repo.findAll(pageable);
	}

	@Override
	public Classroom save(Classroom classroom) {
		boolean isUpdating = (classroom.getId() != null);
		LocalDateTime dateNow = LocalDateTime.now();
		Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
		if (auth.isPresent()) {
			StringBuilder adminControl = new StringBuilder(auth.get().getName());
			if (isUpdating) {
				Optional<Classroom> opExist = repo.findById(classroom.getId());
				if (opExist.isPresent()) {
					Classroom exsting = opExist.get();
					classroom.setCreatedDate(exsting.getCreatedDate());
					classroom.setCreatedBy(exsting.getCreatedBy());
					classroom.setModifiedDate(dateNow);
					classroom.setModifiedBy(adminControl.toString());
				}
				else return null;
			} else {
				classroom.setCreatedDate(dateNow);
				classroom.setCreatedBy(adminControl.toString());
			}
			classroom.getClazzes().forEach(clazz -> {
				Clazz clazzNew = clazz;
				clazzNew.addClassroom(classroom);
				clazzService.save(clazzNew);
			});
			return repo.save(classroom);
		}
		return null;
	}

	@Override
	public boolean isNameUnique(Long id, String name) {
		Optional<Classroom> op = Optional.ofNullable(repo.getClassroomByName(name));
		Classroom classroom = op.orElse(null);
		if (classroom == null) return true;
		boolean isCreatingNew = (id == null);
		if (isCreatingNew) {
			if (classroom != null) return false;
		} else {
			if (classroom.getId() != id)
				return false;
		}
		return true;
	}

	@Override
	public boolean isCodeUnique(Long id, String code) {
		Optional<Classroom> op = Optional.ofNullable(repo.getClassroomByCode(code));
		Classroom classroom = op.orElse(null);
		if (classroom == null) return true;
		boolean isCreatingNew = (id == null);
		if (isCreatingNew) {
			if (classroom != null) return false;
		} else {
			if (classroom.getId() != id)
				return false;
		}
		return true;
	}

	@Override
	public Classroom get(Long id) throws EntityNotFoundException {
		try {
			Optional<Classroom> op = repo.findById(id);
			return op.orElse(null);
		} catch (NoSuchElementException e) {
			StringBuilder msg = new StringBuilder();
			msg.append(SystemConstant.NOT_FOUND_ID).append(id);
			throw new EntityNotFoundException(msg.toString());
		}
	}

	@Override
	public void deleteClassroom(Long id) throws EntityNotFoundException {
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
			Optional<Classroom> opExist = Optional.ofNullable(repo.getById(id));
			if (opExist.isPresent()) {
				Classroom exsting = opExist.get();
				exsting.setModifiedDate(dateNow);
				exsting.setModifiedBy(adminControl.toString());
				repo.deleteById(id);
			}
		}
	}
	
	
}
