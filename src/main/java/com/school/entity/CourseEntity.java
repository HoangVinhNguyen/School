package com.school.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.school.model.CourseModel;

@Entity
@Table(name="course")
public class CourseEntity extends BaseEntity {

	private String name;
	private String code;
	
	@ManyToMany(mappedBy = "course")
	private Set<UserEntity> user = new HashSet<UserEntity>();
	
	public Set<UserEntity> getUser() {
		return user;
	}
	public void setUser(Set<UserEntity> user) {
		this.user = user;
	}
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
	
	public void loadFromDTO(CourseModel model) {
		if (model != null) {
			this.setId(model.getId());
			this.code = model.getCode();
			this.name = model.getName();
			this.setCreatedBy(model.getCreatedBy());
			this.setCreatedDate(model.getCreatedDate());
			this.setModifiedBy(model.getModifiedBy());
			this.setModifiedDate(model.getModifiedDate());
			if (model.getUser() != null && !model.getUser().isEmpty()) {
				model.getUser().stream().forEach(e -> {
					UserEntity u = new UserEntity();
					u.loadFromDTO(e);
					this.user.add(u);
				});
			}
		 }
	}
	
}
