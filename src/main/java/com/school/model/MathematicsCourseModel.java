package com.school.model;

public class MathematicsCourseModel extends AbstractModel<MathematicsCourseModel>{

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
