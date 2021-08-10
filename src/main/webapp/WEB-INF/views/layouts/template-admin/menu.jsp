<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../../common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<div id="menu" class="d-flex flex-column flex-shrink-0 p-3">
	<div
		class="d-flex align-items-centerlink-dark">
		<span class="fs-4">Danh mục quản lí</span>
	</div>
	<hr>
	<ul class="nav nav-pills flex-column mb-auto">
		<li class="nav-item"><a class="nav-link link-dark"
			href='<c:url value="/admin/user"/>'> Quản lí tài khoản</a></li>
		<li class="nav-item"><a class="nav-link link-dark"
			href='<c:url value="/admin/inclassroom"/>'>Quản lí lớp học </a></li>
		<li class="nav-item"><a class="nav-link link-dark"
			href='<c:url value="/admin/teacherclassroom"/>'>Phân công giáo
				viên </a></li>
		<li class="nav-item"><a class="nav-link link-dark"
			href='<c:url value="/admin/userCourse"/>'>Quản lí môn học </a></li>
		<li class="nav-item"><a class="nav-link link-dark"
			href='<c:url value="/admin/course"/>'>Danh sách môn học </a></li>
		<li class="nav-item"><a class="nav-link link-dark"
			id="getClassroom" href='<c:url value="/admin/classroom"/>'>Danh
				sách phòng học </a></li>
		<li class="nav-item"><a class="nav-link link-dark" id="getClass"
			href='<c:url value="/admin/class"/>'>Danh sách lớp học </a></li>
		<li class="nav-item"><a class="nav-link link-dark" id="getGrade"
			href='<c:url value="/admin/grade"/>'>Danh sách cấp lớp học </a></li>
		<li class="nav-item"><a class="nav-link link-dark"
			id="getLevelGrade" href='<c:url value="/admin/level-grade"/>'>
				Danh sách bậc học </a></li>
	</ul>
</div>
