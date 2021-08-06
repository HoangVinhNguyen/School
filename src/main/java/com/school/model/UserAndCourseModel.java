package com.school.model;

import com.school.constant.SystemConstant;
import com.school.entity.UserAndCourseEntity;

public class UserAndCourseModel extends BaseModel {

	private UserModel userModel = new UserModel();
	private CourseModel courseModel = new CourseModel();
	private CourseModel oldCourse = new CourseModel();
	
	public CourseModel getCourseModel() {
		return courseModel;
	}

	public void setCourseModel(CourseModel courseModel) {
		this.courseModel = courseModel;
	}
	
	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public CourseModel getOldCourse() {
		return oldCourse;
	}

	public void setOldCourse(CourseModel oldCourse) {
		this.oldCourse = oldCourse;
	}
	
	public void loadFromEntity(UserAndCourseEntity entity) {
		if (entity != null) {
			this.userModel.loadFromEntity(entity.getUser());
			this.courseModel.loadFromEntity(entity.getCourse());
			this.oldCourse.loadFromEntity(entity.getOldCourse());
			this.setCreatedBy(entity.getCreatedBy());
			this.setCreatedDate(entity.getCreatedDate());
			this.setModifiedBy(entity.getModifiedBy());
			this.setModifiedDate(entity.getModifiedDate());
		}
	}
}
