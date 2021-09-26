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
	public final static String TOTAL_PAGE = "totalPages";
	public final static String START_COUNT = "startCount";
	public final static String END_COUNT = "endCount";
	public final static String TOTAL_ITEM = "totalItems";
	public final static String SORT_FILED = "sortField";
	public final static String SORT_DIR = "sortDir";
	public final static String REVERSE_SORT_DIR = "reverseSortDir";
	public final static String KEYWORD = "keyword";
	public final static String LINK = "link";
	public final static String NAME = "name";

	/*
	 * For User class.
	 */
	public final static String PAGE_TILE = "pageTitle";
	public final static String TITLE_CREATE_NEW_USER = "Create New UserDto";
	public final static String TITLE_EDIT_USER = "Edit UserDto ID ";
	public final static String TITLE_LOGIN = "Login - School Admin";
	public final static String TITLE_ACCOUNT_DETAILS = "Your Account Details";
	public final static String TITLE_USERS = "User - School Admin";
	public final static String TITLE_HOME = "Home - School Admin";
	public final static String TITLE_LEVEL = "Level - School Admin";
	public final static String NOT_FOUND_ID = "Could not find any user with ID ";
	public final static String ATTR_MESSAGE = "message";
	public final static String ATTR_CONTENT_USER_SAVE_SUCCESS = "The user have been saved successfully";
	public final static String ENABLED = "enabled";
	public final static String DISABLED = "disabled";
	public final static String JS_FILE = "jsFiles";
	public final static String CSS_FILE = "cssFiles";

	public final static String ATTR_CONTENT_USER_EDIT_SUCCESS(Long id) {
		StringBuilder content = new StringBuilder();
		content.append("The user ID ").append(id).append(" has been deteled successfully");
		return  content.toString() ;
	}
	public final static String ATTR_CONTENT_USER_STATUS_SUCCESS(Long id, String status) {
		StringBuilder content = new StringBuilder();
		content.append("The user ID ").append(id).append(" has been ").append(status);
		return content.toString();
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
