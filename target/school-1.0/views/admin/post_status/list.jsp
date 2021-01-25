<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Danh sách bài viết</title>
</head>
<body>

	<div class="main-content">


		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a></li>
					<li class="active">Danh sách bài viết</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>

			<div class="page-content">

				<button id="btnGetPostStatus" type="button" class="btn btn-primary">Xem danh sách</button>
				<div class="row">
					<div class="col-xs-12">
						<!-- modal button Delete -->
						<div id="delete-poststatus-modal" class="modal fade" tabindex="-1">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-hidden="true">&times;</button>
										<h3 class="smaller lighter blue no-margin">Xóa bài viết</h3>
									</div>

									<div class="modal-body">
										<form>
											<div class="form-group">
												<label for="txtIDPost" class="col-form-label">ID:</label>
												<input type="text" class="form-control" id="txtIDPost"
													disabled>
											</div>
											<div class="form-group">
												<label for="txtIDUserPost" class="col-form-label">Mã người viết</label>
												<input type="text" class="form-control"
													id="txtIDUserPost" disabled>
											</div>
											<div class="form-group">
												<label for="txtIDClassroomPost" class="col-form-label">Mã lớp học</label>
												<input class="form-control"
													id="txtIDClassroomPost" disabled>
											</div>
											<div class="form-group">
												<label for="txtIDContentPost" class="col-form-label">Nội dung bài viết</label>
												<input class="form-control"
													id="txtIDContentPost" disabled>
											</div>
										</form>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Đóng</button>
										<button id="btnDeleteModalPostStatus" type="button"
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
									<table id="post-status-table"
										class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th scope="col">#</th>
												<th scope="col">Mã Học Sinh</th>
												<th scope="col">Mã lớp học</th>
												<th scope="col">Nội dung</th>
												<th scope="col">Người tạo</th>
												<th scope="col">Ngày tạo</th>
												<th scope="col">Người chỉnh sửa</th>
												<th scope="col">Ngày chỉnh sửa</th>
												<th></th>
											</tr>
										</thead>
										<tbody id="post_status_container">
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