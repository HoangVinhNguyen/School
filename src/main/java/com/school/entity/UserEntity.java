package com.school.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.school.model.RoleModel;
import com.school.model.UserModel;

@Entity
@Table(name="user")
public class UserEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	private String fullname;
	private Timestamp dob;
	private String address;
	@Column(name="refresh_token")
	private String refreshToken;
//	@Column(name="role_id")
//	private Long roleId;
	@OneToOne
	@JoinColumn(name = "role_id", nullable = false)
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


	public String getFullname() {
		return fullname;
	}


	public void setFullname(String fullname) {
		this.fullname = fullname;
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


	public String getRefreshToken() {
		return refreshToken;
	}


	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}


//	public Long getRoleId() {
//		return roleId;
//	}
//
//
//	public void setRoleId(Long roleId) {
//		this.roleId = roleId;
//	}


	public RoleEntity getRole() {
		return role;
	}


	public void setRole(RoleEntity role) {
		this.role = role;
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
		this.password = model.getPassword();
		//this.roleId = model.getRoleId();
		RoleModel role = new RoleModel();
		role.setId(model.getRoleId());
		this.role.loadFromDTO(role);
	}

}
