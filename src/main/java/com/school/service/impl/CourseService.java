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

import com.school.DAO.ICourseDAO;
import com.school.constant.SystemConstant;
import com.school.entity.CourseEntity;
import com.school.model.CourseModel;
import com.school.service.ICourseService;

@Service
public class CourseService implements ICourseService {

	@Autowired
	private ICourseDAO courseDAO;
	
	@Override
	public CourseModel findOne(long id) {
		CourseModel model = new CourseModel();
		model.loadFromEntity(courseDAO.findOne(id));
		return model;
	}

	@Override
	public CourseModel findOneByCode(String code) {
		CourseModel model = new CourseModel();
		model.loadFromEntity(courseDAO.findOneByCode(code));
		return model;
	}
	
	@Override
	public CourseModel findOneByName(String name) {
		CourseModel model = new CourseModel();
		model.loadFromEntity(courseDAO.findOneByName(name));
		return model;
	}

	@Override
	public int getTotalItem() {
		return courseDAO.getTotalItem();
	}

	@Override
	public Long save(CourseModel model, String method) {
		model = getModifiedField(model, method);
		CourseEntity courseEntity = new CourseEntity();
		courseEntity.loadFromDTO(model);
		return courseDAO.save(courseEntity);
	}

	@Override
	public List<CourseModel> findAll() {
		List<CourseModel> courseModels = new ArrayList<CourseModel>();
		List<CourseEntity> courseEntities = courseDAO.findAll();
		Iterator<CourseEntity> itr = courseEntities.iterator();
		while(itr.hasNext()) {
			CourseModel model = new CourseModel();
			model.loadFromEntity(itr.next());
			courseModels.add(model);
		}
		return courseModels;
	}

	@Override
	public Long delete(CourseModel model) {
		CourseEntity courseEntity = new CourseEntity();
		courseEntity.loadFromDTO(model);
		return courseDAO.delete(courseEntity);
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
					CourseModel model = new CourseModel();
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
					CourseEntity entity = new CourseEntity();
					entity.loadFromDTO(model);
					if (checkNullEntity(entity)) {
						courseDAO.save(entity);
					}
					else {
						return 0L;
					}
				}
			}
			return 1L;
		} catch (IOException e) {
			System.out.println("save list course");
			e.printStackTrace();
			return 0L;
		}
	}
	
	private CourseModel getModifiedField(CourseModel model, String method) {
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
	
	private boolean checkNullEntity(CourseEntity entity) {
		if (entity.getCode() == null || entity.getName() == null || entity.getCreatedBy() == null) {
			return false;
		}
		return true;
	}
}
