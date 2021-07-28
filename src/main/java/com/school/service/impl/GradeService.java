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

import com.school.DAO.IGradeDAO;
import com.school.constant.SystemConstant;
import com.school.entity.GradeEntity;
import com.school.model.GradeModel;
import com.school.service.IGradeService;

@Service
public class GradeService implements IGradeService {

	@Autowired
	private IGradeDAO gradeDAO;
	
	@Override
	public GradeModel findOne(long id) {
		GradeModel model = new GradeModel();
		model.loadFromEntity(gradeDAO.findOne(id));
		return model;
	}

	@Override
	public GradeModel findOneByCode(String code) {
		GradeModel model = new GradeModel();
		model.loadFromEntity(gradeDAO.findOneByCode(code));
		return model;
	}
	
	@Override
	public GradeModel findOneByName(String name) {
		GradeModel model = new GradeModel();
		model.loadFromEntity(gradeDAO.findOneByName(name));
		return model;
	}

	@Override
	public int getTotalItem() {
		return gradeDAO.getTotalItem();
	}

	@Override
	public Long save(GradeModel model, String method) {
		model = getModifiedField(model, method);
		GradeEntity gradeEntity = new GradeEntity();
		gradeEntity.loadFromDTO(model);
		Long result = gradeDAO.save(gradeEntity);
		return result;
	}

	@Override
	public List<GradeModel> findAll() {
		List<GradeModel> gradeModels = new ArrayList<GradeModel>();
		List<GradeEntity> gradeEntities = gradeDAO.findAll();
		Iterator<GradeEntity> itr = gradeEntities.iterator();
		while(itr.hasNext()) {
			GradeModel model = new GradeModel();
			model.loadFromEntity(itr.next());
			gradeModels.add(model);
		}
		return gradeModels;
	}

	@Override
	public Long delete(GradeModel model) {
		GradeEntity gradeEntity = new GradeEntity();
		gradeEntity.loadFromDTO(model);
		return gradeDAO.delete(gradeEntity);
	}
	
	private GradeModel getModifiedField(GradeModel model, String method) {
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
					GradeModel model = new GradeModel();
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
					GradeEntity entity = new GradeEntity();
					entity.loadFromDTO(model);
					gradeDAO.save(entity);
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
