<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nhập điểm</title>
</head>
<body>

	<div class="main-content">


		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a></li>
					<li class="active" >Nhập điểm</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>

			<div class="page-content">
				<div class="page-header">
					<h1 id="liTitleCourse">
					</h1>
				</div><!-- /.page-header -->

				<div class="btn-group">
					<button data-toggle="dropdown"
						class="btn btn-success btn-lg dropdown-toggle">
						Chọn Lớp <i class="ace-icon fa fa-angle-down icon-on-right"></i>
					</button>

					<ul id="listMyClassroom" class="dropdown-menu dropdown-success dropdown-menu-right">
					</ul>
				</div>
				<!-- /.btn-group -->
				<!-- <button id="btnGetInClassroom" type="button" class="btn btn-primary">Xem
					danh sách</button>
				<button data-toggle="modal" data-target="#add-modal"
					id="btnAddInClassroom" type="button" class="btn btn-success">Thêm
					học sinh vào lớp học</button> -->
				<div class="row">
					<div class="col-xs-12">
					
						<!-- modal button Update -->
						<div id="update-point-modal" class="modal fade"
							tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="smaller lighter blue no-margin">Cập nhật điểm</h3>
									</div>

									<div class="modal-body">
										<form>
											<div class="form-group">
												<label for="txtIDUpdatePoint" class="col-form-label">ID:</label>
												<input type="text" class="form-control"
													id="txtIDUpdatePoint" disabled>
											</div>
											<div class="form-group">
												<label for="txtIDStudentPointUpdate"
													class="col-form-label">Mã học sinh:</label> <input
													type="text" class="form-control"
													id="txtIDStudentPointUpdate" disabled>
											</div>
											<div class="form-group">
												<label for="txtIDClassroomPointUpdate"
													class="col-form-label">Mã lớp:</label> <input
													class="form-control" id="txtIDClassroomPointUpdate" disabled>
											</div>
											<div class="form-group">
												<label for="txtPointUpdate"
													class="col-form-label">Điểm:</label> <input
													class="form-control" id="txtPointUpdate">
											</div>
										</form>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnUpdatePointModal" type="button"
											class="btn btn-primary">Cập nhật</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- modal button Update -->

						<div class="row">
							<div class="col-xs-12">
								<div class="card-body">
									<table id="point-literature-table"
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
												<th></th>
											</tr>
										</thead>
										<tbody id="customer_container">
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>