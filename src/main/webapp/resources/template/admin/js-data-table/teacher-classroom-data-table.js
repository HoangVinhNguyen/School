/**
 * 
 */

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
