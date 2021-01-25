<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Đăng nhập</title>
</head>
<body>
	<div class="container h-100">
		<div class="row align-items-center h-100">
			<aside class="col-sm-4 mx-auto">
				<!-- <h1 class="form-heading">login Form</h1> -->
				<div class="card">
					<article class="card-body">
						<h4 class="card-title text-center mb-4 mt-1">Đăng nhập</h4>
						<hr>
						<p class="text-success text-center">
							<c:if test="${not empty message}">
								<div class="alert alert-${alert}">
										${message}
								</div>
							</c:if>
						</p>
						<form action="<c:url value='/login'/>" id="formLogin" method="post">
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i class="fa fa-user"></i>
										</span>
									</div>
									<input class="form-control" placeholder="Email for login"
										type="text" id="email" name="email">
								</div>
								<!-- input-group.// -->
							</div>
							<!-- form-group// -->
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i class="fa fa-lock"></i>
										</span>
									</div>
									<input class="form-control" placeholder="******" type="password"
									id="password" name="password">
								</div>
								<!-- input-group.// -->
							</div>
							<!-- form-group// -->
							<div class="form-group">
							<input type="hidden" value="login" name="action"/>
								<button type="submit" class="btn btn-primary btn-block">
									Đăng nhập</button>
							</div>
							<!-- form-group// -->
							<p class="text-center">
								<a href="#" class="btn">Forgot password?</a>
							</p>
						</form>
					</article>
				</div>
				<!-- card.// -->
			</aside>
		</div>
	</div>
</body>
</html>