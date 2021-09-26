package com.school.common.dto;

import java.util.Optional;

import com.school.common.entity.Role;

public class RoleDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -442126325814614561L;
	private String name;
	private String description;
	

	public RoleDto() {

	}

	public RoleDto(Long id) {
		this.setId(id);
	}

	public RoleDto(String name, String decription) {
		this.name = name;
		this.description = decription;
	}
	
	public RoleDto(Role entity) {
		Optional<Role> op = Optional.ofNullable(entity);
		if (op.isPresent()) {
			this.setId(entity.getId());
			this.name = entity.getName();
			this.description = entity.getDescription();
		}
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
		RoleDto other = (RoleDto) obj;
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
	
	public RoleDto clone() {
		RoleDto roleDto = new RoleDto();
		roleDto.setId(this.getId());
		roleDto.setName(this.getName());
		roleDto.setDescription(this.getDescription());
		return roleDto;
	}

}
