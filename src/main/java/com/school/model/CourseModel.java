package com.school.model;

import java.util.HashSet;
import java.util.Set;

import com.school.entity.CourseEntity;
import com.school.entity.UserEntity;

public class CourseModel extends BaseModel {

	private String name;
	private String code;
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

	public Set<UserModel> getUser() {
		return user;
	}

	public void setUser(Set<UserModel> user) {
		this.user = user;
	}

	public void loadFromEntity(CourseEntity entity) {
		if (entity != null) {
			this.setId(entity.getId());
			this.code = entity.getCode();
			this.name = entity.getName();
			this.setCreatedBy(entity.getCreatedBy());
			this.setCreatedDate(entity.getCreatedDate());
			this.setModifiedBy(entity.getModifiedBy());
			this.setModifiedDate(entity.getModifiedDate());
		}
	}
}
