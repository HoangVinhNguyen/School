package com.school.common.entity;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="class")
public class Clazz extends BaseEntity {

	private static final long serialVersionUID = 7262682027766723720L;

	@Column(length = 50, nullable = false, unique = true)
	private String code;

	@Column(length = 50, nullable = false, unique = true)
	private String name;

	@Column(length = 150, nullable = false)
	private String description;

	@OneToOne
	@JoinColumn(name = "grade_id")
	private Grade grade;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "class_classroom", 
		joinColumns = @JoinColumn(name = "class_id"),
		inverseJoinColumns = @JoinColumn(name = "classroom_id"))
	private Set<Classroom> classrooms = new HashSet<>();

	public Clazz() {
		
	}
	
	public Clazz(Long id) {
		this.setId(id);
	}
	
	public Clazz(String code, String name, String description, Grade grade) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.grade = grade;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
	public Set<Classroom> getClassrooms() {
		return classrooms;
	}

	public void setClassrooms(Set<Classroom> classrooms) {
		this.classrooms = classrooms;
	}
	
	public void addClassroom(Classroom classroom) {
		Optional<Classroom> op = Optional.ofNullable(classroom);
		if (op.isPresent()) {
			this.classrooms.add(op.get());
		}
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
		Clazz other = (Clazz) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.name;
	}
	
}
