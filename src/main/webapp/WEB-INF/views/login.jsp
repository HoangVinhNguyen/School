<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../../../common/taglib.jsp" %>

<c:url value="/login-check" var="url"></c:url>
<form method="POST" action="${url}">
	<p>Ten</p>
	<input type="text" name="username" />
	<p>Mat khau</p>
	<input type="password" name="password" />
	<button type="submit">Đăng nhập</button>
</form>