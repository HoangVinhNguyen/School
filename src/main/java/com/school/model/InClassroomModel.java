package com.school.model;

import com.school.entity.InClassroomEntity;
import com.school.entity.RoleEntity;

public class InClassroomModel extends BaseModel {

	private UserModel userModel = new UserModel();
	private ClassroomModel classroomModel = new ClassroomModel();

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public ClassroomModel getClassroomModel() {
		return classroomModel;
	}

	public void setClassroomModel(ClassroomModel classroomModel) {
		this.classroomModel = classroomModel;
	}

	public void loadFromEntity(InClassroomEntity entity) {
		if (entity != null) {
			this.setId(entity.getId());
			this.userModel.loadFromEntity(entity.getUser());
			this.classroomModel.loadFromEntity(entity.getClassroom());
			this.setCreatedBy(entity.getCreatedBy());
			this.setCreatedDate(entity.getCreatedDate());
			this.setModifiedBy(entity.getModifiedBy());
			this.setModifiedDate(entity.getModifiedDate());
		}
	}
}
