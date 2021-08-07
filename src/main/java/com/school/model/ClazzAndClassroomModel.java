package com.school.model;

import com.school.entity.ClazzAndClassroomEntity;

public class ClazzAndClassroomModel extends BaseModel {

	private ClazzModel clazzModel = new ClazzModel();
	private ClassroomModel classroomModel = new ClassroomModel();

	public ClazzModel getClazzModel() {
		return clazzModel;
	}

	public void setClazzModel(ClazzModel clazzModel) {
		this.clazzModel = clazzModel;
	}

	public ClassroomModel getClassroomModel() {
		return classroomModel;
	}

	public void setClassroomModel(ClassroomModel classroomModel) {
		this.classroomModel = classroomModel;
	}

	public void loadFromEntity(ClazzAndClassroomEntity entity) {
		if (entity != null) {
			this.setId(entity.getId());
			this.clazzModel.loadFromEntity(entity.getClazz());
			this.classroomModel.loadFromEntity(entity.getClassroom());
			this.setCreatedBy(entity.getCreatedBy());
			this.setCreatedDate(entity.getCreatedDate());
			this.setModifiedBy(entity.getModifiedBy());
			this.setModifiedDate(entity.getModifiedDate());
		}
	}

}
