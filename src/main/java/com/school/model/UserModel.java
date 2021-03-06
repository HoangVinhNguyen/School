package com.school.model;

import java.sql.Timestamp;

import com.school.entity.UserEntity;

public class UserModel extends AbstractModel {

	private String email;
	private String password;
	private String fullname;
	private Timestamp dob;
	private String address;
	private Long roledId;
	private int isDeleted;
	private RoleModel role = new RoleModel();

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getDob() {
		return dob;
	}

	public void setDob(Timestamp dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long isRoledId() {
		return roledId;
	}

	public void setRoledId(Long roledId) {
		this.roledId = roledId;
	}

	public int isDeleted() {
		return isDeleted;
	}

	public void setDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public RoleModel getRole() {
		return role;
	}

	public void setRole(RoleModel role) {
		this.role = role;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public void loadFromEntity(UserEntity userEntity) {
		this.setId(userEntity.getId());
		this.setCreatedBy(userEntity.getCreatedBy());
		this.setCreatedDate(userEntity.getCreatedDate());
		this.setModifiedBy(userEntity.getModifiedBy());
		this.setModifiedDate(userEntity.getModifiedDate());
		this.address = userEntity.getAddress();
		this.dob = userEntity.getDob();
		this.email = userEntity.getEmail();
		this.fullname = userEntity.getFullname();
		this.role.loadFromEntity(userEntity.getRole());
	}

}
