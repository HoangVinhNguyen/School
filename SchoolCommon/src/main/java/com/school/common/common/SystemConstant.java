package com.school.common.common;

public class SystemConstant {

	public static final String PHOTOS_OF_USERS_FOLDER = "user-photos";
	public static final String FORWARD_SLASH = "/";
	public static final String FORWARD_SLASH_2_START = "/**";
	public static final String FORWARD_SLASH_FILE = "file:/";
	public static final String PATH_IMG_DEFAULT = "/images/default-user.png";

	/*
	 * For paging.
	 */
	public final static String ASC = "asc";
	public final static String DESC = "desc";
	public final static String FIRST_NAME = "firstName";
	public final static String LAST_NAME = "lastName";
	public final static String CURRENT_PAGE = "currentPage";
	public final static String PAGE_NUM = "pageNum";
	public final static String TOTAL_PAGE = "totalPages";
	public final static String START_COUNT = "startCount";
	public final static String END_COUNT = "endCount";
	public final static String TOTAL_ITEM = "totalItems";
	public final static String SORT_FILED = "sortField";
	public final static String SORT_DIR = "sortDir";
	public final static String REVERSE_SORT_DIR = "reverseSortDir";
	public final static String KEYWORD = "keyword";
	public final static String LINK = "link";
	public final static String LINK_GOBACK = "linkGoBack";
	public final static String NAME = "name";
	public final static String CODE = "code";
	
	/*
	 * For Roles.
	 */
	public final static String TEACHER = "teacher";
	public final static String STUDENT = "student";
	

	/*
	 * For User class.
	 */
	public final static String PAGE_TILE = "pageTitle";
	
	public final static String TITLE_CREATE_NEW_USER = "Create New User";
	public final static String TITLE_EDIT_USER = "Edit User ID ";
	public final static String TITLE_USERS = "User - School Admin";
	public final static String TITLE_ACCOUNT_DETAILS = "Your Account Details";

	public final static String TITLE_LOGIN = "Login - School Admin";
	public final static String TITLE_ERROR = "Error - School Admin";
	public final static String TITLE_HOME = "Home - School Admin";
	
	public final static String TITLE_LEVEL = "Level - School Admin";
	public final static String TITLE_CREATE_NEW_LEVEL = "Create New Level";
	public final static String TITLE_EDIT_LEVEL = "Edit Level ID ";
	
	public final static String TITLE_GRADE = "Grade - School Admin";
	public final static String TITLE_CREATE_NEW_GRADE = "Create New Grade";
	public final static String TITLE_EDIT_GRADE = "Edit Grade ID ";
	
	public final static String TITLE_CLAZZ = "Class - School Admin";
	public final static String TITLE_CREATE_NEW_CLAZZ = "Create New Class";
	public final static String TITLE_EDIT_CLAZZ = "Edit Class ID ";
	public final static String TITLE_EDIT_CLAZZ_CLASSROOM = "Edit Classroom for Class ID ";
	public final static String TITLE_EDIT_CLAZZ_TEACHER = "Edit Teacher for Class ID ";
	
	public final static String TITLE_CLASSROOM = "Classroom - School Admin";
	public final static String TITLE_CREATE_NEW_CLASSROOM = "Create New Classroom";
	public final static String TITLE_EDIT_CLASSROOM = "Edit Classroom ID ";

	public final static String TITLE_COURSE = "Course - School Admin";
	public final static String TITLE_CREATE_NEW_COURSE = "Create New Course";
	public final static String TITLE_EDIT_COURSE = "Edit Course ID ";
	
	public final static String NOT_FOUND_ID = "Could not find any with ID ";
	public final static String ATTR_MESSAGE = "message";
	public final static String ATTR_CONTENT_USER_SAVE_SUCCESS = "The User have been saved successfully";
	public final static String ATTR_CONTENT_TEACHER_SAVE_SUCCESS = "The Teacher have been saved successfully";
	public final static String ATTR_CONTENT_LEVEL_SAVE_SUCCESS = "The Level have been saved successfully";
	public final static String ATTR_CONTENT_GRADE_SAVE_SUCCESS = "The Grade have been saved successfully";
	public final static String ATTR_CONTENT_CLAZZ_SAVE_SUCCESS = "The Class have been saved successfully";
	public final static String ATTR_CONTENT_CLASSROOM_SAVE_SUCCESS = "The Classroom have been saved successfully";
	public final static String ATTR_CONTENT_COURSE_SAVE_SUCCESS = "The Course have been saved successfully";
	public final static String ENABLED = "enabled";
	public final static String DISABLED = "disabled";
	public final static String JS_FILE = "jsFiles";
	public final static String CSS_FILE = "cssFiles";

	public final static String ATTR_CONTENT_USER_DEL_SUCCESS(Long id) {
		StringBuilder content = new StringBuilder();
		content.append("The User ID ").append(id).append(" has been deteled successfully");
		return  content.toString() ;
	}
	public final static String ATTR_CONTENT_USER_STATUS_SUCCESS(Long id, String status) {
		StringBuilder content = new StringBuilder();
		content.append("The User ID ").append(id).append(" has been ").append(status);
		return content.toString();
	}

	public final static String ATTR_CONTENT_TEACHER_STATUS_SUCCESS(Long id, String status) {
		StringBuilder content = new StringBuilder();
		content.append("The Teacher ID ").append(id).append(" has been ").append(status);
		return content.toString();
	}
	
	public final static String ATTR_CONTENT_LEVEL_DEL_SUCCESS(Long id) {
		StringBuilder content = new StringBuilder();
		content.append("The Level ID ").append(id).append(" has been deteled successfully");
		return  content.toString() ;
	}
	
	public final static String ATTR_CONTENT_GRADE_DEL_SUCCESS(Long id) {
		StringBuilder content = new StringBuilder();
		content.append("The Grade ID ").append(id).append(" has been deteled successfully");
		return  content.toString() ;
	}
	
	public final static String ATTR_CONTENT_CLAZZ_DEL_SUCCESS(Long id) {
		StringBuilder content = new StringBuilder();
		content.append("The Class ID ").append(id).append(" has been deteled successfully");
		return  content.toString() ;
	}
	
	public final static String ATTR_CONTENT_CLASSROOM_DEL_SUCCESS(Long id) {
		StringBuilder content = new StringBuilder();
		content.append("The Classroom ID ").append(id).append(" has been deteled successfully");
		return  content.toString() ;
	}
	
	public final static String ATTR_CONTENT_COURSE_DEL_SUCCESS(Long id) {
		StringBuilder content = new StringBuilder();
		content.append("The Course ID ").append(id).append(" has been deteled successfully");
		return  content.toString() ;
	}

	/*
	 * For export.
	 */
	public final static String TYPE_CSV = "text/csv";
	public final static String SUFFIX_CSV = ".csv";
	public final static String TYPE_EXCEL = "application/octet-stream";
	public final static String SUFFIX_EXCEL = ".xlsx";
	public final static String TYPE_PDF = "application/pdf";
	public final static String SUFFIX_PDF = ".pdf";

}
