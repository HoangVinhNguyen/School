package com.school.common.dto;

import java.util.Optional;

import com.school.common.entity.Topic;

public class TopicDto extends BaseDto {

	private static final long serialVersionUID = 1L;
	
	private String code;
	private String content;
	private UserDto teacher;
	private CourseDto course;
	private ClazzDto clazz;
	private int coefficient;
	
	public TopicDto() {
		
	}
	
	public TopicDto(String code, String content, UserDto teacher, CourseDto course, ClazzDto clazz) {
		this.code = code;
		this.content = content;
		this.teacher = teacher;
		this.course = course;
		this.clazz = clazz;
	}
	
	public TopicDto(Topic entity) {
		Optional<Topic> op = Optional.ofNullable(entity);
		if (op.isPresent()) {
			this.setId(entity.getId());
			this.code = entity.getCode();
			this.content = entity.getContent();
			this.teacher = new UserDto(entity.getTeacher());
			this.course = new CourseDto(entity.getCourse());
			this.clazz = new ClazzDto(entity.getClazz());
			this.coefficient = entity.getCoefficient();
		}
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public UserDto getTeacher() {
		return teacher;
	}
	public void setTeacher(UserDto teacher) {
		this.teacher = teacher;
	}
	public CourseDto getCourse() {
		return course;
	}
	public void setCourse(CourseDto course) {
		this.course = course;
	}
	public ClazzDto getClazz() {
		return clazz;
	}
	public void setClazz(ClazzDto clazz) {
		this.clazz = clazz;
	}
	public int getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TopicDto other = (TopicDto) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}
	
}
