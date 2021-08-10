package com.school.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.school.model.PointModel;

@Entity
@Table(name = "point")
public class PointEntity extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private UserEntity teacher;
	@ManyToOne
	@JoinColumn(name = "student_id")
	private UserEntity student;
	@ManyToOne
	@JoinColumn(name = "class_id")
	private ClazzEntity clazz;
	@ManyToOne
	@JoinColumn(name = "course_id")
	private CourseEntity course;
	private Double point;

	public UserEntity getTeacher() {
		return teacher;
	}

	public void setTeacher(UserEntity teacher) {
		this.teacher = teacher;
	}

	public UserEntity getStudent() {
		return student;
	}

	public void setStudent(UserEntity student) {
		this.student = student;
	}

	public ClazzEntity getClazz() {
		return clazz;
	}

	public void setClazz(ClazzEntity clazz) {
		this.clazz = clazz;
	}

	public CourseEntity getCourse() {
		return course;
	}

	public void setCourse(CourseEntity course) {
		this.course = course;
	}

	public Double getPoint() {
		return point;
	}

	public void setPoint(Double point) {
		this.point = point;
	}

	public void loadFromDTO(PointModel model) {
		if (model != null) {
			this.setId(model.getId());
			this.setCreatedBy(model.getCreatedBy());
			this.setCreatedDate(model.getCreatedDate());
			this.setModifiedBy(model.getModifiedBy());
			this.setModifiedDate(model.getModifiedDate());
			this.clazz = new ClazzEntity();
			this.course = new CourseEntity();
			this.student = new UserEntity();
			this.teacher = new UserEntity();
			this.clazz.loadFromDTO(model.getClazz());
			this.course.loadFromDTO(model.getCourse());
			this.student.loadFromDTO(model.getStudent());
			this.teacher.loadFromDTO(model.getTeacher());
			this.point = model.getPoint();
		}
	}

}
