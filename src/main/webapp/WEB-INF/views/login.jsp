<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../../../common/taglib.jsp" %>

<c:url value="/login" var="url"></c:url>
<form method="post" action="${url}">
	<p>Ten</p>
	<input type="text" name="username" />
	<p>Mat khau</p>
	<input type="password" name="password" />
	<button type="submit">Đăng nhập</button>
</form>