package com.school.model;

import java.sql.Timestamp;

import com.school.entity.UserEntity;

public class UserModel extends BaseModel {

	private String email;
	private String password;
	private String fullname;
	private Timestamp dob;
	private String phone;
	private String address;
	private Long roleId;
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
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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
	
	public void loadFromEntity(UserEntity entity) {
		if (entity != null) {
			this.setId(entity.getId());
			this.setCreatedBy(entity.getCreatedBy());
			this.setCreatedDate(entity.getCreatedDate());
			this.setModifiedBy(entity.getModifiedBy());
			this.setModifiedDate(entity.getModifiedDate());
			this.phone = entity.getPhone();
			this.address = entity.getAddress();
			this.dob = entity.getDob();
			this.email = entity.getEmail();
			this.fullname = entity.getFullname();
			//this.roleId = entity.getRoleId();
			this.role.loadFromEntity(entity.getRole());
			this.password = entity.getPassword();
			this.role.loadFromEntity(entity.getRole());
		}
	}
	
	public void loadFromEntityNotPassword(UserEntity entity) {
		if (entity != null) {
			this.setId(entity.getId());
			this.setCreatedBy(entity.getCreatedBy());
			this.setCreatedDate(entity.getCreatedDate());
			this.setModifiedBy(entity.getModifiedBy());
			this.setModifiedDate(entity.getModifiedDate());
			this.phone = entity.getPhone();
			this.address = entity.getAddress();
			this.dob = entity.getDob();
			this.email = entity.getEmail();
			this.fullname = entity.getFullname();
			this.role.loadFromEntity(entity.getRole());
			this.role.loadFromEntity(entity.getRole());
		}
	}

}
