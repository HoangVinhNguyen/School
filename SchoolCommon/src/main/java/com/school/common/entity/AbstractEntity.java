package com.school.common.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5502798196050326341L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "is_deleted", columnDefinition = "boolean default false", nullable = false)
	private boolean isDeleted;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@Column(name = "modified_by", nullable = true)
	private String modifiedBy;

	@Column(name = "created_date", nullable = false)
	private LocalDateTime createdDate;

	@Column(name = "modified_date", nullable = true)
	private LocalDateTime modifiedDate;
	
	

	protected AbstractEntity() {
		super();
	}

	protected AbstractEntity(Integer id, boolean isDeleted, String createdBy, String modifiedBy, LocalDateTime createdDate,
			LocalDateTime modifiedDate) {
		this.id = id;
		this.isDeleted = isDeleted;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
