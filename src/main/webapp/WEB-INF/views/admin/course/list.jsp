<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<div class="main-content">


		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a></li>
					<li class="active">Danh sách môn học</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>

			<div class="page-content">

				<button id="btnGetListUser" type="button" class="btn btn-primary">Xem danh sách</button>
				<button data-toggle="modal" data-target="#add-course-modal" id="btnAddUser" type="button" class="btn btn-success">Thêm môn học</button>
				<div class="row">
					<div class="col-xs-12">
						<!-- modal button Add New -->
						<div id="add-course-modal" class="modal fade" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="smaller lighter blue no-margin">Thêm mới một môn học</h3>
									</div>

									<div class="modal-body">
										<form>
											<div class="form-group">
												<label for="txtNameCourseAdd" class="col-form-label">Tên môn học:</label>
													<input type="text" class="form-control"
													id="txtNameCourseAdd">
											</div>
											<div class="form-group">
												<label for="txtCodeCourseAdd" class="col-form-label">Mã môn học:</label>
												<input class="form-control" id="txtCodeCourseAdd">
											</div>
										</form>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnAddCourseModal" type="button"
											class="btn btn-success">Thêm</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- modal button Add New -->

						<!-- modal button Update -->
						<div id="update-course-modal" class="modal fade" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="smaller lighter blue no-margin">Cập nhật thông tin môn học</h3>
									</div>

									<div class="modal-body">
										<form>
											<div class="form-group">
												<label for="txtIDCourseUpdate" class="col-form-label">ID:</label>
												<input type="text" class="form-control" id="txtIDCourseUpdate"
													disabled>
											</div>
											<div class="form-group">
												<label for="txtNameCourseUpdate" class="col-form-label">Tên môn học:</label>
													<input type="text" class="form-control"
													id="txtNameCourseUpdate">
											</div>
											<div class="form-group">
												<label for="txtCodeCourseUpdate" class="col-form-label">Mã môn học:</label>
												<input class="form-control" id="txtCodeCourseUpdate">
											</div>
										</form>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnUpdateCourseModal" type="button"
											class="btn btn-primary">Cập nhật</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- modal button Update -->

						<!-- modal button Delete -->
						<div id="delete-Course-modal" class="modal fade" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="smaller lighter blue no-margin">Xóa thông tin môn học</h3>
									</div>

									<div class="modal-body">
										<form>
											<div class="form-group">
												<label for="txtIDCourseDelete" class="col-form-label">ID:</label>
												<input type="text" class="form-control" id="txtIDCourseDelete"
													disabled>
											</div>
											<div class="form-group">
												<label for="txtNameCourseDelete" class="col-form-label">Tên môn học:</label>
													<input type="text" class="form-control"
													id="txtNameCourseDelete" disabled>
											</div>
											<div class="form-group">
												<label for="txtCodeCourseDelete" class="col-form-label">Mã môn học:</label>
												<input class="form-control" id="txtCodeCourseDelete" disabled>
											</div>
										</form>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnDeleteCourseModal" type="button"
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
									<table id="course-table"
										class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th scope="col">#</th>
												<th scope="col">Tên môn học</th>
												<th scope="col">Mã môn học</th>
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
	