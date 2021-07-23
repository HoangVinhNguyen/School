<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../../common/taglib.jsp" %>

<security:authorize access="isAuthenticated()">
	<security:authentication property="principal" var="user" />
	Welcome, ${user.username }
	<a href="<c:url value='/logout-check'/>">Thoat</a>
</security:authorize>

<p>Menu</p>
<p><a href="<c:url value='/home'/>">Trang chủ</a> > Đăng nhập</p>
