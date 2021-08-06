<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<div class="main-content">


		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a></li>
					<li class="active">Danh sách tài khoản</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>

			<div class="page-content">

				<button id="btnGetListuserCourse" type="button" class="btn btn-primary">Xem danh sách</button>
				<button data-toggle="modal" data-target="#add-userCourse-modal" id="btnAdduserCourse" type="button" class="btn btn-success">Thêm mới</button>
				<button data-toggle="modal" data-target="#add-userCourse-file" id="btnAdduserCourseFile" type="button" class="btn btn-success">Thêm danh sách tài khoản</button>
				<br/>
				
				<div class="row">
					<div class="col-xs-12">
						<!-- modal button Add New -->
						<div id="add-userCourse-modal" class="modal fade" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="smaller lighter blue no-margin">Thêm mới</h3>
									</div>

									<div class="modal-body">
										<form>
											<div class="form-group">
												<label for="txtEmailuserCourseAdd" class="col-form-label">Email:</label>
													<input type="text" class="form-control"
													id="txtEmailuserCourseAdd">
											</div>
											
										</form>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnAdduserCourseModal" type="button"
											class="btn btn-success">Thêm</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- modal button Add New -->

						<!-- modal button Update -->
						<div id="update-userCourse-modal" class="modal fade" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="smaller lighter blue no-margin">Cập nhật thông tin tài khoản</h3>
									</div>

									<div class="modal-body">
										<form>
											<div class="form-group">
												<label for="txtIDuserCourseUpdate" class="col-form-label">ID:</label>
												<input type="text" class="form-control" id="txtIDuserCourseUpdate"
													disabled>
											</div>
											<div class="form-group">
												<label for="txtEmailuserCourseUpdate" class="col-form-label">Email</label>
													<input type="text" class="form-control"
													id="txtEmailuserCourseUpdate">
											</div>
											<div class="form-group">
												<label for="txtFullnameUpdate" class="col-form-label">Họ tên</label>
												<input class="form-control" id="txtFullnameUpdate">
											</div>
											<div class="form-group">
												<label for="txtDobUpdate" class="col-form-label">Ngày sinh</label>
												<input class="form-control" id="txtDobUpdate">
											</div>
											<div class="form-group">
												<label for="txtPhoneUpdate" class="col-form-label">Số điện thoại</label>
												<input class="form-control" id="txtPhoneUpdate">
											</div>
											<div class="form-group">
												<label for="txtAddressUpdate" class="col-form-label">Địa chỉ</label>
												<input class="form-control" id="txtAddressUpdate">
											</div>
											<div class="form-group">
												<label for="txtRoleUpdate" class="col-form-label">Quyền tài khoản</label>
												<input class="form-control" id="txtRoleUpdate">
											</div>
										</form>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnUpdateuserCourseModal" type="button"
											class="btn btn-primary">Cập nhật</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- modal button Update -->

						<!-- modal button Delete -->
						<div id="delete-userCourse-modal" class="modal fade" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="smaller lighter blue no-margin">Xóa thông tin tài khoản</h3>
									</div>

									<div class="modal-body">
										<form>
											<div class="form-group">
												<label for="txtIDuserCourseDelete" class="col-form-label">ID:</label>
												<input type="text" class="form-control" id="txtIDuserCourseDelete"
													disabled>
											</div>
											<div class="form-group">
												<label for="txtEmailuserCourseDelete" class="col-form-label">Email</label>
													<input type="text" class="form-control"
													id="txtEmailuserCourseDelete" disabled>
											</div>
											<div class="form-group">
												<label for="txtFullnameDelete" class="col-form-label">Họ tên</label>
												<input class="form-control" id="txtFullnameDelete" disabled>
											</div>
											<div class="form-group">
												<label for="txtDobDelete" class="col-form-label">Ngày sinh</label>
												<input class="form-control" id="txtDobDelete" disabled>
											</div>
											<div class="form-group">
												<label for="txtPhoneDelete" class="col-form-label">Số điện thoại</label>
												<input class="form-control" id="txtPhoneDelete" disabled>
											</div>
											<div class="form-group">
												<label for="txtAddressDelete" class="col-form-label">Địa chỉ</label>
												<input class="form-control" id="txtAddressDelete" disabled>
											</div>
											<div class="form-group">
												<label for="txtRoleDelete" class="col-form-label">Quyền tài khoản</label>
												<input class="form-control" id="txtRoleDelete" disabled>
											</div>
										</form>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnDeleteuserCourseModal" type="button"
											class="btn btn-danger">Xóa</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- modal button Delete -->
						
						<!-- modal button Add New with File -->
						<div id="add-userCourse-file" class="modal fade" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="smaller lighter blue no-margin">Thêm danh sách tài khoản giáo viên</h3>
									</div>

									<div class="modal-body">
										<form id="data" method="post" enctype="multipart/form-data">
											<button id="openSendFile" type="button" class="btn btn-primary">
											Chọn tập tin</button>
										    <input name="file" type="file" id="file" style="display:none;" />
										</form>
										<div class="form-group">
											<label for="fileName" class="col-form-label">Tập tin đã chọn</label>
											<input class="form-control" id="fileName" placeholder="*.xls, *xlsx" disabled>
										</div>
										<div class="radio">
											<label for="rdTeacher">
												<input type="radio" name="rd" id="rdTeacher" value="teacher">
												Đây là danh sách giáo viên
											</label>
											<label for="rdStudent">
												<input type="radio" name="rd" id="rdStudent" value="student">
												Đây là danh sách học sinh
											</label>
										</div>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnAdduserCourseList" type="button"
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
								<h1>BẢNG DANH SÁCH TÀI KHOẢN</h1>
									<table id="userCourse-table"
										class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th scope="col">#</th>
												<th scope="col">Họ tên</th>
												<th scope="col">Email</th>
												<th scope="col">Môn học tham gia</th>
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