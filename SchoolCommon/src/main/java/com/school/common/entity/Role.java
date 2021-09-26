package com.school.common.entity;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.school.common.dto.RoleDto;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

	private static final long serialVersionUID = 2553421292532850624L;

	@Column(length = 45, nullable = false, unique = true)
	private String name;

	@Column(length = 150, nullable = false)
	private String description;

	public Role() {
		
	}
	
	public Role(Long id) {
		this.setId(id);
	}
	
	public Role(String name, String decription) {
		this.name = name;
		this.description = decription;
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
		result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
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
	
	@Transient
	public Role clone() {
		Role role = new Role();
		role.setId(this.getId());
		role.setName(this.getName());
		role.setDescription(this.getDescription());
		role.setDeleted(this.isDeleted());
		role.setCreatedBy(this.getCreatedBy());
		role.setCreatedDate(this.getCreatedDate());
		role.setModifiedBy(this.getModifiedBy());
		role.setModifiedDate(this.getModifiedDate());
		return role;
	}
	
	@Transient
	public static Role convertToRole(RoleDto roleDto) {
		Optional<RoleDto> op = Optional.ofNullable(roleDto);
		if (op.isPresent()) {
			RoleDto r = op.get();
			Role role = new Role();
			role.setId(r.getId());
			return role;
		}
		return null;
	}
	
}
