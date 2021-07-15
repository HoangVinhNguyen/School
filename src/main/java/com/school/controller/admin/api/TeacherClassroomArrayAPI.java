package com.school.controller.admin.api;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.entity.ClassroomEntity;
import com.school.entity.CourseEntity;
import com.school.entity.TeacherClassroomEntity;
import com.school.entity.UserEntity;
import com.school.service.IClassroomService;
import com.school.service.ICourseService;
import com.school.service.ITeacherClassroomService;
import com.school.service.IUserService;
import com.school.utils.SessionUtil;

@WebServlet(urlPatterns = {"/api-admin-teacherclassroom-array"})
public class TeacherClassroomArrayAPI extends HttpServlet {

	private static final long serialVersionUID = -2125568500507556672L;
	
	@Inject
	private ICourseService courseService;
	
	@Inject
	private IUserService userService;
	
	@Inject
	private IClassroomService classroomService;
	
	@Inject
	private ITeacherClassroomService teacherService;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		resp.setContentType("application/json");
		UserEntity modelU = (UserEntity) SessionUtil.getInstance().getValue(req, "USERMODEL");
		try {
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
			for (FileItem item : items) {
				if (item.isFormField()) {
					// Process regular form field (input type="text|radio|checkbox|etc", select,
					// etc).
					String fieldName = item.getFieldName();
					String fieldValue = item.getString();
				} else {
					// Process form file field (input type="file").
					String fieldName = item.getFieldName();
					String fileName = FilenameUtils.getName(item.getName());
					InputStream fileContent = item.getInputStream();
					System.out.println(fileContent);
					HSSFWorkbook wb = new HSSFWorkbook(fileContent);
					HSSFSheet sheet = wb.getSheetAt(0);
					// evaluating cell type
					FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
					for (Row row : sheet) // iteration over row using for each loop
					{
						TeacherClassroomEntity model = new TeacherClassroomEntity();
						for (Cell cell : row) // iteration over cell using for each loop
						{
							/*
							 * _NONE(-1), NUMERIC(0), STRING(1), FORMULA(2), BLANK(3), BOOLEAN(4), ERROR(5);
							 */
							switch (cell.getColumnIndex()) {
							case 0:
								Long idTeacher = userService.findByEmail(cell.getStringCellValue());
								model.setTeacherId(idTeacher);
								break;
							case 1:
								Long idStudent = userService.findByEmail(cell.getStringCellValue());
								model.setStudentId(idStudent);
								break;
							case 2:
								ClassroomEntity classroom = classroomService.findOneByCode(cell.getStringCellValue());
								model.setClassroomId(classroom.getId());
								break;
							case 3:
								CourseEntity course = courseService.findOneByName(cell.getStringCellValue());
								model.setCourseId(course.getId());
								break;
							}
						}
						model.setCreatedBy(modelU.getFullname());
						Timestamp t = new Timestamp(System.currentTimeMillis());
						model.setCreatedDate(t);
						model.setModifiedBy(modelU.getFullname());
						model.setModifiedDate(t);
						Long id = teacherService.save(model);
						mapper.writeValue(resp.getOutputStream(), id);
					}
				}
			}
		} catch (FileUploadException e) {
			throw new ServletException("Cannot parse multipart request.", e);
		}
	}

}
