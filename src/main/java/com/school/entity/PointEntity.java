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
	private UserEntity teacherEntity;
	@ManyToOne
	@JoinColumn(name="student_id")
	private UserEntity studentEntity;
	@ManyToOne
	@JoinColumn(name="classroom_id")
	private ClassroomEntity classroomEntity;
	@ManyToOne
	@JoinColumn(name="course_id")
	private CourseEntity courseEntity;
	private Double point;


	public UserEntity getTeacherEntity() {
		return teacherEntity;
	}

	public void setTeacherEntity(UserEntity teacherEntity) {
		this.teacherEntity = teacherEntity;
	}

	public UserEntity getStudentEntity() {
		return studentEntity;
	}

	public void setStudentEntity(UserEntity studentEntity) {
		this.studentEntity = studentEntity;
	}

	public ClassroomEntity getClassroomEntity() {
		return classroomEntity;
	}

	public void setClassroomEntity(ClassroomEntity classroomEntity) {
		this.classroomEntity = classroomEntity;
	}

	public CourseEntity getCourseEntity() {
		return courseEntity;
	}

	public void setCourseEntity(CourseEntity courseEntity) {
		this.courseEntity = courseEntity;
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
			this.classroomEntity.loadFromDTO(model.getClassroomModel());
			this.courseEntity.loadFromDTO(model.getCourseModel());
			this.studentEntity.loadFromDTO(model.getStudentModel());
			this.teacherEntity.loadFromDTO(model.getStudentModel());
			this.point = model.getPoint();
		 }
	}
	
}
