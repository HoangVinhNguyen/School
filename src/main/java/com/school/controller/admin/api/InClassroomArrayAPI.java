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
import com.school.model.ClassroomModel;
import com.school.model.InClassroomModel;
import com.school.model.UserModel;
import com.school.service.IClassroomService;
import com.school.service.IInClassroomService;
import com.school.service.IUserService;
import com.school.utils.SessionUtil;

@WebServlet(urlPatterns = { "/api-admin-inclassroom-array" })
public class InClassroomArrayAPI extends HttpServlet {

	private static final long serialVersionUID = -5823342027947949023L;

	@Inject
	private IInClassroomService inclassroomService;
	
	@Inject
	private IUserService userService;
	
	@Inject
	private IClassroomService classroomService;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		resp.setContentType("application/json");
		UserModel modelU = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
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
						InClassroomModel model = new InClassroomModel();
						for (Cell cell : row) // iteration over cell using for each loop
						{
							/*
							 * _NONE(-1), NUMERIC(0), STRING(1), FORMULA(2), BLANK(3), BOOLEAN(4), ERROR(5);
							 */
							switch (cell.getColumnIndex()) {
							case 0:
								Long idStudent = userService.findByEmail(cell.getStringCellValue());
								model.setStudentId(idStudent);
								break;
							case 1:
								ClassroomModel classroom = classroomService.findOneByCode(cell.getStringCellValue());
								model.setClassroomId(classroom.getId());
								break;
							}
						}
						model.setCreatedBy(modelU.getFullname());
						Timestamp t = new Timestamp(System.currentTimeMillis());
						model.setCreatedDate(t);
						model.setModifiedBy(modelU.getFullname());
						model.setModifiedDate(t);
						Long id = inclassroomService.save(model);
						mapper.writeValue(resp.getOutputStream(), id);
					}
				}
			}
		} catch (FileUploadException e) {
			throw new ServletException("Cannot parse multipart request.", e);
		}
	}
}
