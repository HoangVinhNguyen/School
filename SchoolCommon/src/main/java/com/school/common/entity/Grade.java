package com.school.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "grade")
public class Grade extends BaseEntity {

	private static final long serialVersionUID = -2440888477841599137L;

	@Column(length = 50, nullable = false, unique = true)
	private Integer code;

	@Column(length = 50, nullable = false, unique = true)
	private String name;

	@Column(length = 150, nullable = false)
	private String description;

	@OneToOne
	@JoinColumn(name = "level_id")
	private Level level;

	public Grade() {

	}

	public Grade(Long id) {
		this.setId(id);
	}

	public Grade(Integer code, String name, String description, Level level) {
		this.setCode(code);
		this.setName(name);
		this.setDescription(description);
		this.level = level;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
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

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
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
		Grade other = (Grade) obj;
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
