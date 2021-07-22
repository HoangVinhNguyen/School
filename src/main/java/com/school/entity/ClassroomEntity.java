package com.school.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.school.model.ClassroomModel;

@Entity
@Table(name="classroom")
public class ClassroomEntity extends BaseEntity{

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
	
	public void loadFromDTO(ClassroomModel model) {
		this.setId(model.getId());
		this.code = model.getCode();
		this.name = model.getName();
		this.setCreatedBy(model.getCreatedBy());
		this.setCreatedDate(model.getCreatedDate());
		this.setModifiedBy(model.getModifiedBy());
		this.setModifiedDate(model.getModifiedDate());
	}
	
}
