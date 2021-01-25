package com.school.controller.admin.api;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
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
import com.school.model.UserModel;
import com.school.service.IUserService;
import com.school.utils.SessionUtil;

@WebServlet(urlPatterns = { "/api-admin-user-array" })
public class UserArrayAPI extends HttpServlet {

	private static final long serialVersionUID = 2811209177814833196L;

	@Inject
	private IUserService userService;

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
						UserModel model = new UserModel();
						for (Cell cell : row) // iteration over cell using for each loop
						{
							/*
							 * _NONE(-1), NUMERIC(0), STRING(1), FORMULA(2), BLANK(3), BOOLEAN(4), ERROR(5);
							 */
							switch (cell.getColumnIndex()) {
							case 0:
								model.setEmail(cell.getStringCellValue());
								break;
							case 1:
								model.setFullname(cell.getStringCellValue());
								break;
							case 2:
								Date tt = cell.getDateCellValue();
								Timestamp t = new Timestamp(tt.getYear(), tt.getMonth(), tt.getDay(), tt.getHours(),
										tt.getMinutes(), tt.getSeconds(), 0);
								model.setDob(t);
								break;
							case 3:
								model.setAddress(cell.getStringCellValue());
								break;
							case 4:
								String roleidS = String.valueOf(cell.getNumericCellValue());
								String[] roleidStr = roleidS.split("\\.");
								model.setRoledId(Long.parseLong(roleidStr[0]));
							}
						}
						model.setCreatedBy(modelU.getFullname());
						Timestamp t = new Timestamp(System.currentTimeMillis());
						model.setCreatedDate(t);
						model.setModifiedBy(modelU.getFullname());
						model.setModifiedDate(t);
						Long id = userService.save(model);
						mapper.writeValue(resp.getOutputStream(), id);
					}
				}
			}
		} catch (

		FileUploadException e) {
			throw new ServletException("Cannot parse multipart request.", e);
		}
//		ObjectMapper mapper = new ObjectMapper();
//		req.setCharacterEncoding("UTF-8");
//		resp.setContentType("application/json");
//		UserModel userModel =  HttpUtil.of(req.getReader()).toModel(UserModel.class);
//		UserModel model = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
//		if (model != null && model.getFullname() != null) {
//			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//			userModel.setCreatedBy(model.getFullname());
//			userModel.setCreatedDate(timestamp);
//			userModel.setModifiedBy(model.getFullname());
//			userModel.setModifiedDate(timestamp);
//			Long id = userService.save(userModel);
//			mapper.writeValue(resp.getOutputStream(), id);
//		}

	}
}
