<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../../common/taglib.jsp"%>

<tiles:importAttribute name="javascripts" />
<%-- <tiles:importAttribute name="stylesheets"/> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ admin</title>
	<link rel="stylesheet" href="<c:url value='/resource/template/admin/bootstrap/css/bootstrap.min.css'/>" />
	<link rel="stylesheet" href="<c:url value='/resource/template/admin/css/custom.css'/>" />
	<script src="<c:url value='/resource/template/admin/bootstrap/js/bootstrap.min.js'/>"></script>
	<script src="<c:url value='/resource/template/admin/bootstrap/js/bootstrap.bundle.min.js'/>"></script>
	<script src="<c:url value='/resource/template/admin/bootstrap/js/jquery-3.6.0.min.js'/>"></script>
	<script src="<c:url value='/resource/template/admin/js/custom.js'/>"></script>
</head>
<body class="container">

	<div class="row">
		<!-- header -->
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
		<!-- header -->
	</div>

	<div id="layout-menu" class="row">
		<div class="col nopadding">
			<!-- menu -->
			<tiles:insertAttribute name="menu"></tiles:insertAttribute>
			<!-- menu -->
		</div>
		<div class="col-9">
			<!-- body -->
			<tiles:insertAttribute name="body"></tiles:insertAttribute>
			<!-- body -->
		</div>
	</div>

	<div class="row">
		<!-- footer -->
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
		<!-- footer -->
	</div>
	<!-- page specific plugin scripts -->
	<c:forEach var="js" items="${javascripts}">
		<script type="text/javascript" src="<c:url value='${js}'/>"></script>
	</c:forEach>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
</body>
</html>