package com.school.common.entity;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.Transient;

import com.school.common.dto.StudentAndPointDto;

@Entity
public class StudentAndPoint extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private User student;
	private float point;
	private int coefficient;
	private LocalDateTime createdDate;
	
	//AcademicTranscript - id
	
	public StudentAndPoint() {
	}
	
	public StudentAndPoint(Long id, User student, float point, int coefficient, LocalDateTime createdDate) {
		this.setId(id);
		this.student = student;
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
		StudentAndPoint other = (StudentAndPoint) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}
	
	@Transient
	public static StudentAndPoint convertToStudentAndPoint(StudentAndPointDto userDto) {
		Optional<StudentAndPointDto> op = Optional.ofNullable(userDto);
		if (op.isPresent()) {
			StudentAndPointDto academicDto = op.get();
			StudentAndPoint academic = new StudentAndPoint();
			academic.setId(academicDto.getId());
			academic.setStudent(User.convertToUser(academicDto.getStudent()));
			//academic.setPoint(academicDto.getListPoint().);
			return academic;
		}
		return null;
	}
	
}
