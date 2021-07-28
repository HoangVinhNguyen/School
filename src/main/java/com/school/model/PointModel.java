package com.school.model;

import com.school.entity.PointEntity;

public class PointModel extends BaseModel {

	private UserModel teacherModel = new UserModel();
	private UserModel studentModel = new UserModel();
	private ClassroomModel classroomModel = new ClassroomModel();
	private CourseModel courseModel = new CourseModel();
	private Double point;


	public UserModel getTeacherModel() {
		return teacherModel;
	}

	public void setTeacherModel(UserModel teacherModel) {
		this.teacherModel = teacherModel;
	}

	public UserModel getStudentModel() {
		return studentModel;
	}

	public void setStudentModel(UserModel studentModel) {
		this.studentModel = studentModel;
	}

	public ClassroomModel getClassroomModel() {
		return classroomModel;
	}

	public void setClassroomModel(ClassroomModel classroomModel) {
		this.classroomModel = classroomModel;
	}

	public CourseModel getCourseModel() {
		return courseModel;
	}

	public void setCourseModel(CourseModel courseModel) {
		this.courseModel = courseModel;
	}

	public Double getPoint() {
		return point;
	}

	public void setPoint(Double point) {
		this.point = point;
	}
	
	public void loadFromEntity(PointEntity entity) {
		if (entity != null) {
			this.setId(entity.getId());
			this.classroomModel.loadFromEntity(entity.getClassroomEntity());
			this.courseModel.loadFromEntity(entity.getCourseEntity());
			this.studentModel.loadFromEntityNotPassword(entity.getStudentEntity());
			this.teacherModel.loadFromEntityNotPassword(entity.getTeacherEntity());
			this.point = entity.getPoint();
			this.setId(entity.getId());
			this.setCreatedBy(entity.getCreatedBy());
			this.setCreatedDate(entity.getCreatedDate());
			this.setModifiedBy(entity.getModifiedBy());
			this.setModifiedDate(entity.getModifiedDate());
		}
	}
	
}
