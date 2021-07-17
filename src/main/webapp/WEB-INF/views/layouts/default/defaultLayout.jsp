<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>

<link rel="stylesheet" href="<c:url value='/resource/css/style.css'/>">
<script type="text/javascript" src="<c:url value='/resource/js/main.js'/>"></script>
</head>
<body>
	<div id="root">
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
		<tiles:insertAttribute name="menu"></tiles:insertAttribute>
		<tiles:insertAttribute name="body"></tiles:insertAttribute>
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</div>
</body>
</html>