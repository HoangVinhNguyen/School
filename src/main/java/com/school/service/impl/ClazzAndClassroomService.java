package com.school.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.school.DAO.IClazzAndClassroomDAO;
import com.school.constant.SystemConstant;
import com.school.entity.ClassroomEntity;
import com.school.entity.ClazzAndClassroomEntity;
import com.school.entity.ClazzEntity;
import com.school.model.ClassroomModel;
import com.school.model.ClazzAndClassroomModel;
import com.school.model.ClazzModel;
import com.school.service.IClassroomService;
import com.school.service.IClazzAndClassroomService;
import com.school.service.IClazzService;

@Service
public class ClazzAndClassroomService implements IClazzAndClassroomService {
	
	@Autowired
	private IClazzAndClassroomDAO clazzAndClassroomDAO;
	
	@Autowired
	private IClazzService clazzService;
	
	@Autowired
	private IClassroomService classroomService;

	@Override
	public List<ClazzAndClassroomModel> findAll() {
		List<ClazzAndClassroomModel> models = new ArrayList<ClazzAndClassroomModel>();
		List<ClazzAndClassroomEntity> entities = clazzAndClassroomDAO.findAll();
		Iterator<ClazzAndClassroomEntity> itr = entities.iterator();
		while(itr.hasNext()) {
			ClazzAndClassroomModel model = new ClazzAndClassroomModel();
			model.loadFromEntity(itr.next());
			models.add(model);
		}
		return models;
	}

	@Override
	public ClazzAndClassroomModel findOne(ClazzModel clazz, ClassroomModel classroom) {
		if (clazz != null && classroom != null) {
			ClazzAndClassroomModel model = new ClazzAndClassroomModel();
			ClazzEntity clazzEntity = new ClazzEntity();
			clazzEntity.loadFromDTO(clazz);
			ClassroomEntity classroomEntity = new ClassroomEntity();
			classroomEntity.loadFromDTO(classroom);
			model.loadFromEntity(clazzAndClassroomDAO.findOne(clazzEntity, classroomEntity));
			return model;
		}
		return null;
	}

	@Override
	public List<ClazzAndClassroomModel> findOneByClazzName(String name) {
		List<ClazzAndClassroomModel> listModel = new ArrayList<>();
		List<ClazzAndClassroomEntity> list = clazzAndClassroomDAO.findOneByClazzName(name);
		if (!list.isEmpty()) {
			list.forEach(e -> {
				ClazzAndClassroomModel model = new ClazzAndClassroomModel();
				model.loadFromEntity(e);
				listModel.add(model);
			});
			return listModel;
		}
		return null;
	}

	@Override
	public List<ClazzAndClassroomModel> findOneByClassroomName(String name) {
		List<ClazzAndClassroomModel> listModel = new ArrayList<>();
		List<ClazzAndClassroomEntity> list = clazzAndClassroomDAO.findOneByClassroomName(name);
		if (!list.isEmpty()) {
			list.forEach(e -> {
				ClazzAndClassroomModel model = new ClazzAndClassroomModel();
				model.loadFromEntity(e);
				listModel.add(model);
			});
			return listModel;
		}
		return null;
	}

	@Override
	public Long save(ClazzAndClassroomModel model, String method) {
		if (model != null && method != null) {
			model = getModifiedField(model, method);
			if (model.getClazzModel().getName() != null && model.getClassroomModel().getName() != null) {
				ClazzModel clazzModel = clazzService.findOneByName(model.getClazzModel().getName());
				ClassroomModel classroomModel = classroomService.findOneByName(model.getClassroomModel().getName());
				if (clazzModel != null && classroomModel != null) {
					model.setClazzModel(clazzModel);
					model.setClassroomModel(classroomModel);
				}
				ClazzAndClassroomEntity entity = new ClazzAndClassroomEntity();
				entity.loadFromDTO(model);
				return clazzAndClassroomDAO.save(entity);
			}
		}
		return SystemConstant.ERROR;
	}

	@Override
	public Long delete(ClazzAndClassroomModel model) {
		if (model != null && model.getId() != null) {
			ClazzAndClassroomEntity entity = new ClazzAndClassroomEntity();
			entity.loadFromDTO(model);
			return clazzAndClassroomDAO.delete(entity);
		}
		return SystemConstant.ERROR;
	}

	@Override
	public int getTotalItem() {
		return clazzAndClassroomDAO.getTotalItem();
	}
	
	private ClazzAndClassroomModel getModifiedField(ClazzAndClassroomModel model, String method) {
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
