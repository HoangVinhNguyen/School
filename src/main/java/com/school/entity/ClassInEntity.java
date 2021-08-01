package com.school.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.school.model.ClassInModel;

@Entity
@Table(name="class")
public class ClassInEntity extends BaseEntity {

	private String name;
	private String code;
	
	@ManyToOne
	@JoinColumn(name="grade_id", nullable=false)
	private GradeEntity grade;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "class_classroom", 
        joinColumns = { @JoinColumn(name = "class_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "classroom_id") }
    )
    Set<ClassroomEntity> classroom = new HashSet<>();
	
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
	
	public Set<ClassroomEntity> getClassroom() {
		return classroom;
	}
	public void setClassroom(Set<ClassroomEntity> classroom) {
		this.classroom = classroom;
	}
	
	public void loadFromDTO(ClassInModel model) {
		if (model != null) {
			this.setId(model.getId());
			this.code = model.getCode();
			this.name = model.getName();
			this.grade = new GradeEntity();
			this.grade.loadFromDTO(model.getGrade());
			// classroom - class
			this.setCreatedBy(model.getCreatedBy());
			this.setCreatedDate(model.getCreatedDate());
			this.setModifiedBy(model.getModifiedBy());
			this.setModifiedDate(model.getModifiedDate());
		 }
	}
}
