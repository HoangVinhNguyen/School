package com.school.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.school.model.InClassroomModel;

@Entity
@Table(name = "in_classroom")
public class InClassroomEntity extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity student;
	@OneToOne
	@JoinColumn(name = "classroom_id", nullable = false)
	private ClassroomEntity classroom;

	public UserEntity getStudent() {
		return student;
	}

	public void setStudent(UserEntity student) {
		this.student = student;
	}

	public ClassroomEntity getClassroom() {
		return classroom;
	}

	public void setClassroom(ClassroomEntity classroom) {
		this.classroom = classroom;
	}

	public void loadFromDTO(InClassroomModel model) {
		if (model != null) {
			this.setId(model.getId());
			this.student.loadFromDTO(model.getStudentModel());
			this.classroom.loadFromDTO(model.getClassroomModel());
			this.setCreatedBy(model.getCreatedBy());
			this.setCreatedDate(model.getCreatedDate());
			this.setModifiedBy(model.getModifiedBy());
			this.setModifiedDate(model.getModifiedDate());
		}
	}

}
