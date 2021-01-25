<%@ include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bản điểm</title>
</head>
<body>
	<div class="row">

		<div class="col-lg-3">

			<h1 class="my-4">Môn học</h1>
			<div class="list-group" id="courseTitle">
			</div>

		</div>
		<!-- /.col-lg-3 -->

		<div class="col-lg-9">
			<div class="page-header">
				<h1 id="titleClassroom"></h1>
			</div>
			<!-- /.page-header -->
			
			<div class="row">
							<div class="col-xs-12">
								<div class="card-body">
									<table id="point-table"
										class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th scope="col">#</th>
												<th scope="col">Mã học sinh</th>
												<th scope="col">Mã lớp</th>
												<th scope="col">Điểm</th>
												<th scope="col">Người tạo</th>
												<th scope="col">Ngày tạo</th>
												<th scope="col">Người chỉnh sửa</th>
												<th scope="col">Ngày chỉnh sửa</th>
											</tr>
										</thead>
										<tbody id="customer_container">
										</tbody>
									</table>
								</div>
							</div>
						</div>

		</div>
		<!-- /.col-lg-9 -->

	</div>
</body>
</html>