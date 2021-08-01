package com.school.model;

import java.util.HashSet;
import java.util.Set;

import com.school.entity.ClassroomEntity;

public class ClassroomModel extends BaseModel {

	private String name;
	private String code;
	private Set<ClassInModel> classIn = new HashSet();

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

	public Set<ClassInModel> getClassIn() {
		return classIn;
	}

	public void setClassIn(Set<ClassInModel> classIn) {
		this.classIn = classIn;
	}
	
	public void loadFromEntity(ClassroomEntity entity) {
		if (entity != null) {
			this.setId(entity.getId());
			this.code = entity.getCode();
			this.name = entity.getName();
			this.setCreatedBy(entity.getCreatedBy());
			this.setCreatedDate(entity.getCreatedDate());
			this.setModifiedBy(entity.getModifiedBy());
			this.setModifiedDate(entity.getModifiedDate());
		}
	}
}
