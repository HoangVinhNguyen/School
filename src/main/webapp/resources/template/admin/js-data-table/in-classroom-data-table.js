/**
 * 
 */

/*================================== IN CLASSROOM ==================================*/
var classroomArray = [];
var tableInClassroom;
var dataTableRowInClassroom;
var rowDataInClassroom;

$(document).ready(function() {
	tableInClassroom = $('#inclassroom-table').DataTable({
		destroy: true,
		retrieve: true,
		"autoWidth": false,
		language: { "url": "//cdn.datatables.net/plug-ins/1.10.22/i18n/Vietnamese.json" },
		"ajax": rowDataInClassroom,
		"columnDefs": [{
			"targets": -1,
			"data": null,
			"defaultContent": `<div class="btn-group" role="group">

															<button data-toggle="modal"
              data-target="#update-inclassroom-modal" type="button" class="btn btn-xs btn-info">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>

															<button data-toggle="modal"
              data-target="#delete-inclassroom-modal" type="button" class="btn btn-xs btn-danger">
																<i class="ace-icon fa fa-trash-o bigger-120"></i>
															</button>
														</div>`
		}]
	});
});


$('#btnGetInClassroom').on('click', function() {
	GetDataInClassroom();
});

$('#inclassroom-table tbody').on('click', 'tr', function() {
	dataTableRowInClassroom = tableInClassroom.row(this).data();
});

