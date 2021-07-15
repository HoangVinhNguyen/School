package com.school.model;

import com.school.entity.InClassroomEntity;
import com.school.entity.RoleEntity;

public class InClassroomModel extends AbstractModel {

	private Long studentId;
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

	public void loadFromEntity(InClassroomEntity entity) {
		this.classroomId = entity.getClassroomId();
		this.studentId = entity.getStudentId();
		this.setCreatedBy(entity.getCreatedBy());
		this.setCreatedDate(entity.getCreatedDate());
		this.setModifiedBy(entity.getModifiedBy());
		this.setModifiedDate(entity.getModifiedDate());
	}
	
}
