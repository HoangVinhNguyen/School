package com.school.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.school.DAO.IPointDAO;
import com.school.constant.SystemConstant;
import com.school.entity.ClazzEntity;
import com.school.entity.CourseEntity;
import com.school.entity.PointEntity;
import com.school.entity.UserEntity;
import com.school.model.ClazzModel;
import com.school.model.CourseModel;
import com.school.model.PointModel;
import com.school.model.UserModel;
import com.school.service.IClazzService;
import com.school.service.ICourseService;
import com.school.service.IPointService;
import com.school.service.IUserService;

@Service
public class PointService implements IPointService {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICourseService courseService;
	
	@Autowired
	private IClazzService clazzService;
	
	@Autowired
	private IPointDAO pointDAO;

	@Override
	public List<PointModel> findAll() {
		List<PointEntity> listEntity = pointDAO.findAll();
		List<PointModel> listModel = new ArrayList<>();
		if (listEntity != null) {
			listEntity.forEach(e -> {
				PointModel model = new PointModel();
				model.loadFromEntity(e);
				listModel.add(model);
			});
			return listModel;
		}
		return null;
	}

	@Override
	public PointModel findOne(long id) {
		if (id > 0) {
			PointEntity entity = pointDAO.findOne(id);
			if (entity != null) {
				PointModel model = new PointModel();
				model.loadFromEntity(entity);
				return model;
			}
			return null;
		}
		return null;
	}

	@Override
	public PointModel findOneByUserCourseClazz(UserModel teacher, UserModel student, CourseModel course,
			ClazzModel clazz) {
		if (teacher.getEmail() != null && student.getEmail() != null && clazz.getName() != null && course.getName() != null) {
			UserModel teacherCheck = userService.findByEmailCheckTeacher(teacher.getEmail());
			UserModel studentCheck = userService.findByEmailCheckStudent(student.getEmail());
			CourseModel courseCheck = courseService.findOneByName(course.getName());
			ClazzModel clazzCheck = clazzService.findOneByName(clazz.getName());
			if (teacherCheck != null && studentCheck != null && courseCheck != null && clazzCheck != null) {
				UserEntity teacherEntity = new UserEntity();
				UserEntity studentEntity = new UserEntity();
				CourseEntity courseEntity = new CourseEntity();
				ClazzEntity clazzEntity = new ClazzEntity();
				teacherEntity.loadFromDTO(teacherCheck);
				studentEntity.loadFromDTO(studentCheck);
				courseEntity.loadFromDTO(courseCheck);
				clazzEntity.loadFromDTO(clazzCheck);
				PointEntity entity = pointDAO.findOneByUserCourseClazz(teacherEntity, studentEntity, courseEntity, clazzEntity);
				if (entity != null) {
					PointModel model = new PointModel();
					model.loadFromEntity(entity);
					return model;
				}
			}
		}
		return null;
	}

	@Override
	public List<PointModel> findAllByCourse(CourseModel course) {
		if (course != null && course.getName() != null) {
			CourseModel model = courseService.findOneByName(course.getName());
			if (model != null) {
				CourseEntity entity = new CourseEntity();
				entity.loadFromDTO(model);
				List<PointEntity> listE = pointDAO.findAllByCourse(entity);
				if (!listE.isEmpty()) {
					List<PointModel> listModel = new ArrayList<>();
					listE.forEach(e -> {
						PointModel m = new PointModel();
						m.loadFromEntity(e);
						listModel.add(m);
					});
					return listModel;
				}
			}
		}
		return null;
	}

	@Override
	public List<PointModel> findAllByClazz(ClazzModel clazz) {
		if (clazz != null && clazz.getName() != null) {
			ClazzModel model = clazzService.findOneByName(clazz.getName());
			if (model != null) {
				ClazzEntity entity = new ClazzEntity();
				entity.loadFromDTO(model);
				List<PointEntity> listE = pointDAO.findAllByClazz(entity);
				if (!listE.isEmpty()) {
					List<PointModel> listModel = new ArrayList<>();
					listE.forEach(e -> {
						PointModel m = new PointModel();
						m.loadFromEntity(e);
						listModel.add(m);
					});
					return listModel;
				}
			}
		}
		return null;
	}

	@Override
	public List<PointModel> findAllByTeacher(UserModel teacher) {
		if (teacher != null && teacher.getEmail() != null) {
			UserModel model = userService.findByEmailCheckTeacher(teacher.getEmail());
			if (model != null) {
				UserEntity entity = new UserEntity();
				entity.loadFromDTO(model);
				List<PointEntity> listE = pointDAO.findAllByTeacher(entity);
				if (!listE.isEmpty()) {
					List<PointModel> listModel = new ArrayList<>();
					listE.forEach(e -> {
						PointModel m = new PointModel();
						m.loadFromEntity(e);
						listModel.add(m);
					});
					return listModel;
				}
			}
		}
		return null;
	}

