package com.school.common.dto;

import com.school.common.entity.AcademicTranscript;

public class AcademicTranscriptStudentPointDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	private UserDto student;
	private UserDto teacher;
	private ClazzDto clazz;
	private CourseDto course;
	private float point;
	private TopicDto topic;
	private int coefficient;
	
	public AcademicTranscriptStudentPointDto() {
		
	}

	public AcademicTranscriptStudentPointDto(UserDto teacher, UserDto student, ClazzDto clazz, CourseDto course, float point) {
		//this.student = student;
		this.teacher = teacher;
		this.clazz = clazz;
		this.course = course;
		this.point = point;
	}

	public AcademicTranscriptStudentPointDto(UserDto student, UserDto teacher, CourseDto course, ClazzDto clazz, TopicDto topic, int coefficient, float point, Long id ) {
		this.student = student;
		this.teacher = teacher;
		this.clazz = clazz;
		this.course = course;
		this.point = point;
		this.topic = topic;
		this.coefficient = coefficient;
		this.setId(id);
	}
	
	public AcademicTranscriptStudentPointDto(Long id, float point) {
		this.setId(id);
		this.point = point;
	}

	public AcademicTranscriptStudentPointDto(AcademicTranscript entity) {
		this.student = new UserDto(entity.getStudent());
		this.teacher = new UserDto(entity.getTeacher());
		this.clazz = new ClazzDto(entity.getClazz());
		this.course = new CourseDto(entity.getCourse());
		this.point = entity.getPoint();
		this.topic = new TopicDto(entity.getTopic());
		this.coefficient = entity.getCoefficient();
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
		AcademicTranscriptStudentPointDto other = (AcademicTranscriptStudentPointDto) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}

}
