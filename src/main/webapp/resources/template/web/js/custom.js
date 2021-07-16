var table;
var dataTableRow;
var rowData;
var className;
var courseName;

$(document).ready(function() {

	$.ajax({
		url: 'http://localhost:8080/school/api-web-point',
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json'
	}).done(function(data) {
		console.log(data);
		for (className of data['listCourseName']) {

			$('#courseTitle').append(`
				<a href="#" class="list-group-item" onClick="aClick('${className}')">${className}</a>
			`);
		}
		$('#titleClassroom').append(data['listClassName'][0]);
		className = data['listClassName'][0];
		courseName = data['listCourseName'][0]

	}).fail(function(jqXHR, textStatus, err) {
		console.error(jqXHR);
		console.error(textStatus);
		console.error(err);
	});

	table = $('#point-table').DataTable({
		destroy: true,
		retrieve: true,
		"autoWidth": false,
		language: { "url": "//cdn.datatables.net/plug-ins/1.10.22/i18n/Vietnamese.json" }
	});
});


function callAjaxCourse(courseName) {
	const dataToPost = {
		name: courseName
	}
	const jsonToPost = JSON.stringify(dataToPost);
	$.ajax({
		url: 'http://localhost:8080/school/api-web-point',
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(data) {
		table.clear().draw();
		if (data.createdDate != null) {
			var dateC = new Date(data.createdDate);
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

		if (data.modifiedDate) {
			var date = new Date(data.modifiedDate);
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
			[data.id, data.studentId, className, data.point, data.createdBy, dateTimeC, data.modifiedBy, dateTime]
		).draw(false);
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


function aClick(courseName) {
	console.log(courseName)
	callAjaxCourse(courseName);
}

