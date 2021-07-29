package com.school.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.school.model.PointModel;

@Entity
@Table(name="point")
public class PointEntity extends BaseEntity {

	@ManyToOne
	@JoinColumn(name="teacher_id")
	private UserEntity teacher;
	@ManyToOne
	@JoinColumn(name="student_id")
	private UserEntity student;
	@ManyToOne
	@JoinColumn(name="classroom_id")
	private ClassroomEntity classroom;
	@ManyToOne
	@JoinColumn(name="course_id")
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

	public ClassroomEntity getClassroom() {
		return classroom;
	}

	public void setClassroom(ClassroomEntity classroom) {
		this.classroom = classroom;
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
			this.classroom.loadFromDTO(model.getClassroom());
			this.course.loadFromDTO(model.getCourse());
			this.student.loadFromDTO(model.getStudent());
			this.teacher.loadFromDTO(model.getTeacher());
			this.point = model.getPoint();
		 }
	}
	
}
