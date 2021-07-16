package com.school.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.school.model.UserModel;

@Entity(name="user")
public class UserEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	private String fullname;
	private Timestamp dob;
	private String address;
	@Column(name="roled_id")
	private Long roledId;
	@Column(name="is_deleted")
	private int isDeleted;
	@Transient
	private RoleEntity role = new RoleEntity();

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

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public void loadFromDTO(UserModel model) {
		this.setId(model.getId());
		this.setCreatedBy(model.getCreatedBy());
		this.setCreatedDate(model.getCreatedDate());
		this.setModifiedBy(model.getModifiedBy());
		this.setModifiedDate(model.getModifiedDate());
		this.address = model.getAddress();
		this.dob = model.getDob();
		this.email = model.getEmail();
		this.fullname = model.getFullname();
		this.role.loadFromDTO(model.getRole());
	}

}
