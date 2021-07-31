package com.school.model;

import com.school.entity.ClassInEntity;

public class ClassInModel extends BaseModel {

	private String name;
	private String code;
	private GradeModel grade = new GradeModel();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public GradeModel getGrade() {
		return grade;
	}

	public void setGrade(GradeModel grade) {
		this.grade = grade;
	}

	public void loadFromEntity(ClassInEntity entity) {
		if (entity != null) {
			this.setId(entity.getId());
			this.code = entity.getCode();
			this.name = entity.getName();
			this.grade.loadFromEntity(entity.getGrade());
			this.setCreatedBy(entity.getCreatedBy());
			this.setCreatedDate(entity.getCreatedDate());
			this.setModifiedBy(entity.getModifiedBy());
			this.setModifiedDate(entity.getModifiedDate());
		 }
	}
}
