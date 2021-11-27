package com.school.common.entity;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.school.common.dto.CourseDto;

@Entity
@Table(name="course")
public class Course extends BaseEntity {

	private static final long serialVersionUID = 8258337930613017006L;

	@Column(length = 50, nullable = false, unique = true)
	private String code;

	@Column(length = 50, nullable = false, unique = true)
	private String name;

	@Column(length = 150, nullable = false)
	private String description;
	
	@ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
	private Set<User> users = new HashSet<>();

	public Course() {
		
	}
	
	public Course(Long id) {
		this.setId(id);
	}

	public Course(String code, String name, String description) {
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
	
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public void addUser(User user) {
		Optional<User> op  = Optional.ofNullable(user);
		if (op.isPresent()) {
			this.users.add(op.get());
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
		Course other = (Course) obj;
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
	public static Course convertToCourse(CourseDto courseDto) {
		Optional<CourseDto> op = Optional.ofNullable(courseDto);
		if (op.isPresent()) {
			CourseDto c = op.get();
			Course course = new Course();
			course.setId(c.getId());
			course.name = c.getName();
			return course;
		}
		return null;
	}
}