	@Override
	public List<PointModel> findAllByStudent(UserModel student) {
		if (student != null && student.getEmail() != null) {
			UserModel model = userService.findByEmailCheckStudent(student.getEmail());
			if (model != null) {
				UserEntity entity = new UserEntity();
				entity.loadFromDTO(model);
				List<PointEntity> listE = pointDAO.findAllByTeacher(entity);
				if (!listE.isEmpty()) {
					List<PointModel> listModel = new ArrayList<>();
					listE.forEach(e -> {
						PointModel m = new PointModel();
						m.loadFromEntity(e);
						listModel.add(m);
					});
					return listModel;
				}
			}
		}
		return null;
	}

	@Override
	public Long save(PointModel model, String method) {
		if (model != null && method != null) {
			model = getModifiedField(model, method);
			if (model.getTeacher().getEmail() != null && model.getStudent().getEmail() != null
					&& model.getClazz().getName() != null && model.getCourse().getName() != null) {
				UserModel teacherCheck = userService.findByEmailCheckTeacher(model.getTeacher().getEmail());
				UserModel studentCheck = userService.findByEmailCheckStudent(model.getStudent().getEmail());
				CourseModel courseCheck = courseService.findOneByName(model.getCourse().getName());
				ClazzModel clazzCheck = clazzService.findOneByName(model.getClazz().getName());
				if (teacherCheck != null && studentCheck != null && courseCheck != null && clazzCheck != null) {
					model.setTeacher(teacherCheck);
					model.setStudent(studentCheck);
					model.setCourse(courseCheck);
					model.setClazz(clazzCheck);
					PointModel modelCheck = findOneByUserCourseClazz(model.getTeacher(), model.getStudent(), model.getCourse(), model.getClazz());
					if (modelCheck != null) {
						return SystemConstant.DUPLICATE;
					}
					PointEntity entity = new PointEntity();
					entity.loadFromDTO(model);
					return pointDAO.save(entity);
				}
			}
		}
		return SystemConstant.ERROR;
	}

	@Override
	public Long savePoint(PointModel model, String method) {
		if (model != null) {
			model = getModifiedField(model, method);
			if (model.getTeacher().getEmail() != null && model.getStudent().getEmail() != null
					&& model.getClazz().getName() != null && model.getCourse().getName() != null) {
				UserModel teacherCheck = userService.findByEmailCheckTeacher(model.getTeacher().getEmail());
				UserModel studentCheck = userService.findByEmailCheckStudent(model.getStudent().getEmail());
				CourseModel courseCheck = courseService.findOneByName(model.getCourse().getName());
				ClazzModel clazzCheck = clazzService.findOneByName(model.getClazz().getName());
				if (teacherCheck != null && studentCheck != null && courseCheck != null && clazzCheck != null) {
					model.setTeacher(teacherCheck);
					model.setStudent(studentCheck);
					model.setCourse(courseCheck);
					model.setClazz(clazzCheck);
					PointModel modelCheck = findOneByUserCourseClazz(model.getTeacher(), model.getStudent(), model.getCourse(), model.getClazz());
					if (modelCheck != null) {
						PointEntity entity = new PointEntity();
						entity.loadFromDTO(model);
						return pointDAO.savePoint(entity);
					}
				}
			}
		}
		return SystemConstant.ERROR;
	}

	@Override
	public Long delete(PointModel model, String method) {
		if (model != null && model.getId() != null) {
			model = getModifiedField(model, method);
			PointModel modelCheck = findOne(model.getId());
			if (modelCheck != null) {
				if (model.getId() == modelCheck.getId()) {
					PointEntity entity = new PointEntity();
					entity.loadFromDTO(modelCheck);
					return pointDAO.delete(entity);
				}
			}
		}
		return SystemConstant.ERROR;
	}

	@Override
	public int getTotalItem() {
		return pointDAO.getTotalItem();
	}
	
	private PointModel getModifiedField(PointModel model, String method) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		switch (method) {
		case SystemConstant.INSERT:
			model.setModifiedDate(timestamp);
			model.setCreatedBy(authentication.getName());
			model.setCreatedDate(timestamp);
			break;
		case SystemConstant.MODIFY:
			model.setModifiedDate(timestamp);
			model.setModifiedBy(authentication.getName());
			break;
		}
		return model;
	}

}
