
/*================================== CLASSROOM ==================================*/
var tableClassroom;
var dataTableRowClassroom;
var rowData;

$(document).ready(function() {
	tableClassroom = $('#dynamic-table').DataTable({
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
$("#update-modal").on('shown.bs.modal', function() {
	$("#txtIDUpdate").val(dataTableRowClassroom[0]);
	$("#txtNameClassroom").val(dataTableRowClassroom[1]);
	$("#txtCodeClassroom").val(dataTableRowClassroom[2]);
});

// add content for modal delete.
$("#delete-modal").on('shown.bs.modal', function() {
	$("#txtIDDelete").val(dataTableRowClassroom[0]);
	$("#txtNameClassroomDelete").val(dataTableRowClassroom[1]);
	$("#txtCodeClassroomDelete").val(dataTableRowClassroom[2]);
});
// gọi api cập nhật lớp học.
$('#btnUpdateModal').on('click', function() {
	const dataToPost = {
		id: +$('#txtIDUpdate').val(),
		name: $('#txtNameClassroom').val(),
		code: $('#txtCodeClassroom').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-classroom',
		type: 'PUT',
		dataType: 'json',
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
$('#btnAddModal').on('click', function() {
	const dataToPost = {
		name: $('#txtAddNameClassroom').val(),
		code: $('#txtAddCodeClassroom').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-classroom',
		type: 'POST',
		dataType: 'json',
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
$('#btnDeleteModal').on('click', function() {
	const dataToPost = {
		id: $('#txtIDDelete').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-classroom',
		type: 'DELETE',
		dataType: 'json',
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

/*====================================================================*/


/*================================== POST STATUS ==================================*/
var tablePost;
var dataTableRowClassroom;
var rowData;

$(document).ready(function() {
	table = $('#post-status-table').DataTable({
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
              data-target="#delete-poststatus-modal" type="button" class="btn btn-xs btn-danger">
																<i class="ace-icon fa fa-trash-o bigger-120"></i>
															</button>
														</div>`
		}]
	});
});


$('#btnGetPostStatus').on('click', function() {
	GetDataPostStatus();
});

$('#post-status-table tbody').on('click', 'tr', function() {
	dataTableRow = table.row(this).data();
});

function GetDataPostStatus() {
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-poststatus',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json'
	}).done(function(data) {
		console.log(data);
		table.clear().draw();
		for (poststatus of data) {

			if (poststatus.createdDate != null) {
				var dateC = new Date(poststatus.createdDate);
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

			if (poststatus.modifiedDate) {
				var date = new Date(poststatus.modifiedDate);
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
				[poststatus.id, poststatus.userId, poststatus.classroomId, poststatus.content, poststatus.createdBy, dateTimeC, poststatus.modifiedBy, dateTime]
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

// add content for modal delete.
$("#delete-poststatus-modal").on('shown.bs.modal', function() {
	$("#txtIDPost").val(dataTableRow[0]);
	$("#txtIDUserPost").val(dataTableRow[1]);
	$("#txtIDClassroomPost").val(dataTableRow[2]);
	$("#txtIDContentPost").val(dataTableRow[3]);
});

// Delete
$('#btnDeleteModalPostStatus').on('click', function() {
	const dataToPost = {
		id: $('#txtIDPost').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-poststatus',
		type: 'DELETE',
		dataType: 'json',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataPostStatus();
		$('#delete-poststatus-modal').modal('toggle');
		Swal.fire({
			title: 'Xóa thành công',
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
				[inclassroom.id, inclassroom.studentId, inclassroom.classroomId, inclassroom.createdBy, dateTimeC, inclassroom.modifiedBy, dateTime]
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
		studentId: $('#txtIDStudentInClassroomUpdate').val(),
		classroomId: $('#txtIDClassroomInClassroomUpdate').val()
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


/*================================== USER ==================================*/
var tableUser;
var dataTableRowUser;
var rowDataUser;
var dataExcel = ['a'];

$(document).ready(function() {
	tableUser = $('#user-table').DataTable({
		destroy: true,
		retrieve: true,
		"autoWidth": false,
		language: { "url": "//cdn.datatables.net/plug-ins/1.10.22/i18n/Vietnamese.json" },
		"ajax": rowDataUser,
		"columnDefs": [{
			"targets": -1,
			"data": null,
			"defaultContent": `<div class="btn-group" role="group">

															<button data-toggle="modal"
              data-target="#update-user-modal" type="button" class="btn btn-xs btn-info">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>

															<button data-toggle="modal"
              data-target="#delete-user-modal" type="button" class="btn btn-xs btn-danger">
																<i class="ace-icon fa fa-trash-o bigger-120"></i>
															</button>
														</div>`
		}]
	});
});


$('#btnGetListUser').on('click', function() {
	GetDataUser();
});

$('#user-table tbody').on('click', 'tr', function() {
	dataTableRowUser = tableUser.row(this).data();
});

function GetDataUser() {
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-user',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json'
	}).done(function(data) {

		tableUser.clear().draw();
		for (user of data) {

			if (user.dob != null) {
				var dateD = new Date(user.dob);
				var dateTimeD = dateD.getFullYear() + "-" +
					("0" + (dateD.getMonth() + 1)).slice(-2) + "-" +
					("0" + dateD.getDate()).slice(-2) + " " +
					("0" + dateD.getHours()).slice(-2) + ":" +
					("0" + dateD.getMinutes()).slice(-2) + ":" +
					("0" + dateD.getSeconds()).slice(-2);
			}
			else {
				var dateTimeD = null;
			}

			if (user.createdDate != null) {
				var dateC = new Date(user.createdDate);
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

			if (user.modifiedDate) {
				var date = new Date(user.modifiedDate);
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
			tableUser.row.add(
				[user.id, user.email, user.fullname, dateTimeD, user.address, user.role.name, user.createdBy, dateTimeC, user.modifiedBy, dateTime]
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
$("#update-user-modal").on('shown.bs.modal', function() {
	$("#txtIDUserUpdate").val(dataTableRowUser[0]);
	$("#txtEmailUserUpdate").val(dataTableRowUser[1]);
	$("#txtFullnameUpdate").val(dataTableRowUser[2]);
	$("#txtDobUpdate").val(dataTableRowUser[3]);
	$("#txtAddressUpdate").val(dataTableRowUser[4]);
	$("#txtRoleUpdate").val(dataTableRowUser[5]);
});

// add content for modal delete.
$("#delete-user-modal").on('shown.bs.modal', function() {
	$("#txtIDUserDelete").val(dataTableRowUser[0]);
	$("#txtEmailUserDelete").val(dataTableRowUser[1]);
	$("#txtFullnameDelete").val(dataTableRowUser[2]);
	$("#txtDobDelete").val(dataTableRowUser[3]);
	$("#txtAddressDelete").val(dataTableRowUser[4]);
	$("#txtRoleDelete").val(dataTableRowUser[5]);
});
// gọi api cập nhật lớp học.
$('#btnUpdateUserModal').on('click', function() {
	var datum = Date.parse($("#txtDobUpdate").val());
	const dataToPost = {
		id: +$("#txtIDUserUpdate").val(),
		email: $('#txtEmailUserUpdate').val(),
		fullname: $('#txtFullnameUpdate').val(),
		dob: datum,
		address: $("#txtAddressUpdate").val(),
		roledId: $("#txtRoleUpdate").val() === "ADMIN" ? 1 : $("#txtRoleUpdate").val() === "STUDENT" ? 2 : 3
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-user',
		type: 'PUT',
		dataType: 'json',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataUser();
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
$('#btnAddUserModal').on('click', function() {
	var datum = Date.parse($("#txtDobAdd").val());
	const dataToPost = {
		id: +$("#txtIDUserAdd").val(),
		email: $('#txtEmailUserAdd').val(),
		password: $('#txtPasswordAdd').val(),
		fullname: $('#txtFullnameAdd').val(),
		dob: datum,
		address: $("#txtAddressAdd").val(),
		roledId: $("#txtRoleAdd").val() === "ADMIN" ? 1 : $("#txtRoleAdd").val() === "STUDENT" ? 2 : 3
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-user',
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataUser();
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
$('#btnDeleteUserModal').on('click', function() {
	const dataToPost = {
		id: $('#txtIDUserDelete').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-user',
		type: 'DELETE',
		dataType: 'json',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataUser();
		Swal.fire({
			title: 'Xóa thành công',
			text: "",
			icon: 'success',
			confirmButtonText: 'Đóng'
		});
		$('#delete-user-modal').modal('toggle');
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


$("form#data").submit(function(e) {
    e.preventDefault();    
    var formData = new FormData(this);

    $.ajax({
        url: 'http://localhost:8080/school/admin/api-admin-user-array',
        type: 'POST',
        data: formData,
        cache: false,
        contentType: false,
        processData: false
    }).done(function(data) {
		GetDataUser();
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


$('#btnAddUserFileAction').on('click', function() {

	var data = new FormData();
	data.append('file', selectedFile);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-user-array',
		type: 'POST',
		contentType: 'multipart/form-data',
		data: data
	}).done(function(data) {
		//GetDataUser();
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
	//XLSX.utils.json_to_sheet(data, 'out.xlsx');
	/*if (selectedFile) {
		var fileReader = new FileReader();
		fileReader.readAsBinaryString(selectedFile);
		fileReader.onload = (event) => {
			var data = event.target.result;
			var workbook = XLSX.read(data, { type: "binary" });
			workbook.SheetNames.forEach(sheet => {
				var rowObject = XLSX.utils.sheet_to_row_object_array(workbook.Sheets[sheet]);
				for (row of rowObject){
					dataExcel.push(row);
				}
				//console.log(rowObject);
			});
		}
		console.log('list', dataExcel);
		console.log('data1', dataExcel[1]);
	}
	
	for (rowData of dataExcel){
		console.log('row', rowData);
	}*/

	/*var datum = Date.parse($("#txtDobAdd").val());
	const dataToPost = {
		id: +$("#txtIDUserAdd").val(),
		email: $('#txtEmailUserAdd').val(),
		fullname: $('#txtFullnameAdd').val(),
		dob: datum,
		address: $("#txtAddressAdd").val(),
		roledId: $("#txtRoleAdd").val() === "ADMIN" ? 1 : $("#txtRoleAdd").val() === "STUDENT" ? 2 : 3
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-user',
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataUser();
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
	});*/
});

/*====================================================================*/


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
		"ajax": rowDataUser,
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
	$("#txtIDCourseUpdate").val(dataTableRowCourse[0]);
	$("#txtNameCourseUpdate").val(dataTableRowCourse[1]);
	$("#txtCodeCourseUpdate").val(dataTableRowCourse[2]);
});

// add content for modal delete.
$("#delete-course-modal").on('shown.bs.modal', function() {
	$("#txtIDCourseDelete").val(dataTableRowCourse[0]);
	$("#txtNameCourseDelete").val(dataTableRowCourse[1]);
	$("#txtCodeCourseDelete").val(dataTableRowCourse[2]);
});
// gọi api cập nhật lớp học.
$('#btnUpdateCourseModal').on('click', function() {

	const dataToPost = {
		id: +$("#txtIDCourseUpdate").val(),
		name: $('#txtNameCourseUpdate').val(),
		code: $('#txtCodeCourseUpdate').val(),
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-course',
		type: 'PUT',
		dataType: 'json',
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
		id: +$("#txtIDCourseAdd").val(),
		name: $('#txtNameCourseAdd').val(),
		code: $('#txtCodeCourseAdd').val(),
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-course',
		type: 'POST',
		dataType: 'json',
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
		id: $('#txtIDCourseDelete').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-course',
		type: 'DELETE',
		dataType: 'json',
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

/*====================================================================*/

/*================================== TEACHER CLASSROOM ==================================*/
var classroomArray = [];
var tableTeacherClassroom;
var dataTableRowTeacherClassroom;
var rowDataTeacherClassroom;

$(document).ready(function() {
	tableTeacherClassroom = $('#teacherclassroom-table').DataTable({
		destroy: true,
		retrieve: true,
		"autoWidth": false,
		language: { "url": "//cdn.datatables.net/plug-ins/1.10.22/i18n/Vietnamese.json" },
		"ajax": rowDataTeacherClassroom,
		"columnDefs": [{
			"targets": -1,
			"data": null,
			"defaultContent": `<div class="btn-group" role="group">

															<button data-toggle="modal"
              data-target="#update-teacherclassroom-modal" type="button" class="btn btn-xs btn-info">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>

															<button data-toggle="modal"
              data-target="#delete-teacherclassroom-modal" type="button" class="btn btn-xs btn-danger">
																<i class="ace-icon fa fa-trash-o bigger-120"></i>
															</button>
														</div>`
		}]
	});
});


$('#btnGetTeacherClassroom').on('click', function() {
	GetDataTeacherClassroom();
});

$('#teacherclassroom-table tbody').on('click', 'tr', function() {
	dataTableRowTeacherClassroom = tableTeacherClassroom.row(this).data();
});

function GetDataTeacherClassroom() {
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-teacher',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json'
	}).done(function(data) {
		tableTeacherClassroom.clear().draw();
		for (teacherClassroom of data) {

			if (teacherClassroom.createdDate != null) {
				var dateC = new Date(teacherClassroom.createdDate);
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

			if (teacherClassroom.modifiedDate) {
				var date = new Date(teacherClassroom.modifiedDate);
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
			tableTeacherClassroom.row.add(
				[teacherClassroom.id, teacherClassroom.teacherId, teacherClassroom.studentId, teacherClassroom.classroomId, teacherClassroom.courseId, teacherClassroom.createdBy, dateTimeC, teacherClassroom.modifiedBy, dateTime]
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
$("#update-teacherclassroom-modal").on('shown.bs.modal', function() {
	$("#txtIDUpdateTeacherClassroom").val(dataTableRowTeacherClassroom[0]);
	$("#txtIDTeacherUpdate").val(dataTableRowTeacherClassroom[1]);
	$("#txtIDStudentUpdate").val(dataTableRowTeacherClassroom[2]);
	$("#txtIDClassroomUpdate").val(dataTableRowTeacherClassroom[3]);
	$("#txtIDCourseUpdate").val(dataTableRowTeacherClassroom[4]);
});

// add content for modal delete.
$("#delete-teacherclassroom-modal").on('shown.bs.modal', function() {
	$("#txtIDDeleteTeacherClassroom").val(dataTableRowTeacherClassroom[0]);
	$("#txtIDTeacherDelete").val(dataTableRowTeacherClassroom[1]);
	$("#txtIDStudentDelete").val(dataTableRowTeacherClassroom[2]);
	$("#txtIDClassroomDelete").val(dataTableRowTeacherClassroom[3]);
	$("#txtIDCourseDelete").val(dataTableRowTeacherClassroom[4]);
});

// Add new
$('#btnAddTeacherClassroom').on('click', function() {
	const dataToPost = {
		teacherId: $('#txtIDTeacherAdd').val(),
		studentId: $('#txtIDStudentAdd').val(),
		classroomId: $('#txtIDClassroomAdd').val(),
		courseId: $('#txtIDCourseAdd').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-teacher',
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataTeacherClassroom();
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
$('#btnUpdateTeacherClassroomModal').on('click', function() {
	const dataToPost = {
		teacherId: $('#txtIDTeacherUpdate').val(),
		studentId: $('#txtIDStudentUpdate').val(),
		classroomId: $('#txtIDClassroomUpdate').val(),
		courseId: $('#txtIDCourseUpdate').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-teacher',
		type: 'PUT',
		dataType: 'json',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataTeacherClassroom();
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
$('#btnDeleteTeacherClassroomModal').on('click', function() {
	const dataToPost = {
		id: $('#txtIDDeleteTeacherClassroom').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-teacher',
		type: 'DELETE',
		dataType: 'json',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataTeacherClassroom();
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

$("form#dataTeacher").submit(function(e) {
    e.preventDefault();    
    var formData = new FormData(this);

    $.ajax({
        url: 'http://localhost:8080/school/admin/api-admin-teacherclassroom-array',
        type: 'POST',
        data: formData,
        cache: false,
        contentType: false,
        processData: false
    }).done(function(data) {
		GetDataTeacherClassroom();
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



