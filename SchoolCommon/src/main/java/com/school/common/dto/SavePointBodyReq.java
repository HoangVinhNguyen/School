package com.school.common.dto;

import java.util.ArrayList;
import java.util.List;

public class SavePointBodyReq {

	private UserDto teacher;
	private List<UserDto> listStudents = new ArrayList<>();
	private ClazzDto clazz;
	private CourseDto course;

	public SavePointBodyReq() {
	}

	public SavePointBodyReq(UserDto teacher, List<UserDto> listStudents, ClazzDto clazz, CourseDto course) {
		this.teacher = teacher;
		this.setListStudents(listStudents);
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

	public List<UserDto> getListStudents() {
		return listStudents;
	}

	public void setListStudents(List<UserDto> listStudents) {
		this.listStudents = listStudents;
	}

}
