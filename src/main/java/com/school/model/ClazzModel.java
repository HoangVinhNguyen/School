package com.school.model;

import java.util.HashSet;
import java.util.Set;

import com.school.entity.ClazzEntity;

public class ClazzModel extends BaseModel {

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

	public void loadFromEntity(ClazzEntity entity) {
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