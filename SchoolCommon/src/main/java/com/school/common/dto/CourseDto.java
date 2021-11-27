package com.school.common.dto;

import com.school.common.entity.Course;

public class CourseDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	private String name;
	
	public CourseDto() {
	}
	
	public CourseDto(Course entity) {
		this.setId(entity.getId());
		this.name = entity.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseDto other = (CourseDto) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}
}
