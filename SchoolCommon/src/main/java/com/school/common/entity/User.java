package com.school.common.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.school.common.common.ResourceGet;
import com.school.common.common.SystemConstant;
import com.school.common.dto.UserDto;
import com.school.common.dto.UserSavePointDto;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

	private static final long serialVersionUID = 6655451999701501635L;
	
	@Column(name = "first_name", length = 45, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 45, nullable = false)
	private String lastName;

	@Column(length = 128, nullable = false, unique = true)
	private String email;

	@Column(nullable = true)
	private String phone;

	@Column(length = 64, nullable = false)
	private String password;

	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;

	@Column(nullable = true)
	private String address;

	@Column(nullable = true)
	private String photos;

	@Column(nullable = false)
	private boolean enabled;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
	private Set<Clazz> clazzes = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_courses", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "course_id"))
	private Set<Course> courses = new HashSet<>();

	public User() {

	}

	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Clazz> getClazzes() {
		return clazzes;
	}

	public void setClazzes(Set<Clazz> clazzes) {
		this.clazzes = clazzes;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	@Transient
	public void addRole(Role role) {
		Optional<Role> roleOp = Optional.ofNullable(role);
		if (roleOp.isPresent()) {
			Role r = roleOp.get().clone();
			this.roles.add(r);
		}
	}
	
	@Transient
	public void addCourse(Course course) {
		Optional<Course> op = Optional.ofNullable(course);
		if (op.isPresent()) {
			this.courses.add(op.get());
		}
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
		User other = (User) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		return true;
	}

	@Transient
	public String getPhotosImagePath() {
		if (this.getId() == null || this.getPhotos() == null)
			return SystemConstant.PATH_IMG_DEFAULT;
		ResourceGet getImagePath = new ResourceGet();
		return getImagePath.getUserImagePathWithId(this.getId(), this.getPhotos());
	}

	@Override
	public String toString() {
		StringBuilder printUser = new StringBuilder();
		printUser.append("[");
		printUser.append(getId());
		printUser.append(", ");
		printUser.append(getEmail());
		printUser.append(", ");
		printUser.append(getFirstName());
		printUser.append(", ");
		printUser.append(getLastName());
		printUser.append(", ");
		printUser.append(isEnabled());
		printUser.append("]");

		return printUser.toString();
	}

	@Transient
	public String getFullName() {
		StringBuilder fullname = new StringBuilder(getLastName());
		fullname.append(" ");
		fullname.append(getFirstName());
		return fullname.toString();
	}

	@Transient
	public User clone() {
		User user = new User();
		user.setId(getId());
		user.setDob(getDob());
		user.setEmail(getEmail());
		user.setEnabled(isEnabled());
		user.setFirstName(getFirstName());
		user.setLastName(getLastName());
		user.setPhotos(getPhotos());
		user.setAddress(getAddress());
		user.setPhone(getPhone());
		user.setRoles(getRoles());
		user.setDeleted(this.isDeleted());
		user.setCreatedBy(this.getCreatedBy());
		user.setCreatedDate(this.getCreatedDate());
		user.setModifiedBy(this.getModifiedBy());
		user.setModifiedDate(this.getModifiedDate());
		return user;
	}

	@Transient
	public static User convertToUser(UserDto userDto) {
		Optional<UserDto> op = Optional.ofNullable(userDto);
		if (op.isPresent()) {
			UserDto u = op.get();
			User user = new User();
			user.setId(u.getId());
			user.firstName = u.getFirstName();
			user.lastName = u.getLastName();
			user.email = u.getEmail();
			user.dob = u.getDob();
			user.address = u.getAddress();
			user.phone = u.getPhone();
			user.photos = u.getPhotos();
			return user;
		}
		return null;
	}

	@Transient
	public static User convertToUser(UserSavePointDto userDto) {
		Optional<UserSavePointDto> op = Optional.ofNullable(userDto);
		if (op.isPresent()) {
			UserSavePointDto u = op.get();
			User user = new User();
			user.setId(u.getId());
			user.firstName = u.getFirstName();
			user.lastName = u.getLastName();
			user.email = u.getEmail();
			user.dob = u.getDob();
			user.address = u.getAddress();
			user.phone = u.getPhone();
			user.photos = u.getPhotos();
			return user;
		}
		return null;
	}

}
