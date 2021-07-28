package com.school.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.school.model.GradeModel;

@Entity
@Table(name="grade")
public class GradeEntity extends BaseEntity {

	private String name;
	private String code;
	
	@ManyToOne
	@JoinColumn(name="level_grade_id", nullable=false)
	private LevelGradeEntity levelGradeEntity;
	
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
	
	public LevelGradeEntity getLevelGradeEntity() {
		return levelGradeEntity;
	}
	
	public void setLevelGradeEntity(LevelGradeEntity levelGradeEntity) {
		this.levelGradeEntity = levelGradeEntity;
	}
	
	public void loadFromDTO(GradeModel model) {
		if (model != null) {
			this.setId(model.getId());
			this.code = model.getCode();
			this.name = model.getName();
			this.levelGradeEntity.loadFromDTO(model.getLevelGradeModel());
			this.setCreatedBy(model.getCreatedBy());
			this.setCreatedDate(model.getCreatedDate());
			this.setModifiedBy(model.getModifiedBy());
			this.setModifiedDate(model.getModifiedDate());
		 }
	}
}
