package com.school.model;

import com.school.entity.InClassroomEntity;
import com.school.entity.RoleEntity;

public class InClassroomModel extends BaseModel {

	private UserModel studentModel;
	private ClassroomModel classroomModel;

	public UserModel getStudentModel() {
		return studentModel;
	}

	public void setStudentModel(UserModel studentModel) {
		this.studentModel = studentModel;
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
			this.studentModel.loadFromEntity(entity.getStudent());
			this.classroomModel.loadFromEntity(entity.getClassroom());
			this.setCreatedBy(entity.getCreatedBy());
			this.setCreatedDate(entity.getCreatedDate());
			this.setModifiedBy(entity.getModifiedBy());
			this.setModifiedDate(entity.getModifiedDate());
		}
	}
}
