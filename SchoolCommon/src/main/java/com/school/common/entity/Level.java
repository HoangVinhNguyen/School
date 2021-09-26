package com.school.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="level")
public class Level extends BaseEntity {

	private static final long serialVersionUID = -5021790767381766735L;

	@Column(length = 45, nullable = false, unique = true)
	private String code;

	@Column(length = 45, nullable = false, unique = true)
	private String name;

	@Column(length = 150, nullable = false)
	private String description;
	
	public Level() {
		
	}
	
	public Level(String code, String name, String description) {
		this.setCode(code);
		this.setName(name);
		this.setDescription(description);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.getId() == null ? 0 : this.getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		Level other = (Level) obj;
		if (this.getId() == null) {
			if (other.getId() != null) return false;
		}
		else if (!this.getId().equals(other.getId())) return false;
		return true;
	}

	@Override
	public String toString() {
		return this.name;
	}
	
}
