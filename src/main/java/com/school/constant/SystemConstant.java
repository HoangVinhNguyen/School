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
	// GRADE
	public static final String TITLE_GRADE = "DANH SÁCH CẤP LỚP HỌC";
	public static final String TITLE_NAME = "Tên cấp lớp học";
	public static final String TITLE_CODE = "Mã cấp lớp học";
	public static final String TITLE_LEVEL_GRADE = "Tên bậc học";
	public static final String NAME_FIELD = "name";
	public static final String CODE_FIELD = "code";
	public static final String LEVEL_GRADE_FIELD = "levelGradeModel";
	// CLASS
	public static final String TITLE_CLASS = "DANH SÁCH LỚP HỌC";
	public static final String TITLE_NAME_CLASS = "Tên lớp học";
	public static final String TITLE_CODE_CLASS = "Mã lớp học";
	public static final String TITLE_GRADE_CLASS = "Tên khối lớp học";
	public static final String GRADE_FIELD = "grade";
	// CLASSROOM
	public static final String TITLE_CLASSROOM = "DANH SÁCH PHÒNG HỌC";
	public static final String TITLE_NAME_CLASSROOM = "Tên phòng học";
	public static final String TITLE_CODE_CLASSROOM = "Mã phòng học";
	// COURSE
	public static final String TITLE_COURSE = "DANH SÁCH MÔN HỌC";
	public static final String TITLE_NAME_COURSE = "Tên môn học";
	public static final String TITLE_CODE_COURSE = "Mã môn học";
}
