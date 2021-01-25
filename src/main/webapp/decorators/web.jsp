<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title><dec:title default="Shop Homepage - Start Bootstrap Template"/></title>
	
	<!-- Bootstrap core CSS -->
	<link href="<c:url value = '/template/web/bootstrap/css/bootstrap.min.css'/>"
		rel="stylesheet" type="text/css" media="all" />
	<!-- Custom styles for this template -->
	<link href="<c:url value = '/template/web/css/style.css'/>" rel="stylesheet" type="text/css"
		media="all" />
	<link href="//cdn.datatables.net/1.10.23/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" media="all" />
		
</head>
<body>
	<!-- header -->
	<%@include file="/common/web/header.jsp" %>
	<!-- header -->
	
	<div class="container">
		<dec:body/>
	</div>
	
	<!-- footer -->
	<%@include file="/common/web/footer.jsp" %>
	<!-- footer -->

	<!-- Bootstrap core JavaScript -->
	<script type="text/javascript" src="<c:url value='/template/web/jquery/jquery.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value= '/template/web/bootstrap/js/bootstrap.bundle.min.js'/>"></script>
	<script type="text/javascript" src="//cdn.datatables.net/1.10.23/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
	<script type="text/javascript" src="<c:url value='/template/web/js/custom.js'/>"></script>
</body>
</html>