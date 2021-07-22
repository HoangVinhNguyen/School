package com.school.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.school.model.InClassroomModel;

@Entity(name="in_classroom")
public class InClassroomEntity extends BaseEntity {

	@Column(name="student_id")
	private Long studentId;
	@Column(name="classroom_id")
	private Long classroomId;

	public Long getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(Long classroomId) {
		this.classroomId = classroomId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public void loadFromDTO(InClassroomModel model) {
		this.setId(model.getId());
		this.studentId = model.getStudentId();
		this.classroomId = model.getClassroomId();
		this.setCreatedBy(model.getCreatedBy());
		this.setCreatedDate(model.getCreatedDate());
		this.setModifiedBy(model.getModifiedBy());
		this.setModifiedDate(model.getModifiedDate());
	}
	
}
