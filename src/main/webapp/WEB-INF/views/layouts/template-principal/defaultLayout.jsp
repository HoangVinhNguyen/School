<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../../common/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ admin</title>
	<link rel="stylesheet" href="<c:url value='/resource/template/principal/assets/css/bootstrap.min.css' />" />
    <link rel="stylesheet" href="<c:url value='/resource/template/principal/font-awesome/4.5.0/css/font-awesome.min.css' />" />
    <link rel="stylesheet" href="<c:url value='/resource/template/principal/assets/css/ace.min.css' />" class="ace-main-stylesheet" id="main-ace-style" />
    <link rel="stylesheet" href="<c:url value='/resource/template/principal/assets/css/ace-rtl.min.css' />" />
    <link rel="stylesheet" href="<c:url value='/resource/template/principal/assets/css/ace-skins.min.css' />" />
    <script src="<c:url value='/resource/template/principal/assets/js/ace-extra.min.js' />"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type='text/javascript' src='<c:url value="/resource/template/principal/js/jquery-2.2.3.min.js" />'></script>
    <script src="<c:url value='/resource/template/principal/assets/js/jquery.2.1.1.min.js' />"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="<c:url value='/resource/template/paging/jquery.twbsPagination.js' />"></script>
    <link rel="stylesheet" href="<c:url value='/resource/template/principal/assets/fonts/fonts.googleapis.com.css' />" />
    
    <link rel="stylesheet" href="<c:url value='/resource/template/principal/css/custom.css'/>" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.16.2/xlsx.full.min.js"></script>
</head>
<body class="no-skin">


	<!-- header -->
	<tiles:insertAttribute name="header"></tiles:insertAttribute>
	<!-- header -->

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
			try {
				ace.settings.loadState('main-container')
			} catch (e) {
			}
		</script>
		<!-- menu -->
		<tiles:insertAttribute name="menu"></tiles:insertAttribute>
		<!-- menu -->

		<!-- body -->
		<tiles:insertAttribute name="body"></tiles:insertAttribute>
		<!-- body -->

		<!-- footer -->
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
		<!-- footer -->

		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse display"> <i
			class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>
	</div>

	<script src="<c:url value='/resource/template/principal/assets/js/jquery.dataTables.min.js'/>" ></script>
	<script src="<c:url value='/resource/template/principal/assets/js/jquery.dataTables.bootstrap.min.js'/>" ></script>
	<script src="<c:url value='/resource/template/principal/assets/js/dataTables.tableTools.min.js'/>" ></script>
	<script src="<c:url value='/resource/template/principal/assets/js/dataTables.buttons.min.js'/>" ></script>
	
	<script src="<c:url value='/resource/template/principal/assets/js/bootstrap.min.js' />"></script>
	<script src="<c:url value='/resource/template/principal/assets/js/jquery-ui.custom.min.js' />"></script>
	<script src="<c:url value='/resource/template/principal/assets/js/jquery.ui.touch-punch.min.js' />"></script>
	<script src="<c:url value='/resource/template/principal/assets/js/jquery.easypiechart.min.js' />"></script>
	<script src="<c:url value='/resource/template/principal/assets/js/jquery.sparkline.min.js' />"></script>
	<script src="<c:url value='/resource/template/principal/assets/js/jquery.flot.min.js' />"></script>
	<script src="<c:url value='/resource/template/principal/assets/js/jquery.flot.pie.min.js' />"></script>
	<script src="<c:url value='/resource/template/principal/assets/js/jquery.flot.resize.min.js' />"></script>
	<script src="<c:url value='/resource/template/principal/assets/js/ace-elements.min.js' />"></script>
	<script src="<c:url value='/resource/template/principal/assets/js/ace.min.js' />"></script>
	
	<!-- page specific plugin scripts -->
	
	<script src="<c:url value='/resource/template/principal/assets/js/jquery-ui.min.js'/>" ></script>
	<script type="text/javascript" src="<c:url value='/resource/template/principal/js/custom.js'/>"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
</body>
</html>