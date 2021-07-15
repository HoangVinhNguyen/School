package com.school.model;

import com.school.entity.TeacherClassroomEntity;

public class TeacherClassroomModel extends AbstractModel {

	private Long teacherId;
	private Long studentId;
	private Long classroomId;
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
	
	public void loadFromEntity(TeacherClassroomEntity entity) {
		this.classroomId = entity.getClassroomId();
		this.courseId = entity.getCourseId();
		this.studentId = entity.getStudentId();
		this.teacherId = entity.getTeacherId();
		this.point = entity.getPoint();
		this.setId(entity.getId());
		this.setCreatedBy(entity.getCreatedBy());
		this.setCreatedDate(entity.getCreatedDate());
		this.setModifiedBy(entity.getModifiedBy());
		this.setModifiedDate(entity.getModifiedDate());
	}
	
}
