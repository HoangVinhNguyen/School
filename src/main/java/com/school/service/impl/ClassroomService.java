package com.school.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.school.DAO.IClassroomDAO;
import com.school.constant.SystemConstant;
import com.school.entity.ClassroomEntity;
import com.school.entity.GradeEntity;
import com.school.model.ClassroomModel;
import com.school.model.GradeModel;
import com.school.service.IClassroomService;

@Service
public class ClassroomService implements IClassroomService {

	@Autowired
	private IClassroomDAO classroomDAO;
	
	@Override
	public ClassroomModel findOne(long id) {
		ClassroomModel model = new ClassroomModel();
		model.loadFromEntity(classroomDAO.findOne(id));
		return model;
	}

	@Override
	public ClassroomModel findOneByCode(String code) {
		ClassroomModel model = new ClassroomModel();
		model.loadFromEntity(classroomDAO.findOneByCode(code));
		return model;
	}
	
	@Override
	public ClassroomModel findOneByName(String name) {
		ClassroomModel model = new ClassroomModel();
		model.loadFromEntity(classroomDAO.findOneByName(name));
		return model;
	}

	@Override
	public int getTotalItem() {
		return classroomDAO.getTotalItem();
	}

	@Override
	public Long save(ClassroomModel model, String method) {
		model = getModifiedField(model, method);
		ClassroomEntity classroomEntity = new ClassroomEntity();
		classroomEntity.loadFromDTO(model);
		Long result = classroomDAO.save(classroomEntity);
		return result;
	}

	@Override
	public List<ClassroomModel> findAll() {
		List<ClassroomModel> classroomModels = new ArrayList<ClassroomModel>();
		List<ClassroomEntity> classroomEntities = classroomDAO.findAll();
		Iterator<ClassroomEntity> itr = classroomEntities.iterator();
		while(itr.hasNext()) {
			ClassroomModel model = new ClassroomModel();
			model.loadFromEntity(itr.next());
			classroomModels.add(model);
		}
		return classroomModels;
	}
	
	@Override
	public List<ClassroomModel> findByGrade(GradeModel gradeModel) {
		List<ClassroomModel> classroomModels = new ArrayList<ClassroomModel>();
		GradeEntity gradeEntity = new GradeEntity();
		gradeEntity.loadFromDTO(gradeModel);
		List<ClassroomEntity> classroomEntities = classroomDAO.findByGrade(gradeEntity);
		if (!classroomEntities.isEmpty()) {
			Iterator<ClassroomEntity> itr = classroomEntities.iterator();
			while (itr.hasNext()) {
				ClassroomModel model = new ClassroomModel();
				model.loadFromEntity(itr.next());
				classroomModels.add(model);
			}
			return classroomModels;
		}
		else {
			return null;
		}
	}

	@Override
	public Long delete(ClassroomModel model) {
		ClassroomEntity classroomEntity = new ClassroomEntity();
		classroomEntity.loadFromDTO(model);
		return classroomDAO.delete(classroomEntity);
	}
	
	private ClassroomModel getModifiedField(ClassroomModel model, String method) {
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
		case SystemConstant.INSERT_FILE:
			model.setModifiedDate(timestamp);
			model.setCreatedBy(authentication.getName());
			model.setCreatedDate(timestamp);
			break;
		}
		return model;
	}

	@Override
	public Long saveList(MultipartFile file) {
		try {
			InputStream fileInputStream = file.getInputStream();
			HSSFWorkbook wb = new HSSFWorkbook(fileInputStream);
			HSSFSheet sheet = wb.getSheetAt(0);
			for (Row row : sheet) // iteration over row using for each loop
			{
				if (row.getRowNum() != 0) {
					ClassroomModel model = new ClassroomModel();
					for (Cell cell : row) // iteration over cell using for each loop
					{
						/*
						 * _NONE(-1), NUMERIC(0), STRING(1), FORMULA(2), BLANK(3), BOOLEAN(4), ERROR(5);
						 */
						switch (cell.getColumnIndex()) {
						case 0:
							model.setName(cell.getStringCellValue());
							break;
						case 1:
							model.setCode(cell.getStringCellValue());
							break;
						}
					}
					model = getModifiedField(model, SystemConstant.INSERT_FILE);
					ClassroomEntity entity = new ClassroomEntity();
					entity.loadFromDTO(model);
					classroomDAO.save(entity);
				}
			}
			return 1L;
		} catch (IOException e) {
			System.out.println("save list classrooms");
			e.printStackTrace();
			return 0L;
		}
	}
}
