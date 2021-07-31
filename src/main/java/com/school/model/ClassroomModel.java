package com.school.model;

import java.util.ArrayList;
import java.util.List;

import com.school.entity.ClassroomEntity;

public class ClassroomModel extends BaseModel {

	private String name;
	private String code;
	private List<GradeModel> grade = new ArrayList<GradeModel>();

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

	public List<GradeModel> getGrade() {
		return grade;
	}

	public void setGrade(List<GradeModel> grade) {
		this.grade = grade;
	}

	public void loadFromEntity(ClassroomEntity entity) {
		if (entity != null) {
			this.setId(entity.getId());
			this.code = entity.getCode();
			this.name = entity.getName();
			entity.getGrade().forEach(g -> {
				GradeModel gradeModel = new GradeModel();
				gradeModel.loadFromEntity(g);
				this.grade.add(gradeModel);
			});
			this.setCreatedBy(entity.getCreatedBy());
			this.setCreatedDate(entity.getCreatedDate());
			this.setModifiedBy(entity.getModifiedBy());
			this.setModifiedDate(entity.getModifiedDate());
		}
	}
}
