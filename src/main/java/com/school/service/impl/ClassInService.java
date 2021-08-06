package com.school.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.school.DAO.IClassInDAO;
import com.school.constant.SystemConstant;
import com.school.entity.ClassInEntity;
import com.school.entity.GradeEntity;
import com.school.model.ClassInModel;
import com.school.model.GradeModel;
import com.school.service.IClassInService;
import com.school.service.IGradeService;
import com.school.utils.GetCellValueMultiType;

@Service
public class ClassInService implements IClassInService {

	@Autowired
	private IClassInDAO classInDAO;
	
	@Autowired
	private IGradeService gradeService;
	
	@Override
	public ClassInModel findOne(long id) {
		ClassInModel model = new ClassInModel();
		model.loadFromEntity(classInDAO.findOne(id));
		return model;
	}

	@Override
	public ClassInModel findOneByCode(String code) {
		ClassInModel model = new ClassInModel();
		model.loadFromEntity(classInDAO.findOneByCode(code));
		return model;
	}
	
	@Override
	public ClassInModel findOneByName(String name) {
		ClassInModel model = new ClassInModel();
		model.loadFromEntity(classInDAO.findOneByName(name));
		return model;
	}
	
	@Override
	public List<ClassInModel> findAllByLevelGreadId(GradeModel gradeModel) {
		GradeEntity entity = new GradeEntity();
		List<ClassInEntity> entityResult = new ArrayList<>();
		List<ClassInModel> models = new ArrayList<>();
		
		entity.loadFromDTO(gradeModel);
		entityResult = classInDAO.findAllByGreadId(entity);
		if (entityResult != null) {
			for (ClassInEntity item : entityResult) {
				ClassInModel model = new ClassInModel();
				model.loadFromEntity(item);
				models.add(model);
			}
			return models;
		}
		return null;
	}

	@Override
	public int getTotalItem() {
		return classInDAO.getTotalItem();
	}

	@Override
	public Long save(ClassInModel model, String method) {
		GradeModel gradeModel = gradeService.findOne(model.getGrade().getId());
		if (gradeModel != null) {
			model.setGrade(gradeModel);
			model = getModifiedField(model, method);
			if (validateField(model, method)) {
				ClassInEntity gradeEntity = new ClassInEntity();
				gradeEntity.loadFromDTO(model);
				Long result = classInDAO.save(gradeEntity);
				return result;
			}
		}
		return SystemConstant.ERROR;
	}
	
	@Override
	public Long saveClassroom(ClassInModel model, String method) {
		if (model != null) {
			model = getModifiedField(model, method);
			ClassInEntity classInEntity = new ClassInEntity();
			classInEntity.loadFromDTO(model);
			return classInDAO.saveClassroom(classInEntity);
		}
		return SystemConstant.ERROR;
	}

	@Override
	public List<ClassInModel> findAll() {
		List<ClassInModel> gradeModels = new ArrayList<ClassInModel>();
		List<ClassInEntity> gradeEntities = classInDAO.findAll();
		Iterator<ClassInEntity> itr = gradeEntities.iterator();
		while(itr.hasNext()) {
			ClassInModel model = new ClassInModel();
			model.loadFromEntity(itr.next());
			gradeModels.add(model);
		}
		return gradeModels;
	}

