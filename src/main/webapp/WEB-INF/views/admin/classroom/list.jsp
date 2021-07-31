<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<div class="main-content">


		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a></li>
					<li class="active">Danh sách phòng học</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>

			<div class="page-content">

				<button id="btnGetClassroom" type="button" class="btn btn-primary">Xem danh sách</button>
				<button data-toggle="modal" data-target="#add-modal-classroom" id="btnAddClassroom" type="button" class="btn btn-success">Thêm phòng học</button>
				<button data-toggle="modal" data-target="#add-classroom-file" id="btnAddClassroomFile" type="button" class="btn btn-success">Thêm danh sách phòng học</button>
				<hr>
				<button id="btnGetClassroomForm" type="button" class="btn btn-primary">Lấy mẫu Microsoft Excel</button>
				<button id="btnGetClassroomExcel" type="button" class="btn btn-primary">Lấy nội dung của bảng</button>
				<hr>
				
				<div class="row">
					<div class="col-xs-12">
						<!-- modal button Add New -->
						<div id="add-modal-classroom" class="modal fade" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="smaller lighter blue no-margin">Thêm mới một phòng học</h3>
									</div>

									<div class="modal-body">
										<form>
											<div class="form-group">
												<label for="txtNameAddClassroom" class="col-form-label">Tên phòng học</label>
													<input type="text" class="form-control"
													id="txtNameAddClassroom">
											</div>
											<div class="form-group">
												<label for="txtCodeAddClassroom" class="col-form-label">Mã phòng học</label>
												<input class="form-control" id="txtCodeAddClassroom">
											</div>
										</form>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnAddClassroomModal" type="button"
											class="btn btn-success">Thêm</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- modal button Add New -->

						<!-- modal button Update -->
						<div id="update-modal-classroom" class="modal fade" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="smaller lighter blue no-margin">Cập nhật thông tin phòng học</h3>
									</div>

									<div class="modal-body">
										<form>
											<div class="form-group">
												<label for="txtIDUpdateClassroom" class="col-form-label">ID</label>
												<input type="text" class="form-control" id="txtIDUpdateClassroom"
													disabled>
											</div>
											<div class="form-group">
												<label for="txtNameUpdateClassroom" class="col-form-label">Tên
													phòng học</label> <input type="text" class="form-control"
													id="txtNameUpdateClassroom">
											</div>
											<div class="form-group">
												<label for="txtCodeUpdateClassroom" class="col-form-label">Mã
													phòng học</label> <input class="form-control" id="txtCodeUpdateClassroom">
											</div>
										</form>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnUpdateClassroomModal" type="button"
											class="btn btn-primary">Cập nhật</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- modal button Update -->

						<!-- modal button Delete -->
						<div id="delete-modal-classroom" class="modal fade" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="smaller lighter blue no-margin">Xóa thông tin phòng học</h3>
									</div>

									<div class="modal-body">
										<form>
											<div class="form-group">
												<label for="txtIDUpdate" class="col-form-label">ID</label>
												<input type="text" class="form-control" id="txtIDDeleteClassroom"
													disabled>
											</div>
											<div class="form-group">
												<label for="txtNameDeleteClassroom" class="col-form-label">Tên
													phòng</label> <input type="text" class="form-control"
													id="txtNameDeleteClassroom" disabled>
											</div>
											<div class="form-group">
												<label for="txtCodeDeleteClassroom" class="col-form-label">Mã
													phòng</label> <input class="form-control"
													id="txtCodeDeleteClassroom" disabled>
											</div>
										</form>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnDeleteClassroomModal" type="button"
											class="btn btn-danger">Xóa</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- modal button Delete -->

						<!-- modal button Add New with File -->
						<div id="add-classroom-file" class="modal fade" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="smaller lighter blue no-margin">Thêm danh sách phòng học</h3>
									</div>

									<div class="modal-body">
										<form id="dataClassroom" method="post" enctype="multipart/form-data">
											<button id="openSendFileClassroom" type="button" class="btn btn-primary">
											Chọn tập tin</button>
										    <input name="file" type="file" id="fileClassroom" style="display:none;" />
										</form>
										<div class="form-group">
											<label for="fileNameClassroom" class="col-form-label">Tập tin đã chọn</label>
											<input class="form-control" id="fileNameClassroom" placeholder="*.xls, *xlsx" disabled>
										</div>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnAddClassroomList" type="button"
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
								<h1>BẢNG DANH SÁCH PHÒNG HỌC</h1>
									<table id="dynamic-table"
										class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th scope="col">#</th>
												<th scope="col">Tên phòng học</th>
												<th scope="col">Mã phòng học</th>
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
	<!-- <script>
		var totalPages = $
		{
			model.totalPage
		};
		var currentPage = $
		{
			model.page
		};
		var limit = 2;
		$(function() {
			window.pagObj = $('#pagination').twbsPagination({
				totalPages : totalPages,
				visiblePages : 10,
				startPage : currentPage,
				onPageClick : function(event, page) {
					if (currentPage != page) {
						$('#maxPageItem').val(limit);
						$('#page').val(page);
						$('#sortName').val('title');
						$('#sortBy').val('asc');
						$('#formSubmit').submit();
					}
				}
			});
		});
	</script> -->