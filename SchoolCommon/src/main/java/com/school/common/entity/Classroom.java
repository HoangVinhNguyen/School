package com.school.common.entity;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="classroom")
public class Classroom extends BaseEntity {

	private static final long serialVersionUID = -1230616448004109366L;

	@Column(length = 50, nullable = false, unique = true)
	private String code;

	@Column(length = 50, nullable = false, unique = true)
	private String name;

	@Column(length = 150, nullable = false)
	private String description;

	@ManyToMany(mappedBy = "classrooms", fetch = FetchType.LAZY)
	private Set<Clazz> clazzes = new HashSet<>();
	
	public Classroom() {
		
	}
	
	public Classroom(Long id) {
		this.setId(id);
	}

	public Classroom(String code, String name, String description) {
		this.code = code;
		this.name = name;
		this.description = description;
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

	public Set<Clazz> getClazzes() {
		return clazzes;
	}

	public void setClazzes(Set<Clazz> clazzes) {
		this.clazzes = clazzes;
	}

	public void addClass(Clazz clazz) {
		Optional<Clazz> op = Optional.ofNullable(clazz);
		if (op.isPresent()) this.clazzes.add(op.get());
	}
	
	public String getNameAllClazz() {
		StringBuilder name = new StringBuilder();
		this.clazzes.forEach(c -> {
			name.append(c.getName());
			name.append(" ");
		});
		name.deleteCharAt(name.length() - 1);
		return name.toString();
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
		Classroom other = (Classroom) obj;
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
