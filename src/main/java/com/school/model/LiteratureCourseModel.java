package com.school.model;

public class LiteratureCourseModel extends AbstractModel<LiteratureCourseModel> {

	private Long userId;
	private Double point;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getPoint() {
		return point;
	}

	public void setPoint(Double point) {
		this.point = point;
	}
}
