<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Điểm môn Ngữ Văn</title>
</head>
<body>

	<div class="main-content">


		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a></li>
					<li class="active">Điểm môn Ngữ Văn</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>

			<div class="page-content">

				<button id="btnGetLiteratureCourse" type="button" class="btn btn-primary">Xem danh sách điểm</button>
				<button data-toggle="modal" data-target="#add-literatureCourse-modal" id="btnAddLiteratureCourse" type="button" class="btn btn-success">Nhập điểm</button>
				<div class="row">
					<div class="col-xs-12">
						<!-- modal button Add New -->
						<div id="add-literatureCourse-modal" class="modal fade" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="smaller lighter blue no-margin">Nhập điểm</h3>
									</div>

									<div class="modal-body">
										<form>
											<div class="form-group">
												<label for="txtAddStudentIDLiteratureCourse" class="col-form-label">Mã học sinh:</label>
													<input type="text" class="form-control"
													id="txtAddStudentIDLiteratureCourse">
											</div>
											<div class="form-group">
												<label for="txtAddPointLiteratureCourse" class="col-form-label">Điểm:</label>
												<input class="form-control" id="txtAddPointLiteratureCourse">
											</div>
										</form>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnAddLiteratureCourseModal" type="button"
											class="btn btn-success">Thêm</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- modal button Add New -->

						<!-- modal button Update -->
						<div id="update-literatureCourse-modal" class="modal fade" tabindex="-1">
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
												<label for="txtIDUpdateLiteratureCourse" class="col-form-label">ID:</label>
												<input type="text" class="form-control" id="txtIDUpdateLiteratureCourse"
													disabled>
											</div>
											<div class="form-group">
												<label for="txtIDStudentLiteratureCourseUpdate" class="col-form-label">Mã học sinh:</label> <input type="text" class="form-control"
													id="txtIDStudentLiteratureCourseUpdate">
											</div>
											<div class="form-group">
												<label for="txtIDClassroomLiteratureCourseUpdate" class="col-form-label">Điểm:</label> <input class="form-control" id="txtIDClassroomInClassroomUpdate">
											</div>
										</form>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnUpdateLiteratureCourseModal" type="button"
											class="btn btn-primary">Cập nhật</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- modal button Update -->

						<!-- modal button Delete -->
						<div id="delete-literatureCourse-modal" class="modal fade" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="smaller lighter blue no-margin">Xóa thông tin học sinh</h3>
									</div>

									<div class="modal-body">
										<form>
											<div class="form-group">
												<label for="txtIDDeleteLiteratureCourse" class="col-form-label">ID:</label>
												<input type="text" class="form-control" id="txtIDDeleteLiteratureCourse"
													disabled>
											</div>
											<div class="form-group">
												<label for="txtIDStudentLiteratureCourseDelete" class="col-form-label">Mã học sinh:</label> <input type="text" class="form-control"
													id="txtIDStudentLiteratureCourseDelete" disabled>
											</div>
											<div class="form-group">
												<label for="txtLiteratureCoursePointDelete" class="col-form-label">Điểm:</label> <input class="form-control"
													id="txtLiteratureCoursePointDelete" disabled>
											</div>
										</form>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnDeleteLiteratureCourseModal" type="button"
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
									<table id="literatureCourse-table"
										class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th scope="col">#</th>
												<th scope="col">Mã học sinh</th>
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