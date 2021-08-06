package com.school.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.school.model.ClassInModel;

@Entity
@Table(name = "class")
public class ClassInEntity extends BaseEntity {

	private String name;
	private String code;

	@ManyToOne
	@JoinColumn(name = "grade_id", nullable = false)
	private GradeEntity grade;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "class_classroom", joinColumns = 
	{ @JoinColumn(name = "class_id") }, inverseJoinColumns = {
			@JoinColumn(name = "classroom_id") 
	})
	private Set<ClassroomEntity> listClassroom = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clazz")
	private Set<UserEntity> listUser = new HashSet<UserEntity>();

	public Set<UserEntity> getListUser() {
		return listUser;
	}

	public void setListUser(Set<UserEntity> listUser) {
		this.listUser = listUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public GradeEntity getGrade() {
		return grade;
	}

	public void setGrade(GradeEntity grade) {
		this.grade = grade;
	}

	public Set<ClassroomEntity> getListClassroom() {
		return listClassroom;
	}

	public void setListClassroom(Set<ClassroomEntity> listClassroom) {
		this.listClassroom = listClassroom;
	}

	public void loadFromDTO(ClassInModel model) {
		if (model != null) {
			this.setId(model.getId());
			this.code = model.getCode();
			this.name = model.getName();
			this.grade = new GradeEntity();
			this.grade.loadFromDTO(model.getGrade());
			// classroom - class
			this.setCreatedBy(model.getCreatedBy());
			this.setCreatedDate(model.getCreatedDate());
			this.setModifiedBy(model.getModifiedBy());
			this.setModifiedDate(model.getModifiedDate());
			if (model.getUser() != null && !model.getUser().isEmpty()) {
				model.getUser().stream().forEach(e -> {
					UserEntity u = new UserEntity();
					u.loadFromDTO(e);
					this.listUser.add(u);
				});
			}
			if (model.getListClassroom() != null && !model.getListClassroom().isEmpty()) {
				model.getListClassroom().forEach(e -> {
					ClassroomEntity c = new ClassroomEntity();
					c.loadFromDTO(e);
					this.listClassroom.add(c);
				});

			}
		}
	}
}
