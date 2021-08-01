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
				<button data-toggle="modal" data-target="#add-course-modal" id="btnAddCourse" type="button" class="btn btn-success">Thêm môn học</button>
				<button data-toggle="modal" data-target="#add-course-file" id="btnAddCourseFile" type="button" class="btn btn-success">Thêm danh sách môn học</button>
				<hr>
				<button id="btnGetCourseForm" type="button" class="btn btn-primary">Lấy mẫu Microsoft Excel</button>
				<button id="btnGetCourseExcel" type="button" class="btn btn-primary">Lấy nội dung của bảng</button>
				<hr>
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
												<label for="txtNameAddCourse" class="col-form-label">Tên môn học</label>
													<input type="text" class="form-control"
													id="txtNameAddCourse">
											</div>
											<div class="form-group">
												<label for="txtCodeAddCourse" class="col-form-label">Mã môn học</label>
												<input class="form-control" id="txtCodeAddCourse">
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
												<label for="txtIDUpdateCourse" class="col-form-label">ID</label>
												<input type="text" class="form-control" id="txtIDUpdateCourse"
													disabled>
											</div>
											<div class="form-group">
												<label for="txtNameUpdateCourse" class="col-form-label">Tên môn học</label>
													<input type="text" class="form-control"
													id="txtNameUpdateCourse">
											</div>
											<div class="form-group">
												<label for="txtCodeUpdateCourse" class="col-form-label">Mã môn học</label>
												<input class="form-control" id="txtCodeUpdateCourse">
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
						<div id="delete-course-modal" class="modal fade" tabindex="-1">
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
												<label for="txtIDDeleteCourse" class="col-form-label">ID</label>
												<input type="text" class="form-control" id="txtIDDeleteCourse"
													disabled>
											</div>
											<div class="form-group">
												<label for="txtNameDeleteCourse" class="col-form-label">Tên môn học</label>
													<input type="text" class="form-control"
													id="txtNameDeleteCourse" disabled>
											</div>
											<div class="form-group">
												<label for="txtCodeDeleteCourse" class="col-form-label">Mã môn học</label>
												<input class="form-control" id="txtCodeDeleteCourse" disabled>
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
						
						<!-- modal button Add New with File -->
						<div id="add-course-file" class="modal fade" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="smaller lighter blue no-margin">Thêm danh sách môn học</h3>
									</div>

									<div class="modal-body">
										<form id="dataCourse" method="post" enctype="multipart/form-data">
											<button id="openSendFileCourse" type="button" class="btn btn-primary">
											Chọn tập tin</button>
										    <input name="file" type="file" id="fileCourse" style="display:none;" />
										</form>
										<div class="form-group">
											<label for="fileNameCourse" class="col-form-label">Tập tin đã chọn</label>
											<input class="form-control" id="fileNameCourse" placeholder="*.xls, *xlsx" disabled>
										</div>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnAddCourseList" type="button"
											class="btn btn-success">Thêm</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- modal button Add New with File -->

						<div class="row">
							<div class="col-xs-12">
								<div class="card-body">
								<h1>BẢNG DANH SÁCH MÔN HỌC</h1>
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
	