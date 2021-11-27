package com.school.common.dto;

import java.time.LocalDateTime;

import com.school.common.entity.AcademicTranscript;

public class AcademicTranscriptDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	private UserDto student;
	private UserDto teacher;
	private ClazzDto clazz;
	private CourseDto course;
	private float point;
	private String createdBy;
	private String modifiedBy;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;

	public AcademicTranscriptDto(UserDto teacher, UserDto student, ClazzDto clazz, CourseDto course, float point) {
		this.student = student;
		this.teacher = teacher;
		this.clazz = clazz;
		this.course = course;
		this.point = point;
	}

	public AcademicTranscriptDto(AcademicTranscript entity) {
		this.student = new UserDto(entity.getStudent());
		this.teacher = new UserDto(entity.getTeacher());
		this.clazz = new ClazzDto(entity.getClazz());
		this.course = new CourseDto(entity.getCourse());
		this.point = entity.getPoint();
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public UserDto getStudent() {
		return student;
	}

	public void setStudent(UserDto student) {
		this.student = student;
	}

	public UserDto getTeacher() {
		return teacher;
	}

	public void setTeacher(UserDto teacher) {
		this.teacher = teacher;
	}

	public ClazzDto getClazz() {
		return clazz;
	}

	public void setClazz(ClazzDto clazz) {
		this.clazz = clazz;
	}

	public CourseDto getCourse() {
		return course;
	}

	public void setCourse(CourseDto course) {
		this.course = course;
	}

	public float getPoint() {
		return point;
	}

	public void setPoint(float point) {
		this.point = point;
	}

}
