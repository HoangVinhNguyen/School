package com.school.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.school.model.ClassroomModel;

@Entity
@Table(name="classroom")
public class ClassroomEntity extends BaseEntity{

	private String name;
	private String code;
	
	@ManyToMany(mappedBy = "classroom")
    private Set<ClassInEntity> classIn = new HashSet<>();
	
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
	
	public Set<ClassInEntity> getClassIn() {
		return classIn;
	}
	
	public void setClassIn(Set<ClassInEntity> classIn) {
		this.classIn = classIn;
	}
	
	public void loadFromDTO(ClassroomModel model) {
		if (model != null) {
			this.setId(model.getId());
			this.code = model.getCode();
			this.name = model.getName();
			
			this.setCreatedBy(model.getCreatedBy());
			this.setCreatedDate(model.getCreatedDate());
			this.setModifiedBy(model.getModifiedBy());
			this.setModifiedDate(model.getModifiedDate());
		 }
	}
	
}
