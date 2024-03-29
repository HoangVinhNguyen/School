package com.school.common.dto;

import com.school.common.entity.AcademicTranscript;

public class AcademicTranscriptDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	private UserSavePointDto student;
	private UserDto teacher;
	private ClazzDto clazz;
	private CourseDto course;
	private float point;
	private TopicDto topic;
	private int coefficient;

	public AcademicTranscriptDto(UserDto teacher, UserDto student, ClazzDto clazz, CourseDto course, float point) {
		//this.student = student;
		this.teacher = teacher;
		this.clazz = clazz;
		this.course = course;
		this.point = point;
	}

	public AcademicTranscriptDto(UserDto teacher, UserDto student, ClazzDto clazz, CourseDto course, float point, TopicDto topic, int coefficient) {
		//this.student = student;
		this.teacher = teacher;
		this.clazz = clazz;
		this.course = course;
		this.point = point;
		this.topic = topic;
		this.coefficient = coefficient;
	}
	
	public AcademicTranscriptDto() {
	}
	
	public AcademicTranscriptDto(Long id, float point) {
		this.setId(id);
		this.point = point;
	}

	public AcademicTranscriptDto(AcademicTranscript entity) {
		this.setId(entity.getId());
		this.student = new UserSavePointDto(entity.getStudent());
		this.teacher = new UserDto(entity.getTeacher());
		this.clazz = new ClazzDto(entity.getClazz());
		this.course = new CourseDto(entity.getCourse());
		this.point = entity.getPoint();
		this.topic = new TopicDto(entity.getTopic());
		this.coefficient = entity.getCoefficient();
	}

	public UserSavePointDto getStudent() {
		return student;
	}

	public void setStudent(UserSavePointDto student) {
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

	public TopicDto getTopic() {
		return topic;
	}

	public void setTopic(TopicDto topic) {
		this.topic = topic;
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
		AcademicTranscriptDto other = (AcademicTranscriptDto) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}

}
