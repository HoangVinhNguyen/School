package com.school.model;

import com.school.entity.ClassroomEntity;

public class ClassroomModel extends AbstractModel {

	private String name;
	private String code;
	
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
	
	public void loadFromEntity(ClassroomEntity entity) {
		this.code = entity.getCode();
		this.name = entity.getName();
		this.setCreatedBy(entity.getCreatedBy());
		this.setCreatedDate(entity.getCreatedDate());
		this.setModifiedBy(entity.getModifiedBy());
		this.setModifiedDate(entity.getModifiedDate());
	}
	
}
