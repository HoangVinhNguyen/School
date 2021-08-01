/**
 * 
 */

/*================================== CLASSROOM ==================================*/
var tableClassroom;
var dataTableRowClassroom;
var rowData;

$(document).ready(function() {
	tableClassroom = $('#dynamic-table').DataTable({
		destroy: true,
		retrieve: true,
		stateSave: true,
		"autoWidth": false,
		language: { "url": "//cdn.datatables.net/plug-ins/1.10.22/i18n/Vietnamese.json" },
		"ajax": rowData,
		"columnDefs": [{
			"targets": -1,
			"data": null,
			"defaultContent": `<div class="btn-group" role="group">

															<button data-toggle="modal"
              data-target="#update-modal-classroom" type="button" class="btn btn-xs btn-info">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>

															<button data-toggle="modal"
              data-target="#delete-modal-classroom" type="button" class="btn btn-xs btn-danger">
																<i class="ace-icon fa fa-trash-o bigger-120"></i>
															</button>
														</div>`
		}]
	});
});


$('#btnGetClassroom').on('click', function() {
	GetDataClassroom();
});

$('#dynamic-table tbody').on('click', 'tr', function() {
	dataTableRowClassroom = tableClassroom.row(this).data();
});

function GetDataClassroom() {
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-classroom',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json'
	}).done(function(data) {

		tableClassroom.clear().draw();
		//tableClassroom.ajax.reload();
		for (classroom of data) {
			if (classroom.createdDate != null) {
				var dateC = new Date(classroom.createdDate);
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

			if (classroom.modifiedDate) {
				var date = new Date(classroom.modifiedDate);
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
			tableClassroom.row.add(
				[classroom.id, classroom.name, classroom.code, classroom.createdBy, dateTimeC, classroom.modifiedBy, dateTime]
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
$("#update-modal-classroom").on('shown.bs.modal', function() {
	$("#txtIDUpdateClassroom").val(dataTableRowClassroom[0]);
	$("#txtNameUpdateClassroom").val(dataTableRowClassroom[1]);
	$("#txtCodeUpdateClassroom").val(dataTableRowClassroom[2]);
});

// add content for modal delete.
$("#delete-modal-classroom").on('shown.bs.modal', function() {
	$("#txtIDDeleteClassroom").val(dataTableRowClassroom[0]);
	$("#txtNameDeleteClassroom").val(dataTableRowClassroom[1]);
	$("#txtCodeDeleteClassroom").val(dataTableRowClassroom[2]);
});
// gọi api cập nhật lớp học.
$('#btnUpdateClassroomModal').on('click', function() {
	const dataToPost = {
		id: +$('#txtIDUpdateClassroom').val(),
		name: $('#txtNameUpdateClassroom').val(),
		code: $('#txtCodeUpdateClassroom').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-classroom',
		type: 'PUT',
		dataType: 'text',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataClassroom();
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
$('#btnAddClassroomModal').on('click', function() {
	const dataToPost = {
		name: $('#txtNameAddClassroom').val(),
		code: $('#txtCodeAddClassroom').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-classroom',
		type: 'POST',
		dataType: 'text',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataClassroom();
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
$('#btnDeleteClassroomModal').on('click', function() {
	const dataToPost = {
		id: $('#txtIDDeleteClassroom').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-classroom',
		type: 'DELETE',
		dataType: 'text',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataClassroom();
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
$('#btnAddClassroomList').on('click', function(e) {
	e.preventDefault();
	let formData = new FormData($('#dataClassroom')[0]);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-classroom-file',
		type: 'POST',
		data: formData,
		cache: false,
		contentType: false,
		processData: false
	}).done(function(data) {
		GetDataClassroom();
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
$('#openSendFileClassroom').on('click', function() {
	$('#fileClassroom').click();
});
$('#fileClassroom').change(function() {
	let name = $('#fileClassroom').val().split('\\');
	$('#fileNameClassroom').val(name[name.length - 1]);
});

$('#btnGetClassroomForm').on('click', function() {
	DownloadFormClassroom();
});

$('#btnGetClassroomExcel').on('click', function() {
	DownloadReportClassroom();
});

function DownloadFormClassroom() {
	window.location.href = "http://localhost:8080/school/admin/api-admin-classroom-file-form-download";
}

function DownloadReportClassroom() {
	window.location.href = "http://localhost:8080/school/admin/api-admin-classroom-file-report-download";
}

/*====================================================================*/
