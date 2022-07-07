package com.school.common.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.school.common.dto.TopicDto;

@Entity
@Table(name = "topic")
public class Topic extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String code;
	private String content;
	@OneToOne
	@JoinColumn(name = "teacher_id")
	private User teacher;
	@OneToOne
	@JoinColumn(name = "course_id")
	private Course course;
	@OneToOne
	@JoinColumn(name = "class_id")
	private Clazz clazz;
	private int coefficient;

	public Topic() {
		
	}

	public Topic(User teacher, Course course, Clazz clazz) {
		this.teacher = teacher;
		this.course = course;
		this.clazz = clazz;
	}
	
	public Topic(String code, String content, User teacher, Course course, Clazz clazz, int coefficient) {
		this.code = code;
		this.content = content;
		this.teacher = teacher;
		this.course = course;
		this.clazz = clazz;
		this.coefficient = coefficient;
	}
	
	public Topic(TopicDto dto) {
		if (dto != null) {
			this.setId(dto.getId());
			this.code = dto.getCode();
			this.content = dto.getContent();
			this.teacher = User.convertToUser(dto.getTeacher());
			this.course = Course.convertToCourse(dto.getCourse());
			this.clazz = Clazz.convertToClazz(dto.getClazz());
			this.coefficient = dto.getCoefficient();
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
	public User getTeacher() {
		return teacher;
	}
	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Clazz getClazz() {
		return clazz;
	}
	public void setClazz(Clazz clazz) {
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
		Topic other = (Topic) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}
	
}
