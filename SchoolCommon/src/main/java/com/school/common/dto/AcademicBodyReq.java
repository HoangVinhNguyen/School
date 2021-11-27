package com.school.common.dto;

public class AcademicBodyReq {

	private UserDto teacher;
	private ClazzDto clazz;
	private CourseDto course;
	
	
	public AcademicBodyReq() {
	}

	public AcademicBodyReq(UserDto teacher, ClazzDto clazz, CourseDto course) {
		this.teacher = teacher;
		this.clazz = clazz;
		this.course = course;
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
	
	
}
