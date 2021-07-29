package com.school.model;

import com.school.entity.InCourseEntity;

public class InCourseModel extends BaseModel {

	private UserModel userModel = new UserModel();
	private CourseModel courseModel = new CourseModel();

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

	public void loadFromEntity(InCourseEntity entity) {
		if (entity != null) {
			this.setId(entity.getId());
			this.userModel.loadFromEntity(entity.getUser());
			this.courseModel.loadFromEntity(entity.getCourse());
			this.setCreatedBy(entity.getCreatedBy());
			this.setCreatedDate(entity.getCreatedDate());
			this.setModifiedBy(entity.getModifiedBy());
			this.setModifiedDate(entity.getModifiedDate());
		}
	}
}
