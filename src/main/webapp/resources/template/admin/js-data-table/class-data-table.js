/**
 * 
 */

/*================================== CLASS ==================================*/
var tableClass;
var dataTableRowClass;
var rowData;

$(document).ready(function() {
	tableClass = $('#dynamic-table').DataTable({
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
              data-target="#update-modal-class" type="button" class="btn btn-xs btn-info">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>

															<button data-toggle="modal"
              data-target="#delete-modal-class" type="button" class="btn btn-xs btn-danger">
																<i class="ace-icon fa fa-trash-o bigger-120"></i>
															</button>
														</div>`
		}]
	});
});


$('#btnGetClass').on('click', function() {
	GetDataClass();
});

$('#dynamic-table tbody').on('click', 'tr', function() {
	dataTableRowClass = tableClass.row(this).data();
});

function GetDataClass() {
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-class',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json'
	}).done(function(data) {

		tableClass.clear().draw();
		//tableClass.ajax.reload();
		for (classIn of data) {
			if (classIn.createdDate != null) {
				var dateC = new Date(classIn.createdDate);
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

			if (classIn.modifiedDate) {
				var date = new Date(classIn.modifiedDate);
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
			tableClass.row.add(
				[classIn.id, classIn.name, classIn.code, classIn.grade.name, classIn.createdBy, dateTimeC, classIn.modifiedBy, dateTime]
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

// add content for modal add new.
$("#add-modal-class").on('shown.bs.modal', function() {
	GetDataGradeClassForSelect($('#txtAddGradeClass'), null);
});

// add content for modal update.
$("#update-modal-class").on('shown.bs.modal', function() {
	GetDataGradeClassForSelect($('#txtUpdateGradeClass'), dataTableRowClass[3]);
	$("#txtIDUpdateClass").val(dataTableRowClass[0]);
	$("#txtNameUpdateClass").val(dataTableRowClass[1]);
	$("#txtCodeUpdateClass").val(dataTableRowClass[2]);
});

// add content for modal delete.
$("#delete-modal-class").on('shown.bs.modal', function() {
	$("#txtIDDeleteClass").val(dataTableRowClass[0]);
	$("#txtNameClassDelete").val(dataTableRowClass[1]);
	$("#txtCodeClassDelete").val(dataTableRowClass[2]);
	$('#txtDeleteGradeClass').val(dataTableRowClass[3]);
});

// add content for modal from file.
$("#add-class-file").on('shown.bs.modal', function() {
	$('#fileNameClass').val("");
});

// gọi api cập nhật lớp học.
$('#btnUpdateClassModal').on('click', function() {
	const dataToPost = {
		id: +$('#txtIDUpdateClass').val(),
		name: $('#txtNameUpdateClass').val(),
		code: $('#txtCodeUpdateClass').val(),
		grade: {
			id: +$('#txtUpdateGradeClass').val()
		}
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-class',
		type: 'PUT',
		dataType: 'text',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataClass();
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
$('#btnAddClassModal').on('click', function() {
	const dataToPost = {
		name: $('#txtNameAddClass').val(),
		code: $('#txtCodeAddClass').val(),
		grade: {
			id: +$('#txtAddGradeClass').val()
		}
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-class',
		type: 'POST',
		dataType: 'text',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataClass();
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
$('#btnDeleteClassModal').on('click', function() {
	const dataToPost = {
		id: $('#txtIDDeleteClass').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-class',
		type: 'DELETE',
		dataType: 'text',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataClass();
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
$('#btnAddClassList').on('click', function(e) {
	e.preventDefault();
	let formData = new FormData($('#dataClass')[0]);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-class-file',
		type: 'POST',
		data: formData,
		cache: false,
		contentType: false,
		processData: false
	}).done(function(data) {
		GetDataClass();
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
$('#openSendFileClass').on('click', function() {
	$('#fileClass').click();
});
$('#fileClass').change(function() {
	let name = $('#fileClass').val().split('\\');
	$('#fileNameClass').val(name[name.length - 1]);
});

$('#btnGetClassForm').on('click', function() {
	DownloadFormClass();
});

$('#btnGetClassExcel').on('click', function() {
	DownloadReportClass();
});

function GetDataGradeClassForSelect(element, selectString) {
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-grade',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json',
	}).done(function(data) {
		console.log(selectString);
		if (data !== null) {
			element.empty();
			for (let item of data) {
				if (selectString === item.name) {
					element.append(new Option(item.name, item.id, true, true));
				}
				else {
					element.append(new Option(item.name, item.id));
				}
			}
		}
	}).fail(function(jqXHR, textStatus, err) {
		console.error(jqXHR);
		console.error(textStatus);
		console.error(err);
		return null;
	});
}

function DownloadFormClass() {
	window.location.href = "http://localhost:8080/school/admin/api-admin-class-file-form-download";
}

function DownloadReportClass() {
	window.location.href = "http://localhost:8080/school/admin/api-admin-class-file-report-download";
}

/*====================================================================*/
