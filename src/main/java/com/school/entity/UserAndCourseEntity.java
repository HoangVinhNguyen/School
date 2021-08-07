package com.school.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.school.model.UserAndCourseModel;

@Entity
@Table(name = "user_course")
public class UserAndCourseEntity extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;
	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	private CourseEntity course;

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public CourseEntity getCourse() {
		return course;
	}

	public void setCourse(CourseEntity course) {
		this.course = course;
	}

	public void loadFromDTO(UserAndCourseModel model) {
		if (model != null) {
			this.setId(model.getId());
			this.user = new UserEntity();
			this.course = new CourseEntity();
			this.user.loadFromDTO(model.getUserModel());
			this.course.loadFromDTO(model.getCourseModel());
			this.setCreatedBy(model.getCreatedBy());
			this.setCreatedDate(model.getCreatedDate());
			this.setModifiedBy(model.getModifiedBy());
			this.setModifiedDate(model.getModifiedDate());
		}
	}

}
