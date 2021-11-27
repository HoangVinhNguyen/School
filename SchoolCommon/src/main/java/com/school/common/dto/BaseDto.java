package com.school.common.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Id;

public class BaseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private boolean isDeleted;

	protected BaseDto() {

	}

	protected BaseDto(Long id, boolean isDeleted) {
		this.id = id;
		this.isDeleted = isDeleted;
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

}
