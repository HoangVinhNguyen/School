package com.school.common.entity;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.school.common.dto.AcademicTranscriptDto;

@Entity
@Table(name="academic_transcript")
public class AcademicTranscript extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "student_id")
	private User student;
	
	@OneToOne
	@JoinColumn(name = "teacher_id")
	private User teacher;
	
	@OneToOne
	@JoinColumn(name = "class_id")
	private Clazz clazz;
	
	@OneToOne
	@JoinColumn(name = "course_id")
	private Course course;
	
	private float point;

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

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public float getPoint() {
		return point;
	}

	public void setPoint(float point) {
		this.point = point;
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
		AcademicTranscript other = (AcademicTranscript) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}
	
	@Transient
	public static AcademicTranscript convertToAcademicTranscript(AcademicTranscriptDto userDto) {
		Optional<AcademicTranscriptDto> op = Optional.ofNullable(userDto);
		if (op.isPresent()) {
			AcademicTranscriptDto academicDto = op.get();
			AcademicTranscript academic = new AcademicTranscript();
			academic.setId(academicDto.getId());
			academic.setClazz(Clazz.convertToClazz(academicDto.getClazz()));
			academic.setCourse(Course.convertToCourse(academicDto.getCourse()));
			academic.setTeacher(User.convertToUser(academicDto.getTeacher()));
			academic.setStudent(User.convertToUser(academicDto.getStudent()));
			academic.setPoint(academicDto.getPoint());
			return academic;
		}
		return null;
	}
}
