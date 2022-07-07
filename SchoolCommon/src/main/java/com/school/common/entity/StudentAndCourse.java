package com.school.common.entity;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.school.common.dto.StudentAndPointDto;

@Entity
public class StudentAndCourse extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private User teacher;
	private Clazz clazz;
	private Course course;
	private User student;
	private float point;
	private int coefficient;
	private LocalDateTime createdDate;
	
	public StudentAndCourse() {
	}
	
	public StudentAndCourse(Long id, User student, 
			User teacher, Clazz clazz, Course course, 
			float point, int coefficient, 
			LocalDateTime createdDate) {
		this.setId(id);
		this.student = student;
		this.teacher = teacher;
		this.clazz = clazz;
		this.course = course;
		this.point = point;
		this.coefficient = coefficient;
		this.createdDate = createdDate;
	}
	
	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
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

	public float getPoint() {
		return point;
	}

	public void setPoint(float point) {
		this.point = point;
	}

	public int getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
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
		StudentAndCourse other = (StudentAndCourse) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}
	
	@Transient
	public static StudentAndCourse convertToStudentAndPoint(StudentAndPointDto userDto) {
		Optional<StudentAndPointDto> op = Optional.ofNullable(userDto);
		if (op.isPresent()) {
			StudentAndPointDto academicDto = op.get();
			StudentAndCourse academic = new StudentAndCourse();
			academic.setId(academicDto.getId());
			academic.setStudent(User.convertToUser(academicDto.getStudent()));
			//academic.setPoint(academicDto.getListPoint().);
			return academic;
		}
		return null;
	}
	
}
