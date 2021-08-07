package com.school.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.school.model.ClazzAndClassroomModel;

@Entity
@Table(name = "class_classroom")
public class ClazzAndClassroomEntity extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "class_id", nullable = false)
	private ClazzEntity clazz;
	@ManyToOne
	@JoinColumn(name = "classroom_id", nullable = false)
	private ClassroomEntity classroom;

	public ClazzEntity getClazz() {
		return clazz;
	}

	public void setClazz(ClazzEntity clazz) {
		this.clazz = clazz;
	}

	public ClassroomEntity getClassroom() {
		return classroom;
	}

	public void setClassroom(ClassroomEntity classroom) {
		this.classroom = classroom;
	}

	public void loadFromDTO(ClazzAndClassroomModel model) {
		if (model != null) {
			this.setId(model.getId());
			this.clazz = new ClazzEntity();
			this.classroom = new ClassroomEntity();
			this.clazz.loadFromDTO(model.getClazzModel());
			this.classroom.loadFromDTO(model.getClassroomModel());
			this.setCreatedBy(model.getCreatedBy());
			this.setCreatedDate(model.getCreatedDate());
			this.setModifiedBy(model.getModifiedBy());
			this.setModifiedDate(model.getModifiedDate());
		}
	}

}
