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
		if (id > 0) {
			CourseModel model = new CourseModel();
			CourseEntity entity = courseDAO.findOne(id);
			if (entity != null) {
				model.loadFromEntity(entity);
				return model;
			}
		}
		return null;
	}

	@Override
	public CourseModel findOneByCode(String code) {
		if (code != null) {
			CourseModel model = new CourseModel();
			CourseEntity entity = courseDAO.findOneByCode(code);
			if (entity != null) {
				model.loadFromEntity(entity);
				return model;
			}
		}
		return null;
	}
	
	@Override
	public CourseModel findOneByName(String name) {
		if (name != null) {
			CourseModel model = new CourseModel();
			CourseEntity entity = courseDAO.findOneByName(name);
			if (entity != null) {
				model.loadFromEntity(entity);
				return model;
			}
		}
		return null;
	}

	@Override
	public int getTotalItem() {
		return courseDAO.getTotalItem();
	}

	@Override
	public Long save(CourseModel model, String method) {
		if (model != null && method != null) {
			model = getModifiedField(model, method);
			CourseEntity courseEntity = new CourseEntity();
			courseEntity.loadFromDTO(model);
			return courseDAO.save(courseEntity);
		}
		return SystemConstant.ERROR;
	}

	@Override
	public List<CourseModel> findAll() {
		List<CourseModel> courseModels = new ArrayList<CourseModel>();
		List<CourseEntity> courseEntities = courseDAO.findAll();
		Iterator<CourseEntity> itr = courseEntities.iterator();
		if (courseEntities != null && !courseEntities.isEmpty()) {
			while(itr.hasNext()) {
				CourseModel model = new CourseModel();
				model.loadFromEntity(itr.next());
				courseModels.add(model);
			}
			return courseModels;
		}
		return null;
	}

	@Override
	public Long delete(CourseModel model) {
		if (model != null) {
			model = getModifiedField(model, SystemConstant.MODIFY);
			CourseEntity courseEntity = new CourseEntity();
			courseEntity.loadFromDTO(model);
			return courseDAO.delete(courseEntity);
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
	
	@Override
	public Long downloadForm(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String fullPath = URLDecoder.decode(path, "UTF-8");
		String pathArr[] = fullPath.split("/WEB-INF/classes/");
		fullPath = pathArr[0];
		StringBuilder pathF = new StringBuilder();
		pathF.append(fullPath);
		pathF.append("/resources/form-file/");
		String dataDirectory2 = pathF.toString().replace("/", "\\\\");
		Path file = Paths.get(dataDirectory2.substring(2), "course-form.xlsx");
		if (Files.exists(file)) {
			// .xls - application/vnd.ms-excel.
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.addHeader("Content-Disposition", "attachment; filename=bieu-mau-mon-hoc.xlsx");
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
		List<CourseModel> list = findAll();
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
	        cell_title.setCellValue(SystemConstant.TITLE_COURSE);
	        cell_title.setCellStyle(style);
	        sheet.addMergedRegion(new CellRangeAddress(0,0,0,1));
	        
	        Row rowTitleSub = sheet.createRow(rowCount++);
	        Cell cellName = rowTitleSub.createCell(0);
	        cellName.setCellValue(SystemConstant.TITLE_NAME_COURSE);
	        cellName.setCellStyle(style2);
	        Cell cellCode = rowTitleSub.createCell(1);
	        cellCode.setCellValue(SystemConstant.TITLE_CODE_COURSE);
	        cellCode.setCellStyle(style2);
	        
			Iterator<CourseModel> itr = list.iterator();
			while(itr.hasNext()) {
				CourseModel model = itr.next();
				Row row = sheet.createRow(rowCount++);
				int checkColumn = 0;
	            for (Field field : model.getClass().getDeclaredFields()) {
	            	switch (field.getName()) {
	            	case SystemConstant.NAME_FIELD:
	            		Cell cell_name = row.createCell(0);
	            		cell_name.setCellValue(model.getName());
	            		cell_name.setCellStyle(style3);
	            		checkColumn++;
	            		break;
	            	case SystemConstant.CODE_FIELD:
	            		Cell cell_code = row.createCell(1);
	            		cell_code.setCellValue(model.getCode());
	            		cell_code.setCellStyle(style3);
	            		checkColumn++;
	            		break;
	            	}
	            	if (checkColumn == 2) {
	            		break;
	            	}
	            }
			}
			sheet.autoSizeColumn(0, true);
			sheet.autoSizeColumn(1);
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.addHeader("Content-Disposition", "attachment; filename=danh-sach-mon-hoc.xlsx");
			try {
				workbook.write(response.getOutputStream());
				response.getOutputStream().flush();
			} catch (IOException e) {
				System.out.println("report course file");
				e.printStackTrace();
				return SystemConstant.ERROR;
			}
			return SystemConstant.OK;
		}
		return SystemConstant.ERROR;
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
	
	private boolean validateField(CourseModel model, String method) {
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
						CourseModel model = new CourseModel();
						for (Cell cell : row)
						{
							/*
							 * _NONE(-1), NUMERIC(0), STRING(1), FORMULA(2), BLANK(3), BOOLEAN(4), ERROR(5);
							 */
							switch (cell.getColumnIndex()) {
							case 0: // Name.
								model.setName(cell.getStringCellValue());
								break;
							case 1: // Code.
								model.setCode(cell.getStringCellValue());
								break;
							}
						}
						model = getModifiedField(model, SystemConstant.INSERT_FILE);
						if (validateField(model, SystemConstant.INSERT_FILE)) {
							CourseEntity entity = new CourseEntity();
							entity.loadFromDTO(model);
							courseDAO.save(entity);
						}
					}
				}
				return 1L;
			} catch (IOException e) {
				System.out.println("save list classroom");
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
						CourseModel model = new CourseModel();
						for (Cell cell : row)
						{
							/*
							 * _NONE(-1), NUMERIC(0), STRING(1), FORMULA(2), BLANK(3), BOOLEAN(4), ERROR(5);
							 */
							switch (cell.getColumnIndex()) {
							case 0: // Name.
								model.setName(cell.getStringCellValue());
								break;
							case 1: // Code.
								model.setCode(cell.getStringCellValue());
								break;
							}
						}
						model = getModifiedField(model, SystemConstant.INSERT_FILE);
						if (validateField(model, SystemConstant.INSERT_FILE)) {
							CourseEntity entity = new CourseEntity();
							entity.loadFromDTO(model);
							courseDAO.save(entity);
						}
					}
				}
				return 1L;
			} catch (IOException e) {
				System.out.println("save list course");
				e.printStackTrace();
				return SystemConstant.ERROR;
			}
		}
		return SystemConstant.ERROR;
	}
}
