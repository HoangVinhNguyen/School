<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<div class="main-content">


		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a></li>
					<li class="active">Danh sách lớp học</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>

			<div class="page-content">

				<button id="btnGetClass" type="button" class="btn btn-primary">Xem danh sách</button>
				<button data-toggle="modal" data-target="#add-modal-class" id="btnAddClass" type="button" class="btn btn-success">Thêm lớp học</button>
				<button data-toggle="modal" data-target="#add-class-file" id="btnAddClassFile" type="button" class="btn btn-success">Thêm danh sách lớp học</button>
				<hr>
				<button id="btnGetClassForm" type="button" class="btn btn-primary">Lấy mẫu Microsoft Excel</button>
				<button id="btnGetClassExcel" type="button" class="btn btn-primary">Lấy nội dung của bảng</button>
				<hr>
				<div class="row">
					<div class="col-xs-12">
						<!-- modal button Add New -->
						<div id="add-modal-class" class="modal fade" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="smaller lighter blue no-margin">Thêm mới một lớp học</h3>
									</div>

									<div class="modal-body">
										<form>
											<div class="form-group">
												<label for=""txtNameAddClass"" class="col-form-label">Tên lớp học</label>
													<input type="text" class="form-control"
													id="txtNameAddClass">
											</div>
											<div class="form-group">
												<label for="txtCodeAddClass" class="col-form-label">Mã lớp học</label>
												<input class="form-control" id="txtCodeAddClass">
											</div>
											<div class="form-group">
												<label for="txtAddGradeClass" class="col-form-label">Tên khối lớp học</label>
												<select class="form-control" id="txtAddGradeClass"></select>
											</div>
										</form>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnAddClassModal" type="button"
											class="btn btn-success">Thêm</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- modal button Add New -->

						<!-- modal button Update -->
						<div id="update-modal-class" class="modal fade" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="smaller lighter blue no-margin">Cập nhật thông tin lớp học</h3>
									</div>

									<div class="modal-body">
										<form>
											<div class="form-group">
												<label for="txtIDUpdateClass" class="col-form-label">ID</label>
												<input type="text" class="form-control" id="txtIDUpdateClass"
													disabled>
											</div>
											<div class="form-group">
												<label for="txtNameUpdateClass" class="col-form-label">Tên
													lớp học</label> <input type="text" class="form-control"
													id="txtNameUpdateClass">
											</div>
											<div class="form-group">
												<label for="txtCodeUpdateClass" class="col-form-label">Mã
													lớp học</label> <input class="form-control" id="txtCodeUpdateClass">
											</div>
											<div class="form-group">
												<label for="txtUpdateGradeClass" class="col-form-label">Tên khối lớp học</label>
												<select class="form-control" id="txtUpdateGradeClass"></select>
											</div>
										</form>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnUpdateClassModal" type="button"
											class="btn btn-primary">Cập nhật</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- modal button Update -->

						<!-- modal button Delete -->
						<div id="delete-modal-class" class="modal fade" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="smaller lighter blue no-margin">Xóa thông tin lớp học</h3>
									</div>

									<div class="modal-body">
										<form>
											<div class="form-group">
												<label for="txtIDDeleteClass" class="col-form-label">ID</label>
												<input type="text" class="form-control" id="txtIDDeleteClass"
													disabled>
											</div>
											<div class="form-group">
												<label for="txtNameClassDelete" class="col-form-label">Tên
													lớp học</label> <input type="text" class="form-control"
													id="txtNameClassDelete" disabled>
											</div>
											<div class="form-group">
												<label for="txtCodeClassDelete" class="col-form-label">Mã
													lớp học</label> <input class="form-control"
													id="txtCodeClassDelete" disabled>
											</div>
											<div class="form-group">
												<label for="txtDeleteGradeClass" class="col-form-label">Tên khối lớp học</label>
												<input class="form-control" id="txtDeleteGradeClass" disabled>
											</div>
										</form>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnDeleteClassModal" type="button"
											class="btn btn-danger">Xóa</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- modal button Delete -->

						<!-- modal button Add New with File -->
						<div id="add-class-file" class="modal fade" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="smaller lighter blue no-margin">Thêm danh sách lớp học</h3>
									</div>

									<div class="modal-body">
										<form id="dataClass" method="post" enctype="multipart/form-data">
											<button id="openSendFileClass" type="button" class="btn btn-primary">
											Chọn tập tin</button>
										    <input name="file" type="file" id="fileClass" style="display:none;" />
										</form>
										<div class="form-group">
											<label for="fileNameClass" class="col-form-label">Tập tin đã chọn</label>
											<input class="form-control" id="fileNameClass" placeholder="*.xls, *xlsx" disabled>
										</div>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnAddClassList" type="button"
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
								<h1>BẢNG DANH SÁCH LỚP HỌC</h1>
									<table id="dynamic-table"
										class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th scope="col">#</th>
												<th scope="col">Tên lớp học</th>
												<th scope="col">Mã lớp học</th>
												<th scope="col">Tên khối lớp học</th>
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