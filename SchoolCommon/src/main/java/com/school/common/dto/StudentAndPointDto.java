package com.school.common.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.school.common.entity.StudentAndPoint;

public class StudentAndPointDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	private UserDto student;
	private List<Point> listPoint = new ArrayList<>();
	private LocalDateTime createdDate;

	public StudentAndPointDto(StudentAndPoint entity) {
		if (entity != null) {
			this.setId(entity.getId());
			this.student = new UserDto(entity.getStudent());
			Point point = new Point(entity.getId(), entity.getPoint(), entity.getCoefficient(), entity.getCreatedDate());
			listPoint.add(point);
		}
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public UserDto getStudent() {
		return student;
	}

	public void setStudent(UserDto student) {
		this.student = student;
	}

	public List<Point> getListPoint() {
		return listPoint;
	}

	public void setListPoint(List<Point> listPoint) {
		this.listPoint = listPoint;
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
		StudentAndPointDto other = (StudentAndPointDto) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}
}
