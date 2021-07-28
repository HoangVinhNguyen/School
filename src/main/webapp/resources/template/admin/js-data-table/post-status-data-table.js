/**
 * 
 */

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
