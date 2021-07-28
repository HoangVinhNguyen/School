package com.school.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.school.model.ClassroomModel;

@Entity
@Table(name="classroom")
public class ClassroomEntity extends BaseEntity{

	private String name;
	private String code;
	@ManyToOne
	@JoinColumn(name="grade_id", nullable = false)
	private GradeEntity grade;
	
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
	
	public GradeEntity getGrade() {
		return grade;
	}
	public void setGrade(GradeEntity grade) {
		this.grade = grade;
	}
	public void loadFromDTO(ClassroomModel model) {
		if (model != null) {
			this.setId(model.getId());
			this.code = model.getCode();
			this.name = model.getName();
			this.grade.loadFromDTO(model.getGrade());
			this.setCreatedBy(model.getCreatedBy());
			this.setCreatedDate(model.getCreatedDate());
			this.setModifiedBy(model.getModifiedBy());
			this.setModifiedDate(model.getModifiedDate());
		 }
	}
	
}
