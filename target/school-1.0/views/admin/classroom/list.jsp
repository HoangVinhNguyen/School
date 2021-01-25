<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Danh sách lớp học</title>
</head>
<body>

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

				<button id="btnGetClassroom" type="button" class="btn btn-primary">Xem danh sách</button>
				<button data-toggle="modal" data-target="#add-modal" id="btnAddClassroom" type="button" class="btn btn-success">Thêm lớp học</button>
				<div class="row">
					<div class="col-xs-12">
						<!-- modal button Add New -->
						<div id="add-modal" class="modal fade" tabindex="-1">
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
												<label for="txtAddNameClassroom" class="col-form-label">Tên lớp:</label>
													<input type="text" class="form-control"
													id="txtAddNameClassroom">
											</div>
											<div class="form-group">
												<label for="txtAddCodeClassroom" class="col-form-label">Mã lớp:</label>
												<input class="form-control" id="txtAddCodeClassroom">
											</div>
										</form>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnAddModal" type="button"
											class="btn btn-success">Thêm</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- modal button Add New -->

						<!-- modal button Update -->
						<div id="update-modal" class="modal fade" tabindex="-1">
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
												<label for="txtIDUpdate" class="col-form-label">ID:</label>
												<input type="text" class="form-control" id="txtIDUpdate"
													disabled>
											</div>
											<div class="form-group">
												<label for="txtStore_idUpdate" class="col-form-label">Tên
													lớp:</label> <input type="text" class="form-control"
													id="txtNameClassroom">
											</div>
											<div class="form-group">
												<label for="txtFirst_nameUpdate" class="col-form-label">Mã
													lớp:</label> <input class="form-control" id="txtCodeClassroom">
											</div>
										</form>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnUpdateModal" type="button"
											class="btn btn-primary">Cập nhật</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- modal button Update -->

						<!-- modal button Delete -->
						<div id="delete-modal" class="modal fade" tabindex="-1">
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
												<label for="txtIDUpdate" class="col-form-label">ID:</label>
												<input type="text" class="form-control" id="txtIDDelete"
													disabled>
											</div>
											<div class="form-group">
												<label for="txtStore_idUpdate" class="col-form-label">Tên
													lớp:</label> <input type="text" class="form-control"
													id="txtNameClassroomDelete" disabled>
											</div>
											<div class="form-group">
												<label for="txtFirst_nameUpdate" class="col-form-label">Mã
													lớp:</label> <input class="form-control"
													id="txtCodeClassroomDelete" disabled>
											</div>
										</form>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnDeleteModal" type="button"
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
									<table id="dynamic-table"
										class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th scope="col">#</th>
												<th scope="col">Tên lớp</th>
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
</body>
</html>