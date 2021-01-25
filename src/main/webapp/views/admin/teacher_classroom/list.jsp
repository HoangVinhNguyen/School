<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Phân công giáo viên</title>
</head>
<body>

	<div class="main-content">


		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a></li>
					<li class="active">Phân công giáo viên</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>

			<div class="page-content">

				<button id="btnGetTeacherClassroom" type="button" class="btn btn-primary">Xem danh sách</button>
				<button data-toggle="modal" data-target="#add-teacher-modal" id="btnAddTeacherClassroom" type="button" class="btn btn-success">Thêm phân công</button>
				<br/>
				<form id="dataTeacher" method="post" enctype="multipart/form-data">
					<label for="file">Phân công giáo viên:</label>
				    <input name="file" type="file" id="fileTeacher"/>
				    <button>Thêm</button>
				</form>
				<div class="row">
					<div class="col-xs-12">
						<!-- modal button Add New -->
						<div id="add-teacher-modal" class="modal fade" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="smaller lighter blue no-margin">Thêm phân công giáo viên</h3>
									</div>

									<div class="modal-body">
										<form>
											<div class="form-group">
												<label for="txtIDTeacherAdd" class="col-form-label">Mã giáo viên:</label>
													<input type="text" class="form-control"
													id="txtIDTeacherAdd">
											</div>
											<div class="form-group">
												<label for="txtIDStudentAdd" class="col-form-label">Mã học sinh:</label>
												<input class="form-control" id="txtIDStudentAdd">
											</div>
											<div class="form-group">
												<label for="txtIDClassroomAdd" class="col-form-label">Mã lớp học:</label>
												<input class="form-control" id="txtIDClassroomAdd">
											</div>
											<div class="form-group">
												<label for="txtIDCourseAdd" class="col-form-label">Mã môn học:</label>
												<input class="form-control" id="txtIDCourseAdd">
											</div>
										</form>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnAddTacherClassroomModal" type="button"
											class="btn btn-success">Thêm</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- modal button Add New -->

						<!-- modal button Update -->
						<div id="update-teacherclassroom-modal" class="modal fade" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="smaller lighter blue no-margin">Cập nhật thông tin phân công</h3>
									</div>

									<div class="modal-body">
										<form>
											<div class="form-group">
												<label for="txtIDUpdateTeacherClassroom" class="col-form-label">ID:</label>
												<input type="text" class="form-control" id="txtIDUpdateTeacherClassroom"
													disabled>
											</div>
											<div class="form-group">
												<label for="txtIDTeacherUpdate" class="col-form-label">Mã giáo viên:</label>
													<input type="text" class="form-control"
													id="txtIDTeacherUpdate">
											</div>
											<div class="form-group">
												<label for="txtIDStudentUpdate" class="col-form-label">Mã học sinh:</label>
												<input class="form-control" id="txtIDStudentUpdate">
											</div>
											<div class="form-group">
												<label for="txtIDClassroomUpdate" class="col-form-label">Mã lớp học:</label>
												<input class="form-control" id="txtIDClassroomUpdate">
											</div>
											<div class="form-group">
												<label for="txtIDCourseUpdate" class="col-form-label">Mã môn học:</label>
												<input class="form-control" id="txtIDCourseUpdate">
											</div>
										</form>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnUpdateTeacherClassroomModal" type="button"
											class="btn btn-primary">Cập nhật</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- modal button Update -->

						<!-- modal button Delete -->
						<div id="delete-teacherclassroom-modal" class="modal fade" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="smaller lighter blue no-margin">Xóa thông tin phân công</h3>
									</div>

									<div class="modal-body">
										<form>
											<div class="form-group">
												<label for="txtIDDeleteTeacherClassroom" class="col-form-label">ID:</label>
												<input type="text" class="form-control" id="txtIDDeleteTeacherClassroom"
													disabled>
											</div>
											<div class="form-group">
												<label for="txtIDTeacherDelete" class="col-form-label">Mã giáo viên:</label>
													<input type="text" class="form-control"
													id="txtIDTeacherDelete" disabled>
											</div>
											<div class="form-group">
												<label for="txtIDStudentDelete" class="col-form-label">Mã học sinh:</label>
												<input class="form-control" id="txtIDStudentDelete" disabled>
											</div>
											<div class="form-group">
												<label for="txtIDClassroomDelete" class="col-form-label">Mã lớp học:</label>
												<input class="form-control" id="txtIDClassroomDelete" disabled>
											</div>
											<div class="form-group">
												<label for="txtIDCourseDelete" class="col-form-label">Mã môn học:</label>
												<input class="form-control" id="txtIDCourseDelete" disabled>
											</div>
										</form>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnDeleteTeacherClassroomModal" type="button"
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
									<table id="teacherclassroom-table"
										class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th scope="col">#</th>
												<th scope="col">Mã giáo viên</th>
												<th scope="col">Mã học sinh</th>
												<th scope="col">Mã lớp</th>
												<th scope="col">Mã môn</th>
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