	@Override
	public Long delete(ClassInModel model) {
		ClassInEntity gradeEntity = new ClassInEntity();
		gradeEntity.loadFromDTO(model);
		return classInDAO.delete(gradeEntity);
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
	
	@Override
	public Long downloadForm(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String fullPath = URLDecoder.decode(path, "UTF-8");
		String pathArr[] = fullPath.split("/WEB-INF/classes/");
		fullPath = pathArr[0];
		StringBuilder pathF = new StringBuilder();
		pathF.append(fullPath);
		pathF.append("/resources/form-file/");
		String dataDirectory2 = pathF.toString().replace("/", "\\\\");
		Path file = Paths.get(dataDirectory2.substring(2), "class-form.xlsx");
		if (Files.exists(file)) {
			// .xls - application/vnd.ms-excel.
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.addHeader("Content-Disposition", "attachment; filename=bieu-mau-lop-hoc.xlsx");
			try {
				Files.copy(file, response.getOutputStream());
				response.getOutputStream().flush();
			}
			catch (IOException ex) {
				ex.printStackTrace();
				return SystemConstant.ERROR;
			}
		}
		return SystemConstant.OK;
	}
	
	@Override
	public Long getReport(HttpServletRequest request, HttpServletResponse response) {
		List<ClassInModel> list = findAll();
		if (list != null) {
			XSSFWorkbook workbook = new XSSFWorkbook();
			POIXMLProperties xmlProps = workbook.getProperties();    
			POIXMLProperties.CoreProperties coreProps =  xmlProps.getCoreProperties();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			coreProps.setCreator(authentication.getName()==null?SystemConstant.ANONYMOUS:authentication.getName());
	        XSSFSheet sheet = workbook.createSheet();
			
	        int rowCount = 0;
	        
	        Font fontTitle = workbook.createFont();
	        fontTitle.setFontHeightInPoints(Short.valueOf("18"));
	        fontTitle.setFontName("Calibri");
	        fontTitle.setBold(true);
	        
	        CellStyle style = workbook.createCellStyle();
	        style.setFont(fontTitle);
	        style.setBorderBottom(BorderStyle.THIN);
	        style.setBorderLeft(BorderStyle.THIN);
	        style.setBorderRight(BorderStyle.THIN);
	        style.setBorderTop(BorderStyle.THIN);
	        style.setAlignment(HorizontalAlignment.CENTER);
	        style.setVerticalAlignment(VerticalAlignment.CENTER);
	        Font fontTitleSub = workbook.createFont();
	        fontTitleSub.setFontName("Calibri");
	        fontTitleSub.setBold(true);
	        
	        CellStyle style2 = workbook.createCellStyle();
	        style2.setFont(fontTitleSub);
	        style2.setAlignment(HorizontalAlignment.CENTER);
	        style2.setVerticalAlignment(VerticalAlignment.CENTER);
	        style2.setBorderBottom(BorderStyle.THIN);
	        style2.setBorderLeft(BorderStyle.THIN);
	        style2.setBorderRight(BorderStyle.THIN);
	        style2.setBorderTop(BorderStyle.THIN);
	        
	        CellStyle style3 = workbook.createCellStyle();
	        style3.setAlignment(HorizontalAlignment.LEFT);
	        style3.setVerticalAlignment(VerticalAlignment.CENTER);
	        style3.setBorderBottom(BorderStyle.THIN);
	        style3.setBorderLeft(BorderStyle.THIN);
	        style3.setBorderRight(BorderStyle.THIN);
	        style3.setBorderTop(BorderStyle.THIN);
	        
	        Row rowTitle = sheet.createRow(rowCount++);
	        Cell cell_title = rowTitle.createCell(0);
	        cell_title.setCellValue(SystemConstant.TITLE_CLASS);
	        cell_title.setCellStyle(style);
	        sheet.addMergedRegion(new CellRangeAddress(0,0,0,2));
	        
	        Row rowTitleSub = sheet.createRow(rowCount++);
	        Cell cellName = rowTitleSub.createCell(0);
	        cellName.setCellValue(SystemConstant.TITLE_NAME_CLASS);
	        cellName.setCellStyle(style2);
	        Cell cellCode = rowTitleSub.createCell(1);
	        cellCode.setCellValue(SystemConstant.TITLE_CODE_CLASS);
	        cellCode.setCellStyle(style2);
	        Cell cellLevelGrade = rowTitleSub.createCell(2);
	        cellLevelGrade.setCellValue(SystemConstant.TITLE_GRADE_CLASS);
	        cellLevelGrade.setCellStyle(style2);
	        
			Iterator<ClassInModel> itr = list.iterator();
			while(itr.hasNext()) {
				ClassInModel model = itr.next();
				Row row = sheet.createRow(rowCount++);
				int checkColumn = 0;
	            for (Field field : model.getClass().getDeclaredFields()) {
	            	switch (field.getName()) {
	            	case SystemConstant.NAME_FIELD:
	            		Cell cell_name = row.createCell(0);
	            		cell_name.setCellValue(model.getName());
	            		cell_name.setCellStyle(style3);
	            		sheet.autoSizeColumn(checkColumn);
	            		checkColumn++;
	            		break;
	            	case SystemConstant.CODE_FIELD:
	            		Cell cell_code = row.createCell(1);
	            		cell_code.setCellValue(model.getCode());
	            		cell_code.setCellStyle(style3);
	            		sheet.autoSizeColumn(checkColumn);
	            		checkColumn++;
	            		break;
	            	case SystemConstant.GRADE_FIELD:
	            		Cell cell_level_grade = row.createCell(2);
	            		cell_level_grade.setCellValue(model.getGrade().getName());
	            		cell_level_grade.setCellStyle(style3);
	            		sheet.autoSizeColumn(checkColumn);
	            		checkColumn++;
	            		break;
	            	}
	            	if (checkColumn == 3) {
	            		break;
	            	}
	            }
			}
			
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.addHeader("Content-Disposition", "attachment; filename=danh-sach-lop-hoc.xlsx");
			try {
				
				workbook.write(response.getOutputStream());
				response.getOutputStream().flush();
			} catch (IOException e) {
				System.out.println("report class file");
				e.printStackTrace();
				return SystemConstant.ERROR;
			}
			return SystemConstant.OK;
		}
		return SystemConstant.ERROR;
	}
	
	private ClassInModel getModifiedField(ClassInModel model, String method) {
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
	
	private boolean validateField(ClassInModel model, String method) {
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
						ClassInModel model = new ClassInModel();
						for (Cell cell : row)
						{
							/*
							 * _NONE(-1), NUMERIC(0), STRING(1), FORMULA(2), BLANK(3), BOOLEAN(4), ERROR(5);
							 */
							switch (cell.getColumnIndex()) {
							case 0: // Name.
								model.setName(GetCellValueMultiType.CellStringValueOf(cell));
								break;
							case 1: // Code.
								model.setCode(GetCellValueMultiType.CellStringValueOf(cell));
								break;
							case 2: // level grade.
								GradeModel gradeModel = new GradeModel();
								gradeModel = gradeService.findOneByName(GetCellValueMultiType.CellStringValueOf(cell));
								if (gradeModel == null)
								{
									return SystemConstant.ERROR;
								}
								model.setGrade(gradeModel);
								break;
							}
						}
						model = getModifiedField(model, SystemConstant.INSERT_FILE);
						if (validateField(model, SystemConstant.INSERT_FILE)) {
							ClassInEntity entity = new ClassInEntity();
							entity.loadFromDTO(model);
							classInDAO.save(entity);
						}
					}
				}
				return 1L;
			} catch (IOException e) {
				System.out.println("save list class");
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
						ClassInModel model = new ClassInModel();
						for (Cell cell : row)
						{
							/*
							 * _NONE(-1), NUMERIC(0), STRING(1), FORMULA(2), BLANK(3), BOOLEAN(4), ERROR(5);
							 */
							switch (cell.getColumnIndex()) {
							case 0: // Name.
								model.setName(GetCellValueMultiType.CellStringValueOf(cell));
								break;
							case 1: // Code.
								model.setCode(GetCellValueMultiType.CellStringValueOf(cell));
								break;
							case 2: // level grade.
								GradeModel gradeModel = new GradeModel();
								gradeModel = gradeService.findOneByName(GetCellValueMultiType.CellStringValueOf(cell));
								if (gradeModel == null)
								{
									return SystemConstant.ERROR;
								}
								model.setGrade(gradeModel);
								break;
							}
						}
						model = getModifiedField(model, SystemConstant.INSERT_FILE);
						if (validateField(model, SystemConstant.INSERT_FILE)) {
							ClassInEntity entity = new ClassInEntity();
							entity.loadFromDTO(model);
							classInDAO.save(entity);
						}
					}
				}
				return 1L;
			} catch (IOException e) {
				System.out.println("save list class");
				e.printStackTrace();
				return SystemConstant.ERROR;
			}
		}
		return SystemConstant.ERROR;
	}
	
}
