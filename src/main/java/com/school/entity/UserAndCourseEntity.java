package com.school.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.school.model.UserAndCourseModel;

@Entity
@Table(name = "user_course")
public class UserAndCourseEntity extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;
	@OneToOne
	@JoinColumn(name = "course_id", nullable = false)
	private CourseEntity course;
	@Transient
	private CourseEntity oldCourse;

	public CourseEntity getOldCourse() {
		return oldCourse;
	}

	public void setOldCourse(CourseEntity oldCourse) {
		this.oldCourse = oldCourse;
	}

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
			this.user = new UserEntity();
			this.course = new CourseEntity();
			this.user.loadFromDTO(model.getUserModel());
			this.course.loadFromDTO(model.getCourseModel());
			this.oldCourse = new CourseEntity();
			this.oldCourse.loadFromDTO(model.getOldCourse());
			this.setCreatedBy(model.getCreatedBy());
			this.setCreatedDate(model.getCreatedDate());
			this.setModifiedBy(model.getModifiedBy());
			this.setModifiedDate(model.getModifiedDate());
		}
	}

}
