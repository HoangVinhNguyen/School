package com.school.model;

import java.util.HashSet;
import java.util.Set;

import com.school.entity.ClassInEntity;

public class ClassInModel extends BaseModel {

	private String name;
	private String code;
	private GradeModel grade = new GradeModel();
	private Set<ClassroomModel> listClassroom = new HashSet<ClassroomModel>();
	private Set<UserModel> user = new HashSet<UserModel>();

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

	public GradeModel getGrade() {
		return grade;
	}

	public void setGrade(GradeModel grade) {
		this.grade = grade;
	}

	public Set<UserModel> getUser() {
		return user;
	}

	public void setUser(Set<UserModel> user) {
		this.user = user;
	}

	public void loadFromEntity(ClassInEntity entity) {
		if (entity != null) {
			this.setId(entity.getId());
			this.code = entity.getCode();
			this.name = entity.getName();
			this.grade.loadFromEntity(entity.getGrade());
			this.setCreatedBy(entity.getCreatedBy());
			this.setCreatedDate(entity.getCreatedDate());
			this.setModifiedBy(entity.getModifiedBy());
			this.setModifiedDate(entity.getModifiedDate());
		}
	}

	public Set<ClassroomModel> getListClassroom() {
		return listClassroom;
	}

	public void setListClassroom(Set<ClassroomModel> listClassroom) {
		this.listClassroom = listClassroom;
	}
}
