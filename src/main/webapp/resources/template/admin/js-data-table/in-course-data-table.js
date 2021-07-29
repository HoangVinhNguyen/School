/**
 * 
 */

/*================================== TEACHER CLASSROOM ==================================*/
var classroomArray = [];
var tableInCourse;
var dataTableRowInCourse;
var rowDataInCourse;

$(document).ready(function() {
	tableInCourse = $('#teacherclassroom-table').DataTable({
		destroy: true,
		retrieve: true,
		"autoWidth": false,
		language: { "url": "//cdn.datatables.net/plug-ins/1.10.22/i18n/Vietnamese.json" },
		"ajax": rowDataInCourse,
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


$('#btnGetInCourse').on('click', function() {
	GetDataInCourse();
});

$('#teacherclassroom-table tbody').on('click', 'tr', function() {
	dataTableRowInCourse = tableInCourse.row(this).data();
});

function GetDataInCourse() {
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-in-course',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json'
	}).done(function(data) {
		tableInCourse.clear().draw();
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
			tableInCourse.row.add(
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
	$("#txtIDUpdateInCourse").val(dataTableRowInCourse[0]);
	$("#txtIDTeacherUpdate").val(dataTableRowInCourse[1]);
	$("#txtIDStudentUpdate").val(dataTableRowInCourse[2]);
	$("#txtIDClassroomUpdate").val(dataTableRowInCourse[3]);
	$("#txtIDCourseUpdate").val(dataTableRowInCourse[4]);
});

// add content for modal delete.
$("#delete-teacherclassroom-modal").on('shown.bs.modal', function() {
	$("#txtIDDeleteInCourse").val(dataTableRowInCourse[0]);
	$("#txtIDTeacherDelete").val(dataTableRowInCourse[1]);
	$("#txtIDStudentDelete").val(dataTableRowInCourse[2]);
	$("#txtIDClassroomDelete").val(dataTableRowInCourse[3]);
	$("#txtIDCourseDelete").val(dataTableRowInCourse[4]);
});

// Add new
$('#btnAddInCourse').on('click', function() {
	const dataToPost = {
		teacherId: $('#txtIDTeacherAdd').val(),
		studentId: $('#txtIDStudentAdd').val(),
		classroomId: $('#txtIDClassroomAdd').val(),
		courseId: $('#txtIDCourseAdd').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-in-course',
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataInCourse();
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
$('#btnUpdateInCourseModal').on('click', function() {
	const dataToPost = {
		teacherId: $('#txtIDTeacherUpdate').val(),
		studentId: $('#txtIDStudentUpdate').val(),
		classroomId: $('#txtIDClassroomUpdate').val(),
		courseId: $('#txtIDCourseUpdate').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-in-course',
		type: 'PUT',
		dataType: 'json',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataInCourse();
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
$('#btnDeleteInCourseModal').on('click', function() {
	const dataToPost = {
		id: $('#txtIDDeleteInCourse').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-in-course',
		type: 'DELETE',
		dataType: 'json',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataInCourse();
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
		GetDataInCourse();
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
