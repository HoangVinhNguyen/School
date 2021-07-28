package com.school.model;

import com.school.entity.GradeEntity;

public class GradeModel extends BaseModel {

	private String name;
	private String code;
	private LevelGradeModel levelGradeModel = new LevelGradeModel();

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

	public LevelGradeModel getLevelGradeModel() {
		return levelGradeModel;
	}

	public void setLevelGradeModel(LevelGradeModel levelGradeModel) {
		this.levelGradeModel = levelGradeModel;
	}

	public void loadFromEntity(GradeEntity entity) {
		if (entity != null) {
			this.setId(entity.getId());
			this.code = entity.getCode();
			this.name = entity.getName();
			this.levelGradeModel.loadFromEntity(entity.getLevelGradeEntity());
			this.setCreatedBy(entity.getCreatedBy());
			this.setCreatedDate(entity.getCreatedDate());
			this.setModifiedBy(entity.getModifiedBy());
			this.setModifiedDate(entity.getModifiedDate());
		 }
	}
}
