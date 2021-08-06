package com.school.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.school.model.RoleModel;
import com.school.model.UserModel;

@Entity
@Table(name="user")
public class UserEntity extends BaseEntity {
	
	private String email;
	private String password;
	private String fullname;
	private Timestamp dob;
	private String phone;
	private String address;
	@Column(name="refresh_token")
	private String refreshToken;
	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private RoleEntity role = new RoleEntity();
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "user_course", 
        joinColumns = { @JoinColumn(name = "user_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
	private Set<CourseEntity> course = new HashSet<CourseEntity>();
	
	@ManyToOne
	@JoinColumn(name = "class_id", nullable = true)
	private ClassInEntity clazz = new ClassInEntity();

	public ClassInEntity getClazz() {
		return clazz;
	}


	public void setClazz(ClassInEntity clazz) {
		this.clazz = clazz;
	}


	public Set<CourseEntity> getCourse() {
		return course;
	}


	public void setCourse(Set<CourseEntity> course) {
		this.course = course;
	}


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
		if (model != null) {
			this.setId(model.getId());
			this.setCreatedBy(model.getCreatedBy());
			this.setCreatedDate(model.getCreatedDate());
			this.setModifiedBy(model.getModifiedBy());
			this.setModifiedDate(model.getModifiedDate());
			this.phone = model.getPhone();
			this.address = model.getAddress();
			this.dob = model.getDob();
			this.email = model.getEmail();
			this.fullname = model.getFullname();
			this.password = model.getPassword();
			// this.roleId = model.getRoleId();
			RoleModel roleModel = new RoleModel();
			if (model.getRoleId() != null) {
				roleModel.setId(model.getRoleId());
			}
			else if (model.getRole() != null) {
				roleModel = model.getRole();
			}
			this.role.loadFromDTO(roleModel);
			if (model.getCourse() != null && !model.getCourse().isEmpty()) {
				model.getCourse().stream().forEach(e -> {
					CourseEntity c = new CourseEntity();
					c.loadFromDTO(e);
					this.course.add(c);
				});

			}
			if (model.getClazz() != null) {
				if (model.getClazz().getName() != null) {
					this.clazz.loadFromDTO(model.getClazz());
				}
			}
		}
	}

}
