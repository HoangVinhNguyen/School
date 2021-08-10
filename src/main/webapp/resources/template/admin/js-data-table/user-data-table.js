/**
 * 
 */

/*================================== USER ==================================*/
var tableUser;
var dataTableRowUser;
var rowDataUser;
var dataExcel = ['a'];

$(document).ready(function() {
	/*tableUser = $('#user-table').DataTable({
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
	});*/
	checkedNavItem();
});

function checkedNavItem() {
	$('.nav-link').each(function() {
		if (window.location.href.search($(this).attr("href")) !== -1) {
			$(this).removeClass("active");
			$(this).addClass("active");
		}
		else {
			$(this).removeClass("active");
		}
	});
};


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
				[user.id, user.email, user.fullname, dateTimeD, user.phone, user.address, user.role.name, user.createdBy, dateTimeC, user.modifiedBy, dateTime]
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
	$('#txtPhoneUpdate').val(dataTableRowUser[4]);
	$("#txtAddressUpdate").val(dataTableRowUser[5]);
	$("#txtRoleUpdate").val(dataTableRowUser[6]);
});

// add content for modal delete.
$("#delete-user-modal").on('shown.bs.modal', function() {
	$("#txtIDUserDelete").val(dataTableRowUser[0]);
	$("#txtEmailUserDelete").val(dataTableRowUser[1]);
	$("#txtFullnameDelete").val(dataTableRowUser[2]);
	$("#txtDobDelete").val(dataTableRowUser[3]);
	$('#txtPhoneDelete').val(dataTableRowUser[4]);
	$("#txtAddressDelete").val(dataTableRowUser[5]);
	$("#txtRoleDelete").val(dataTableRowUser[6]);
});
// gọi api cập nhật lớp học.
$('#btnUpdateUserModal').on('click', function() {
	var datum = Date.parse($("#txtDobUpdate").val());
	const dataToPost = {
		id: +$("#txtIDUserUpdate").val(),
		email: $('#txtEmailUserUpdate').val(),
		fullname: $('#txtFullnameUpdate').val(),
		dob: datum,
		phone: $('#txtPhoneUpdate').val(),
		address: $("#txtAddressUpdate").val(),
		roleId: $("#txtRoleUpdate").val() === "ADMIN" ? 1 : $("#txtRoleUpdate").val() === "STUDENT" ? 2 : 3
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-user',
		type: 'PUT',
		dataType: 'text',
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
		phone: $('#txtPhoneAdd').val(),
		address: $("#txtAddressAdd").val(),
		roleId: $("#txtRoleAdd").val() === "ADMIN" ? 1 : $("#txtRoleAdd").val() === "STUDENT" ? 2 : 3
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/admin/api-admin-user',
		type: 'POST',
		dataType: 'text',
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
		dataType: 'text',
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

$('#btnAddUserList').on('click', function(e) {
	e.preventDefault();
	let url;
	if ($('#rdTeacher:checked').val() === 'teacher') {
		url = 'http://localhost:8080/school/admin/api-admin-user-file-teacher';
	}
	else if ($('#rdStudent:checked').val() === 'student') {
		url = 'http://localhost:8080/school/admin/api-admin-user-file-student';
	}
	let formData = new FormData($('#data')[0]);
	/*let data = $('#data').serialize();
	formData.append("form", data);*/
	$.ajax({
		url: url,
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
	/*$("form#data").submit(function(e) {
		e.preventDefault();

	});*/
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

$('#openSendFile').on('click', function() {
	$('#file').click();
});
$('#file').change(function() {
	let name = $('#file').val().split('\\');
	$('#fileName').val(name[name.length - 1]);
});

/*====================================================================*/
