package com.school.common.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Id;

public class BaseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4795901314802324266L;
	private Long id;
	private boolean isDeleted;
	private String createdBy;
	private String modifiedBy;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;

	protected BaseDto() {

	}

	protected BaseDto(Long id, boolean isDeleted, String createdBy, String modifiedBy, LocalDateTime createdDate,
			LocalDateTime modifiedDate) {
		this.id = id;
		this.isDeleted = isDeleted;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
