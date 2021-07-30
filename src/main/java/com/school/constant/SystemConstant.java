package com.school.constant;

import java.nio.charset.Charset;

public class SystemConstant {

	public static final String MODEL = "model";
	public static final String ADMIN = "ADMIN";
	public static final String STUDENT = "STUDENT";
	public static final String TEACHER = "TEACHER";
	public static final String PRINCIPAL = "PRINCIPAL";
	
	public static final String INSERT = "insert";
	public static final String INSERT_FILE = "insert_file";
	public static final String MODIFY = "modify";
	
	public static final String DOMAINNAME = "@school.com";
	
	public static final String ROLE_TEACHER = TEACHER;
	public static final String ROLE_STUDENT = STUDENT;
	
	public static final String ANONYMOUS = "anonymous";
	
	// SQL logic
	public static final Long DUPLICATE = -1L;
	public static final String DUPLICATE_RES = "Dữ liệu đã tồn tại";
	public static final Long ERROR = 0L;
	public static final String ERROR_RES = "Dữ liệu lỗi";
	public static final String OK_RES = "Lưu trữ thành công";
	public static final String DELETE_OK_RES = "Xóa thành công";
	public static final String DELETE_FAILED_RES = "Xóa không thành công";
	public static final Long OK = 1L;
	
	// UTF-8
	public static final Charset CHARSET_UTF_8 = Charset.forName("UTF-8");
	
	// File
	public static final String XLS = "xls";
	public static final String XLSX = "xlsx";
	
	// report form
	public static final String TITLE_GRADE = "DANH SÁCH CÁC CẤP LỚP HỌC";
	public static final String TITLE_NAME = "Tên cấp lớp";
	public static final String TITLE_CODE = "Mã cấp lớp";
	public static final String TITLE_LEVEL_GRADE = "Tên bậc học";
	public static final String NAME_FIELD = "name";
	public static final String CODE_FIELD = "code";
	public static final String LEVEL_GRADE_FIELD = "levelGradeModel";
}
