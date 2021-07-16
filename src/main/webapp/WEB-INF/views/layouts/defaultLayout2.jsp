<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>2222</title>

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