function GetDataInClassroom() {
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-inclassroom',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json'
	}).done(function(data) {
		tableInClassroom.clear().draw();
		for (inclassroom of data) {

			if (inclassroom.createdDate != null) {
				var dateC = new Date(inclassroom.createdDate);
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

			if (inclassroom.modifiedDate) {
				var date = new Date(inclassroom.modifiedDate);
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
			tableInClassroom.row.add(
				[inclassroom.id, inclassroom.studentModel.email, inclassroom.studentModel.fullname, inclassroom.classroomModel.name, inclassroom.createdBy, dateTimeC, inclassroom.modifiedBy, dateTime]
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
$("#update-inclassroom-modal").on('shown.bs.modal', function() {
	$("#txtIDUpdateInClassroom").val(dataTableRowInClassroom[0]);
	$("#txtIDStudentInClassroomUpdate").val(dataTableRowInClassroom[1]);
	$("#txtIDClassroomInClassroomUpdate").val(dataTableRowInClassroom[2]);
});

// add content for modal delete.
$("#delete-inclassroom-modal").on('shown.bs.modal', function() {
	$("#txtIDDeleteInClassroom").val(dataTableRowInClassroom[0]);
	$("#txtIDStudentInClassroomDelete").val(dataTableRowInClassroom[1]);
	$("#txtIDClassroomInClassroomDelete").val(dataTableRowInClassroom[2]);
});

// Add new
$('#btnAddInClassroomModal').on('click', function() {
	const dataToPost = {
		studentId: $('#txtAddStudentIDInClassroom').val(),
		classroomId: $('#txtAddClassroomIDInClassroom').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-inclassroom',
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataInClassroom();
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

// gọi api cập nhật lớp học.
$('#btnUpdateInClassroomModal').on('click', function() {
	const dataToPost = {
		id: +$('#txtIDUpdateInClassroom').val(),
		studentId: +$('#txtIDStudentInClassroomUpdate').val(),
		classroomId: +$('#txtIDClassroomInClassroomUpdate').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-inclassroom',
		type: 'PUT',
		dataType: 'json',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataInClassroom();
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

// Delete
$('#btnDeleteInClassroomModal').on('click', function() {
	const dataToPost = {
		id: $('#txtIDDeleteInClassroom').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-inclassroom',
		type: 'DELETE',
		dataType: 'json',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataInClassroom();
		Swal.fire({
			title: 'Xóa thành công',
			text: "",
			icon: 'success',
			confirmButtonText: 'Đóng'
		});
		$('#delete-inclassroom-modal').modal('toggle');
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

$("form#dataInClass").submit(function(e) {
	e.preventDefault();
	var formData = new FormData(this);

	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-inclassroom-array',
		type: 'POST',
		data: formData,
		cache: false,
		contentType: false,
		processData: false
	}).done(function(data) {
		GetDataInClassroom();
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

/*====================================================================*/

/*================================== IN CLASSROOM ==================================*/
var classroomArray = [];


$(document).ready(function() {
	table = $('#literatureCourse-table').DataTable({
		destroy: true,
		retrieve: true,
		"autoWidth": false,
		language: { "url": "//cdn.datatables.net/plug-ins/1.10.22/i18n/Vietnamese.json" },
		"ajax": rowData,
		"columnDefs": [{
			"targets": -1,
			"data": null,
			"defaultContent": `<div class="btn-group" role="group">

															<button data-toggle="modal"
              data-target="#update-literatureCourse-modal" type="button" class="btn btn-xs btn-info">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>

															<button data-toggle="modal"
              data-target="#delete-literatureCourse-modal" type="button" class="btn btn-xs btn-danger">
																<i class="ace-icon fa fa-trash-o bigger-120"></i>
															</button>
														</div>`
		}]
	});
});


$('#btnGetLiteratureCourse').on('click', function() {
	GetDataLiteratureCourse();
});

$('#literatureCourse-table tbody').on('click', 'tr', function() {
	dataTableRow = table.row(this).data();
});

function GetDataLiteratureCourse() {
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-literaturecourse',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json'
	}).done(function(data) {
		table.clear().draw();
		for (literaturecourse of data) {

			if (literaturecourse.createdDate != null) {
				var dateC = new Date(literaturecourse.createdDate);
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

			if (literaturecourse.modifiedDate) {
				var date = new Date(literaturecourse.modifiedDate);
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
			table.row.add(
				[literaturecourse.id, literaturecourse.userId, literaturecourse.point, literaturecourse.createdBy, dateTimeC, literaturecourse.modifiedBy, dateTime]
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
$("#update-literatureCourse-modal").on('shown.bs.modal', function() {
	$("#txtIDUpdateLiteratureCourse").val(dataTableRow[0]);
	$("#txtIDStudentLiteratureCourseUpdate").val(dataTableRow[1]);
	$("#txtIDClassroomLiteratureCourseUpdate").val(dataTableRow[2]);
});

// add content for modal delete.
$("#delete-literatureCourse-modal").on('shown.bs.modal', function() {
	$("#txtIDDeleteLiteratureCourse").val(dataTableRow[0]);
	$("#txtIDStudentLiteratureCourseDelete").val(dataTableRow[1]);
	$("#txtLiteratureCoursePointDelete").val(dataTableRow[2]);
});

// Add new
$('#btnAddLiteratureCourseModal').on('click', function() {
	const dataToPost = {
		userId: +$('#txtAddStudentIDLiteratureCourse').val(),
		point: $('#txtAddPointLiteratureCourse').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-literaturecourse',
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataLiteratureCourse();
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

// gọi api cập nhật lớp học.
$('#btnUpdateLiteratureCourseModal').on('click', function() {
	const dataToPost = {
		id: +$('#txtIDUpdateLiteratureCourse').val(),
		userId: +$('#txtIDStudentLiteratureCourseUpdate').val(),
		point: +$('#txtIDClassroomInClassroomUpdate').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-literaturecourse',
		type: 'PUT',
		dataType: 'json',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataLiteratureCourse();
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

// Delete
$('#btnDeleteLiteratureCourseModal').on('click', function() {
	const dataToPost = {
		id: +$('#txtIDDeleteLiteratureCourse').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-literaturecourse',
		type: 'DELETE',
		dataType: 'json',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataLiteratureCourse();
		Swal.fire({
			title: 'Xóa thành công',
			text: "",
			icon: 'success',
			confirmButtonText: 'Đóng'
		});
		$('#delete-literatureCourse-modal').modal('toggle');
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

/*====================================================================*/
