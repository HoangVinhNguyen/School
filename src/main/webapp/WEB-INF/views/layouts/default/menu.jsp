<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<security:authorize access="isAuthenticated()">
	<security:authentication property="principal" var="user" />
	Welcome, ${user.username }
	<a href="<c:url value='/dang-xuat'/>">Thoat</a>
</security:authorize>

<p>Menu</p>
<p><a href="<c:url value='/home'/>">Trang chủ</a> > Đăng nhập</p>
