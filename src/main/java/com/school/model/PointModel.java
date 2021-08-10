package com.school.model;

import com.school.entity.PointEntity;

public class PointModel extends BaseModel {

	private UserModel teacher = new UserModel();
	private UserModel student = new UserModel();
	private ClazzModel clazz = new ClazzModel();
	private CourseModel course = new CourseModel();
	private Double point;

	public UserModel getTeacher() {
		return teacher;
	}

	public void setTeacher(UserModel teacher) {
		this.teacher = teacher;
	}

	public UserModel getStudent() {
		return student;
	}

	public void setStudent(UserModel student) {
		this.student = student;
	}

	public ClazzModel getClazz() {
		return clazz;
	}

	public void setClazz(ClazzModel clazz) {
		this.clazz = clazz;
	}

	public CourseModel getCourse() {
		return course;
	}

	public void setCourse(CourseModel course) {
		this.course = course;
	}

	public Double getPoint() {
		return point;
	}

	public void setPoint(Double point) {
		this.point = point;
	}

	public void loadFromEntity(PointEntity entity) {
		if (entity != null) {
			this.setId(entity.getId());
			this.clazz.loadFromEntity(entity.getClazz());
			this.course.loadFromEntity(entity.getCourse());
			this.student.loadFromEntityNotPassword(entity.getStudent());
			this.teacher.loadFromEntityNotPassword(entity.getTeacher());
			this.point = entity.getPoint();
			this.setCreatedBy(entity.getCreatedBy());
			this.setCreatedDate(entity.getCreatedDate());
			this.setModifiedBy(entity.getModifiedBy());
			this.setModifiedDate(entity.getModifiedDate());
		}
	}

}
