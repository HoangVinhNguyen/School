/**
 * 
 */

/*================================== COURSE ==================================*/
var tableCourse;
var dataTableRowCourse;
var rowDataCourse;

$(document).ready(function() {
	tableCourse = $('#course-table').DataTable({
		destroy: true,
		retrieve: true,
		"autoWidth": false,
		language: { "url": "//cdn.datatables.net/plug-ins/1.10.22/i18n/Vietnamese.json" },
		"ajax": rowDataCourse,
		"columnDefs": [{
			"targets": -1,
			"data": null,
			"defaultContent": `<div class="btn-group" role="group">

															<button data-toggle="modal"
              data-target="#update-course-modal" type="button" class="btn btn-xs btn-info">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>

															<button data-toggle="modal"
              data-target="#delete-course-modal" type="button" class="btn btn-xs btn-danger">
																<i class="ace-icon fa fa-trash-o bigger-120"></i>
															</button>
														</div>`
		}]
	});
});


$('#btnGetListUser').on('click', function() {
	GetDataCourse();
});

$('#course-table tbody').on('click', 'tr', function() {
	dataTableRowCourse = tableCourse.row(this).data();
});

function GetDataCourse() {
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-course',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json'
	}).done(function(data) {

		tableCourse.clear().draw();
		for (course of data) {

			if (course.createdDate != null) {
				var dateC = new Date(course.createdDate);
				var dateTimeC = dateC.getFullYear() + "-" +
					("0" + (dateC.getMonth() + 1)).slice(-2) + "-" +
					("0" + dateC.getDate()).slice(-2) + " " +
					("0" + dateC.getHours()).slice(-2) + ":" +
					("0" + dateC.getMinutes()).slice(-2) + ":" +
					("0" + dateC.getSeconds()).slice(-2);
			}
			else {
				var dateTimeC = null;
			}

			if (course.modifiedDate) {
				var date = new Date(course.modifiedDate);
				var dateTime = date.getFullYear() + "-" +
					("0" + (date.getMonth() + 1)).slice(-2) + "-" +
					("0" + date.getDate()).slice(-2) + " " +
					("0" + date.getHours()).slice(-2) + ":" +
					("0" + date.getMinutes()).slice(-2) + ":" +
					("0" + date.getSeconds()).slice(-2);
			}
			else {
				dateTime = null;
			}
			tableCourse.row.add(
				[course.id, course.name, course.code, course.createdBy, dateTimeC, course.modifiedBy, dateTime]
			).draw(false);
		}
		Swal.fire({
			title: 'Đã có dữ liệu mới',
			text: "",
			icon: 'success',
			showCancelButton: false,
			showConfirmButton: false,
			timer: 1000
		});
	}).fail(function(jqXHR, textStatus, err) {
		Swal.fire({
			title: 'Ôi lỗi',
			text: "",
			icon: 'error',
			confirmButtonText: 'Đóng'
		});
		console.error(jqXHR);
		console.error(textStatus);
		console.error(err);
	});
}
// add content for modal update.
$("#update-course-modal").on('shown.bs.modal', function() {
	$("#txtIDUpdateCourse").val(dataTableRowCourse[0]);
	$("#txtNameUpdateCourse").val(dataTableRowCourse[1]);
	$("#txtCodeUpdateCourse").val(dataTableRowCourse[2]);
});

// add content for modal delete.
$("#delete-course-modal").on('shown.bs.modal', function() {
	$("#txtIDDeleteCourse").val(dataTableRowCourse[0]);
	$("#txtNameDeleteCourse").val(dataTableRowCourse[1]);
	$("#txtCodeDeleteCourse").val(dataTableRowCourse[2]);
});
// gọi api cập nhật lớp học.
$('#btnUpdateCourseModal').on('click', function() {

	const dataToPost = {
		id: +$("#txtIDUpdateCourse").val(),
		name: $('#txtNameUpdateCourse').val(),
		code: $('#txtCodeUpdateCourse').val(),
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-course',
		type: 'PUT',
		dataType: 'text',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataCourse();
		Swal.fire({
			title: 'Cập nhật thành công',
			text: "",
			icon: 'success',
			confirmButtonText: 'Đóng'
		});
	}).fail(function(jqXHR, textStatus, err) {
		Swal.fire({
			title: 'Ôi lỗi',
			text: "",
			icon: 'error',
			confirmButtonText: 'Đóng'
		});
		console.error(jqXHR);
		console.error(textStatus);
		console.error(err);
	});
});

// Add new
$('#btnAddCourseModal').on('click', function() {

	const dataToPost = {
		name: $('#txtNameAddCourse').val(),
		code: $('#txtCodeAddCourse').val(),
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-course',
		type: 'POST',
		dataType: 'text',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataCourse();
		Swal.fire({
			title: 'Thêm mới thành công',
			text: "",
			icon: 'success',
			confirmButtonText: 'Đóng'
		});
	}).fail(function(jqXHR, textStatus, err) {
		Swal.fire({
			title: 'Ôi lỗi',
			text: "",
			icon: 'error',
			confirmButtonText: 'Đóng'
		});
		console.error(jqXHR);
		console.error(textStatus);
		console.error(err);
	});
});

// Delete
$('#btnDeleteCourseModal').on('click', function() {
	const dataToPost = {
		id: $('#txtIDDeleteCourse').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-course',
		type: 'DELETE',
		dataType: 'text',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataCourse();
		Swal.fire({
			title: 'Xóa thành công',
			text: "",
			icon: 'success',
			confirmButtonText: 'Đóng'
		});
		$('#delete-modal').modal('toggle');
	}).fail(function(jqXHR, textStatus, err) {
		Swal.fire({
			title: 'Ôi lỗi',
			text: "",
			icon: 'error',
			confirmButtonText: 'Đóng'
		});
		console.error(jqXHR);
		console.error(textStatus);
		console.error(err);
	});
});

// Add by file
$('#btnAddCourseList').on('click', function(e) {
	e.preventDefault();
	let formData = new FormData($('#dataCourse')[0]);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-course-file',
		type: 'POST',
		data: formData,
		cache: false,
		contentType: false,
		processData: false
	}).done(function(data) {
		GetDataCourse();
		Swal.fire({
			title: 'Thêm mới thành công',
			text: "",
			icon: 'success',
			confirmButtonText: 'Đóng'
		});
	}).fail(function(jqXHR, textStatus, err) {
		Swal.fire({
			title: 'Ôi lỗi',
			text: "",
			icon: 'error',
			confirmButtonText: 'Đóng'
		});
		console.error(jqXHR);
		console.error(textStatus);
		console.error(err);
	});
});
$('#openSendFileCourse').on('click', function() {
	$('#fileCourse').click();
});
$('#fileCourse').change(function() {
	let name = $('#fileCourse').val().split('\\');
	$('#fileNameCourse').val(name[name.length - 1]);
});

$('#btnGetCourseForm').on('click', function() {
	DownloadFormCourse();
});

$('#btnGetCourseExcel').on('click', function() {
	DownloadReportCourse();
});

function DownloadFormCourse() {
	window.location.href = "http://localhost:8080/school/admin/api-admin-course-file-form-download";
}

function DownloadReportCourse() {
	window.location.href = "http://localhost:8080/school/admin/api-admin-course-file-report-download";
}

/*====================================================================*/
