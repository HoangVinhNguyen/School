package com.school.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.school.DAO.ILevelGradeDAO;
import com.school.constant.SystemConstant;
import com.school.entity.LevelGradeEntity;
import com.school.model.GradeModel;
import com.school.model.LevelGradeModel;
import com.school.service.IGradeService;
import com.school.service.ILevelGradeService;
import com.school.utils.GetCellValueMultiType;

@Service
public class LevelGradeService implements ILevelGradeService {

	@Autowired
	private ILevelGradeDAO levelGradeDAO;
	
	@Autowired
	private IGradeService gradeService;
	
	@Override
	public LevelGradeModel findOne(long id) {
		LevelGradeModel model = new LevelGradeModel();
		model.loadFromEntity(levelGradeDAO.findOne(id));
		return model;
	}

	@Override
	public LevelGradeModel findOneByCode(String code) {
		LevelGradeModel model = new LevelGradeModel();
		model.loadFromEntity(levelGradeDAO.findOneByCode(code));
		return model;
	}
	
	@Override
	public LevelGradeModel findOneByName(String name) {
		LevelGradeModel model = new LevelGradeModel();
		model.loadFromEntity(levelGradeDAO.findOneByName(name));
		return model;
	}

	@Override
	public int getTotalItem() {
		return levelGradeDAO.getTotalItem();
	}

	@Override
	public Long save(LevelGradeModel model, String method) {
		model = getModifiedField(model, method);
		if (validateField(model, method)) {
			LevelGradeEntity gradeEntity = new LevelGradeEntity();
			gradeEntity.loadFromDTO(model);
			Long result = levelGradeDAO.save(gradeEntity);
			return result;
		}
		return SystemConstant.ERROR;
	}

	@Override
	public List<LevelGradeModel> findAll() {
		List<LevelGradeModel> gradeModels = new ArrayList<LevelGradeModel>();
		List<LevelGradeEntity> gradeEntities = levelGradeDAO.findAll();
		Iterator<LevelGradeEntity> itr = gradeEntities.iterator();
		while(itr.hasNext()) {
			LevelGradeModel model = new LevelGradeModel();
			model.loadFromEntity(itr.next());
			gradeModels.add(model);
		}
		return gradeModels;
	}

	@Override
	public Long delete(LevelGradeModel model) {
		List<GradeModel> modelResult = new ArrayList<>();
		modelResult = gradeService.findAllByLevelGreadId(model);
		if (modelResult == null) {
			LevelGradeEntity gradeEntity = new LevelGradeEntity();
			gradeEntity.loadFromDTO(model);
			return levelGradeDAO.delete(gradeEntity);
		}
		return SystemConstant.ERROR;
	}

	@Override
	public Long saveList(MultipartFile file) {
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		try {
			InputStream fileInputStream = file.getInputStream();
			return saveFileWithExtensionType(fileInputStream, extension);
		} catch (IOException e) {
			e.printStackTrace();
			return SystemConstant.ERROR;
		}
	}
	
	private LevelGradeModel getModifiedField(LevelGradeModel model, String method) {
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
	
	private boolean validateField(LevelGradeModel model, String method) {
		switch (method) {
		case SystemConstant.INSERT:
			if (model.getCode() == null || model.getName()  == null || model.getCreatedBy() == null 
					|| model.getCode() == "" || model.getName() == "" || model.getCreatedBy() == "")  {
				return false;
			}
			break;
		case SystemConstant.MODIFY:
			if (model.getCode() == null || model.getName()  == null || model.getModifiedBy() == null 
					|| model.getCode() == "" || model.getName() == "" || model.getModifiedBy() == "")  {
				return false;
			}
			break;
		case SystemConstant.INSERT_FILE:
			if (model.getCode() == null || model.getName()  == null || model.getCreatedBy() == null 
					|| model.getCode() == "" || model.getName() == "" || model.getCreatedBy() == "")  {
				return false;
			}
			break;
		}
		return true;
	}
	
	private Long saveFileWithExtensionType(InputStream fileInputStream, String extension) {
		switch (extension) {
		case SystemConstant.XLS:
			try {
				HSSFWorkbook wb = new HSSFWorkbook(fileInputStream);
				HSSFSheet sheet = wb.getSheetAt(0);
				for (Row row : sheet)
				{
					if (row.getRowNum() > 1) {
						LevelGradeModel model = new LevelGradeModel();
						for (Cell cell : row) // iteration over cell using for each loop
						{
							/*
							 * _NONE(-1), NUMERIC(0), STRING(1), FORMULA(2), BLANK(3), BOOLEAN(4), ERROR(5);
							 */
							switch (cell.getColumnIndex()) {
							case 0:
								model.setName(GetCellValueMultiType.CellStringValueOf(cell));
								break;
							case 1:
								model.setCode(GetCellValueMultiType.CellStringValueOf(cell));
								break;
							}
						}
						model = getModifiedField(model, SystemConstant.INSERT_FILE);
						if (validateField(model, SystemConstant.INSERT_FILE)) {
							LevelGradeEntity entity = new LevelGradeEntity();
							entity.loadFromDTO(model);
							levelGradeDAO.save(entity);
						}
					}
				}
				return 1L;
			} catch (IOException e) {
				System.out.println("save list grade");
				e.printStackTrace();
				return SystemConstant.ERROR;
			}
		case SystemConstant.XLSX:
			try {
				XSSFWorkbook wb = new XSSFWorkbook(fileInputStream);
				XSSFSheet sheet = wb.getSheetAt(0);
				for (Row row : sheet)
				{
					if (row.getRowNum() > 1) {
						LevelGradeModel model = new LevelGradeModel();
						for (Cell cell : row) // iteration over cell using for each loop
						{
							/*
							 * _NONE(-1), NUMERIC(0), STRING(1), FORMULA(2), BLANK(3), BOOLEAN(4), ERROR(5);
							 */
							switch (cell.getColumnIndex()) {
							case 0:
								model.setName(GetCellValueMultiType.CellStringValueOf(cell));
								break;
							case 1:
								model.setCode(GetCellValueMultiType.CellStringValueOf(cell));
								break;
							}
						}
						model = getModifiedField(model, SystemConstant.INSERT_FILE);
						if (validateField(model, SystemConstant.INSERT_FILE)) {
							LevelGradeEntity entity = new LevelGradeEntity();
							entity.loadFromDTO(model);
							levelGradeDAO.save(entity);
						}
					}
				}
				return 1L;
			} catch (IOException e) {
				System.out.println("save list grade");
				e.printStackTrace();
				return SystemConstant.ERROR;
			}
		}
		return SystemConstant.ERROR;
	}
}
