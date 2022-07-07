package com.school.common.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.school.common.entity.StudentAndCourse;

public class StudentAndCourseDto extends BaseDto {

	private static final long serialVersionUID = 1L;
	
	private UserDto teacher;
	private ClazzDto clazz;
	private CourseDto course;
	private UserDto student;
	private List<Point> listPoint = new ArrayList<>();
	private LocalDateTime createdDate;

	public StudentAndCourseDto(StudentAndCourse entity) {
		if (entity != null) {
			this.setId(entity.getId());
			this.student = new UserDto(entity.getStudent());
			this.teacher = new UserDto(entity.getTeacher());
			this.clazz = new ClazzDto(entity.getClazz());
			this.course = new CourseDto(entity.getCourse());
			Point point = new Point(entity.getId(), entity.getPoint(), entity.getCoefficient(), entity.getCreatedDate());
			listPoint.add(point);
		}
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
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

	public List<Point> getListPoint() {
		return listPoint;
	}

	public void setListPoint(List<Point> listPoint) {
		this.listPoint = listPoint;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.getId() == null ? 0 : this.getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		StudentAndCourseDto other = (StudentAndCourseDto) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}
}
