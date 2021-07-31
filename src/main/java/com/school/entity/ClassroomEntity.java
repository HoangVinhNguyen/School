package com.school.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.school.model.ClassroomModel;
import com.school.model.GradeModel;

@Entity
@Table(name="classroom")
public class ClassroomEntity extends BaseEntity{

	private String name;
	private String code;
	@OneToMany
	@JoinColumn(name="grade_id", nullable = true)
	private List<GradeEntity> grade;
	
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
	
	public List<GradeEntity> getGrade() {
		return grade;
	}
	public void setGrade(List<GradeEntity> grade) {
		this.grade = grade;
	}
	
	public void loadFromDTO(ClassroomModel model) {
		if (model != null) {
			this.setId(model.getId());
			this.code = model.getCode();
			this.name = model.getName();
			this.grade = new ArrayList<GradeEntity>();
			model.getGrade().forEach(g -> {
				GradeEntity gradeEntity = new GradeEntity();
				gradeEntity.loadFromDTO(g);
				this.grade.add(gradeEntity);
			});
			this.setCreatedBy(model.getCreatedBy());
			this.setCreatedDate(model.getCreatedDate());
			this.setModifiedBy(model.getModifiedBy());
			this.setModifiedDate(model.getModifiedDate());
		 }
	}
	
}
