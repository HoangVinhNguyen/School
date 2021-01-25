var table;
var dataTableRow;
var rowData;
var className;

$(document).ready(function() {

	$.ajax({
		url: 'http://localhost:8080/school/api-teacher-inclassroom',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json'
	}).done(function(data) {
		for (className of data['listClassName']) {

			$('#listMyClassroom').append(`
				<li>
                    <a id=${className} href="#" onClick="aClick('${className}')">
                        <i class="menu-icon fa fa-caret-right"></i>
                        ${className}
                    </a>
                </li>
			`);
		}
		$('#headerName').append(data['listCourseName'][0]);

	}).fail(function(jqXHR, textStatus, err) {
		console.error(jqXHR);
		console.error(textStatus);
		console.error(err);
	});

	table = $('#point-literature-table').DataTable({
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
              data-target="#update-point-modal" type="button" class="btn btn-xs btn-info">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>`
		}]
	});
});

function callAjaxClassroom(className) {
	const dataToPost = {
		name: className
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/api-teacher-inclassroom',
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		table.clear().draw();
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
			table.row.add(
				[inclassroom.id, inclassroom.studentId, inclassroom.classroomId, inclassroom.point, inclassroom.createdBy, dateTimeC, inclassroom.modifiedBy, dateTime]
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


function aClick(idTagA) {
	className = idTagA;
	callAjaxClassroom(className);
	var myElem = document.getElementById('liTitleCourse');
	if (myElem !== null) {
		$('#liTitleCourse').empty();
		$('#liTitleCourse').append(className);
	}
}

function GetDataClassroom() {

}

$('#point-literature-table tbody').on('click', 'tr', function() {
	dataTableRow = table.row(this).data();
});

// add content for modal update.
$("#update-point-modal").on('shown.bs.modal', function() {
	$("#txtIDUpdatePoint").val(dataTableRow[0]);
	$("#txtIDStudentPointUpdate").val(dataTableRow[1]);
	$("#txtIDClassroomPointUpdate").val(dataTableRow[2]);
	$("#txtPointUpdate").val(dataTableRow[3]);
});

$('#btnUpdatePointModal').on('click', function() {
	const dataToPost = {
		id: +$("#txtIDUpdatePoint").val(),
		point: +$("#txtPointUpdate").val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/api-teacher-inclassroom',
		type: 'PUT',
		dataType: 'json',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		var nameclass = $('#liTitleCourse').text();
		console.log(nameclass);
		callAjaxClassroom(nameclass);
		Swal.fire({
			title: 'Cập nhật thành công',
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
});

