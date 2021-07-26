package com.school.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.school.model.TeacherClassroomModel;

@Entity
@Table(name="teacher_classroom")
public class TeacherClassroomEntity extends BaseEntity {

	@Column(name="teacher_id")
	private Long teacherId;
	@Column(name="student_id")
	private Long studentId;
	@Column(name="classroom_id")
	private Long classroomId;
	@Column(name="course_id")
	private Long courseId;
	private Double point;


	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(Long classroomId) {
		this.classroomId = classroomId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Double getPoint() {
		return point;
	}

	public void setPoint(Double point) {
		this.point = point;
	}
	
	public void loadFromDTO(TeacherClassroomModel model) {
		this.setId(model.getId());
		this.setCreatedBy(model.getCreatedBy());
		this.setCreatedDate(model.getCreatedDate());
		this.setModifiedBy(model.getModifiedBy());
		this.setModifiedDate(model.getModifiedDate());
		this.classroomId = model.getClassroomId();
		this.courseId = model.getCourseId();
		this.studentId = model.getStudentId();
		this.teacherId = model.getTeacherId();
		this.point = model.getPoint();
	}
	
}
