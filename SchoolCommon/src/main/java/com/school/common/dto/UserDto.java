package com.school.common.dto;

import java.time.LocalDate;
import java.util.Optional;

import com.school.common.common.ResourceGet;
import com.school.common.common.SystemConstant;
import com.school.common.entity.User;

public class UserDto extends BaseDto {

	private static final long serialVersionUID = -7833564745684143904L;

	private String firstName;
	private String lastName;
	private String fullName;
	private String email;
	private String phone;
	private LocalDate dob;
	private String address;
	private String photos;
	private float point;

	public UserDto() {

	}

	public UserDto(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public UserDto(User entity) {
		Optional<User> op = Optional.ofNullable(entity);
		if (op.isPresent()) {
			this.setId(entity.getId());
			this.firstName = entity.getFirstName();
			this.lastName = entity.getLastName();
			this.email = entity.getEmail();
			this.dob = entity.getDob();
			this.address = entity.getAddress();
			this.phone = entity.getPhone();
			this.photos = entity.getPhotos();
//			this.roles = entity.getRoles().stream().map(RoleDto::new).collect(Collectors.toSet());
//			this.setDeleted(entity.isDeleted());
//			this.setCreatedBy(entity.getCreatedBy());
//			this.setCreatedDate(entity.getCreatedDate());
//			this.setModifiedBy(entity.getModifiedBy());
//			this.setModifiedDate(entity.getModifiedDate());
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFullName() {
		StringBuilder fullname = new StringBuilder(getFirstName());
		fullname.append(" ").append(getLastName());
		return fullname.toString();
	}

	public float getPoint() {
		return point;
	}

	public void setPoint(float point) {
		this.point = point;
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
		UserDto other = (UserDto) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}

	public String getPhotosImagePath() {
		if (this.getId() == null || this.getPhotos() == null)
			return SystemConstant.PATH_IMG_DEFAULT;
		ResourceGet getImagePath = new ResourceGet();
		return getImagePath.getUserImagePathWithId(this.getId(), this.getPhotos());
	}

	public UserDto clone() {
		UserDto userDto = new UserDto();
		userDto.setId(getId());
		userDto.setDob(getDob());
		userDto.setEmail(getEmail());
		userDto.setFirstName(getFirstName());
		userDto.setLastName(getLastName());
		userDto.setPhotos(getPhotos());
		userDto.setAddress(getAddress());
		userDto.setPhone(getPhone());
		return userDto;
	}

}
