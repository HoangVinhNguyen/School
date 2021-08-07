package com.school.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.school.model.UserAndClazzModel;

@Entity
@Table(name = "user_class")
public class UserAndClazzEntity extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "class_id", nullable = false)
	private ClazzEntity clazz;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;

	public ClazzEntity getClazz() {
		return clazz;
	}

	public void setClazz(ClazzEntity clazz) {
		this.clazz = clazz;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public void loadFromDTO(UserAndClazzModel model) {
		if (model != null) {
			this.setId(model.getId());
			this.clazz = new ClazzEntity();
			this.user = new UserEntity();
			this.clazz.loadFromDTO(model.getClazzModel());
			this.user.loadFromDTO(model.getUserModel());
			this.setCreatedBy(model.getCreatedBy());
			this.setCreatedDate(model.getCreatedDate());
			this.setModifiedBy(model.getModifiedBy());
			this.setModifiedDate(model.getModifiedDate());
		}
	}

}
