package com.school.model;

import com.school.entity.UserAndClazzEntity;

public class UserAndClazzModel extends BaseModel {

	private ClazzModel clazzModel = new ClazzModel();
	private UserModel userModel = new UserModel();

	public ClazzModel getClazzModel() {
		return clazzModel;
	}

	public void setClazzModel(ClazzModel clazzModel) {
		this.clazzModel = clazzModel;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public void loadFromEntity(UserAndClazzEntity entity) {
		if (entity != null) {
			this.setId(entity.getId());
			this.userModel.loadFromEntity(entity.getUser());
			this.clazzModel.loadFromEntity(entity.getClazz());
			this.setCreatedBy(entity.getCreatedBy());
			this.setCreatedDate(entity.getCreatedDate());
			this.setModifiedBy(entity.getModifiedBy());
			this.setModifiedDate(entity.getModifiedDate());
		}
	}
}
