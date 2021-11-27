package com.school.common.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.school.common.entity.Clazz;
import com.school.common.entity.Course;
import com.school.common.entity.Role;

public class ClazzDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	private String name;
	private Integer numberOfStudents;
	private List<UserDto> listStudents = new ArrayList<>();
	
	
	
	public ClazzDto() {
	}

	public ClazzDto(String name, Integer numberOfStudents, List<UserDto> listStudents) {
		this.name = name;
		this.numberOfStudents = numberOfStudents;
		this.listStudents = listStudents;
	}
	
	public ClazzDto(Course entity) {
		this.name = entity.getName();
		this.numberOfStudents = entity.getUsers().size();
		this.listStudents = entity.getUsers().stream().map(UserDto::new).collect(Collectors.toList());
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumberOfStudents() {
		return numberOfStudents;
	}
	public void setNumberOfStudents(Integer numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}
	public List<UserDto> getListStudents() {
		return listStudents;
	}
	public void setListStudents(List<UserDto> listStudent) {
		this.listStudents = listStudent;
	}
	
	public ClazzDto(Clazz entity) {
		Optional<Clazz> op = Optional.ofNullable(entity);
		if (op.isPresent()) {
			List<Role> roles = new ArrayList<>();
			roles.add(new Role(3L));
			
			this.setId(entity.getId());
			this.name = entity.getName();
			if (entity.getUsers().size() > 0) {
				this.listStudents.addAll(
					entity.getUsers().stream()
						.filter(u -> u.getRoles().containsAll(roles)).map(UserDto::new).collect(Collectors.toList())
				);
				this.numberOfStudents = this.getListStudents().size();
			}
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClazzDto other = (ClazzDto) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}
}
