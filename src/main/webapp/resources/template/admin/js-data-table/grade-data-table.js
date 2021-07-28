/**
 * 
 */

/*================================== GRADE ==================================*/
var tableGrade;
var dataTableRowGrade;
var rowData;

$(document).ready(function() {
	tableGrade = $('#dynamic-table').DataTable({
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
              data-target="#update-modal" type="button" class="btn btn-xs btn-info">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>

															<button data-toggle="modal"
              data-target="#delete-modal" type="button" class="btn btn-xs btn-danger">
																<i class="ace-icon fa fa-trash-o bigger-120"></i>
															</button>
														</div>`
		}]
	});
});


$('#btnGetGrade').on('click', function() {
	GetDataGrade();
});

$('#dynamic-table tbody').on('click', 'tr', function() {
	dataTableRowGrade = tableGrade.row(this).data();
});

function GetDataGrade() {
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-grade',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json'
	}).done(function(data) {

		tableGrade.clear().draw();
		//tableGrade.ajax.reload();
		for (grade of data) {
			if (grade.createdDate != null) {
				var dateC = new Date(grade.createdDate);
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

			if (grade.modifiedDate) {
				var date = new Date(grade.modifiedDate);
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
			tableGrade.row.add(
				[grade.id, grade.name, grade.code, grade.createdBy, dateTimeC, grade.modifiedBy, dateTime]
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
$("#update-modal").on('shown.bs.modal', function() {
	$("#txtIDUpdate").val(dataTableRowGrade[0]);
	$("#txtNameGrade").val(dataTableRowGrade[1]);
	$("#txtCodeGrade").val(dataTableRowGrade[2]);
});

// add content for modal delete.
$("#delete-modal").on('shown.bs.modal', function() {
	$("#txtIDDelete").val(dataTableRowGrade[0]);
	$("#txtNameGradeDelete").val(dataTableRowGrade[1]);
	$("#txtCodeGradeDelete").val(dataTableRowGrade[2]);
});
// gọi api cập nhật lớp học.
$('#btnUpdateModal').on('click', function() {
	const dataToPost = {
		id: +$('#txtIDUpdate').val(),
		name: $('#txtNameGrade').val(),
		code: $('#txtCodeGrade').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-grade',
		type: 'PUT',
		dataType: 'json',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataGrade();
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
$('#btnAddModal').on('click', function() {
	const dataToPost = {
		name: $('#txtAddNameGrade').val(),
		code: $('#txtAddCodeGrade').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-grade',
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataGrade();
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
$('#btnDeleteModal').on('click', function() {
	const dataToPost = {
		id: $('#txtIDDelete').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-grade',
		type: 'DELETE',
		dataType: 'json',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataGrade();
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
$('#btnAddGradeList').on('click', function(e) {
	e.preventDefault();
	let formData = new FormData($('#dataGrade')[0]);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-grade-file',
		type: 'POST',
		data: formData,
		cache: false,
		contentType: false,
		processData: false
	}).done(function(data) {
		GetDataGrade();
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
$('#openSendFileGrade').on('click', function() {
	$('#fileGrade').click();
});
$('#fileGrade').change(function() {
	let name = $('#fileGrade').val().split('\\');
	$('#fileNameGrade').val(name[name.length - 1]);
});
/*====================================================================*/
