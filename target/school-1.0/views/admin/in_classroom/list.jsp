<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Danh sách thành viên</title>
</head>
<body>

	<div class="main-content">


		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a></li>
					<li class="active">Danh sách thành viên</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>

			<div class="page-content">

				<button id="btnGetInClassroom" type="button" class="btn btn-primary">Xem danh sách</button>
				<button data-toggle="modal" data-target="#add-modal" id="btnAddInClassroom" type="button" class="btn btn-success">Thêm học sinh vào lớp học</button>
				<div class="row">
					<div class="col-xs-12">
						<!-- modal button Add New -->
						<div id="add-modal" class="modal fade" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="smaller lighter blue no-margin">Thêm học sinh vào lớp học</h3>
									</div>

									<div class="modal-body">
										<form>
											<div class="form-group">
												<label for="txtAddStudentIDInClassroom" class="col-form-label">Mã học sinh:</label>
													<input type="text" class="form-control"
													id="txtAddStudentIDInClassroom">
											</div>
											<div class="form-group">
												<label for="txtAddClassroomIDInClassroom" class="col-form-label">Mã lớp:</label>
												<input class="form-control" id="txtAddClassroomIDInClassroom">
											</div>
										</form>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnAddInClassroomModal" type="button"
											class="btn btn-success">Thêm</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- modal button Add New -->

						<!-- modal button Update -->
						<div id="update-inclassroom-modal" class="modal fade" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="smaller lighter blue no-margin">Cập nhật thông tin thành viên</h3>
									</div>

									<div class="modal-body">
										<form>
											<div class="form-group">
												<label for="txtIDUpdateInClassroom" class="col-form-label">ID:</label>
												<input type="text" class="form-control" id="txtIDUpdateInClassroom"
													disabled>
											</div>
											<div class="form-group">
												<label for="txtIDStudentInClassroomUpdate" class="col-form-label">Mã học sinh:</label> <input type="text" class="form-control"
													id="txtIDStudentInClassroomUpdate">
											</div>
											<div class="form-group">
												<label for="txtIDClassroomInClassroomUpdate" class="col-form-label">Mã lớp:</label> <input class="form-control" id="txtIDClassroomInClassroomUpdate">
											</div>
										</form>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnUpdateInClassroomModal" type="button"
											class="btn btn-primary">Cập nhật</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- modal button Update -->

						<!-- modal button Delete -->
						<div id="delete-inclassroom-modal" class="modal fade" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="smaller lighter blue no-margin">Xóa thông tin thành viên</h3>
									</div>

									<div class="modal-body">
										<form>
											<div class="form-group">
												<label for="txtIDDeleteInClassroom" class="col-form-label">ID:</label>
												<input type="text" class="form-control" id="txtIDDeleteInClassroom"
													disabled>
											</div>
											<div class="form-group">
												<label for="txtIDStudentInClassroomDelete" class="col-form-label">Tên
													lớp:</label> <input type="text" class="form-control"
													id="txtIDStudentInClassroomDelete" disabled>
											</div>
											<div class="form-group">
												<label for="txtIDClassroomInClassroomDelete" class="col-form-label">Mã
													lớp:</label> <input class="form-control"
													id="txtIDClassroomInClassroomDelete" disabled>
											</div>
										</form>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnDeleteInClassroomModal" type="button"
											class="btn btn-danger">Xóa</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- modal button Delete -->


						<div class="row">
							<div class="col-xs-12">
								<div class="card-body">
									<table id="inclassroom-table"
										class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th scope="col">#</th>
												<th scope="col">Mã học sinh</th>
												<th scope="col">Mã lớp</th>
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