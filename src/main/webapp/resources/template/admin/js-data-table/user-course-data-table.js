/**
 * 
 */

/*================================== TEACHER CLASSROOM ==================================*/
var classroomArray = [];
var tableUserCourse;
var dataTableRowUserCourse;
var rowDataUserCourse;

$(document).ready(function() {
	tableUserCourse = $('#userCourse-table').DataTable({
		destroy: true,
		retrieve: true,
		"autoWidth": false,
		language: { "url": "//cdn.datatables.net/plug-ins/1.10.22/i18n/Vietnamese.json" },
		"ajax": rowDataUserCourse,
		"columnDefs": [{
			"targets": -1,
			"data": null,
			"defaultContent": `<div class="btn-group" role="group">

															<button data-toggle="modal"
              data-target="#update-userCourse-modal" type="button" class="btn btn-xs btn-info">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>

															<button data-toggle="modal"
              data-target="#delete-userCourse-modal" type="button" class="btn btn-xs btn-danger">
																<i class="ace-icon fa fa-trash-o bigger-120"></i>
															</button>
														</div>`
		}]
	});
});


$('#btnGetUserCourse').on('click', function() {
	GetDataUserCourse();
});

$('#userCourse-table tbody').on('click', 'tr', function() {
	dataTableRowUserCourse = tableUserCourse.row(this).data();
});

function GetDataUserCourse() {
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-user-course',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json'
	}).done(function(data) {
		tableUserCourse.clear().draw();
		for (userCourse of data) {
			if (userCourse.createdDate != null) {
				var dateC = new Date(userCourse.createdDate);
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

			if (userCourse.modifiedDate) {
				var date = new Date(userCourse.modifiedDate);
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
			tableUserCourse.row.add(
				[userCourse.id, userCourse.teacherId, userCourse.studentId, userCourse.classroomId, userCourse.courseId, userCourse.createdBy, dateTimeC, userCourse.modifiedBy, dateTime]
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
$("#update-userCourse-modal").on('shown.bs.modal', function() {
	$("#txtIDUpdateUserCourse").val(dataTableRowUserCourse[0]);
	$("#txtIDTeacherUpdate").val(dataTableRowUserCourse[1]);
	$("#txtIDStudentUpdate").val(dataTableRowUserCourse[2]);
	$("#txtIDClassroomUpdate").val(dataTableRowUserCourse[3]);
	$("#txtIDCourseUpdate").val(dataTableRowUserCourse[4]);
});

// add content for modal delete.
$("#delete-userCourse-modal").on('shown.bs.modal', function() {
	$("#txtIDDeleteUserCourse").val(dataTableRowUserCourse[0]);
	$("#txtIDTeacherDelete").val(dataTableRowUserCourse[1]);
	$("#txtIDStudentDelete").val(dataTableRowUserCourse[2]);
	$("#txtIDClassroomDelete").val(dataTableRowUserCourse[3]);
	$("#txtIDCourseDelete").val(dataTableRowUserCourse[4]);
});

// Add new
$('#btnAddUserCourse').on('click', function() {
	const dataToPost = {
		teacherId: $('#txtIDTeacherAdd').val(),
		studentId: $('#txtIDStudentAdd').val(),
		classroomId: $('#txtIDClassroomAdd').val(),
		courseId: $('#txtIDCourseAdd').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-user-course',
		type: 'POST',
		dataType: 'text',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataUserCourse();
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
$('#btnUpdateUserCourseModal').on('click', function() {
	const dataToPost = {
		teacherId: $('#txtIDTeacherUpdate').val(),
		studentId: $('#txtIDStudentUpdate').val(),
		classroomId: $('#txtIDClassroomUpdate').val(),
		courseId: $('#txtIDCourseUpdate').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-user-course',
		type: 'PUT',
		dataType: 'text',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataUserCourse();
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
$('#btnDeleteUserCourseModal').on('click', function() {
	const dataToPost = {
		id: $('#txtIDDeleteUserCourse').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-user-course',
		type: 'DELETE',
		dataType: 'text',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		GetDataUserCourse();
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
		url: 'http://localhost:8080/school/admin/api-admin-userCourse-array',
		type: 'POST',
		data: formData,
		cache: false,
		contentType: false,
		processData: false
	}).done(function(data) {
		GetDataUserCourse();
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
