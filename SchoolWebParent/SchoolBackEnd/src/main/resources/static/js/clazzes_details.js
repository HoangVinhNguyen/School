/**
 * FOR CLASSES_DETAILS
 */
function edit_row(no) {
	$("#edit_button" + no).hide();
	$("#save_button" + no).show();
	var email = $("#email" + no).text();
	$("#email" + no).html("<input type='text' id='emailInput" + no + "' value='" + email + "'>");
}

/*function save_row(no) {
	var email = $("#emailInput" + no).val();
	var classId = $("#classId").text();
	var url = "http://localhost:8080/SchoolAdmin/clazzes/add_user";
	var csrfValue = $("input[name='_csrf']").val();
	var params = {id: classId, email: email, _csrf: csrfValue};
	
	$.post(url, params, function(respone) {
		var user = respone;
		if (user !== "") {
			$("#fullname" + no).text(user.fullName);
			$("#email" + no).text(user.email);
			$("#course" + no).text(user.course);
			$("#save_button" + no).hide();
			$("#edit_button" + no).show();
		} else {
			showModalDialog("Error", "Not found User with this email");
		}
	}).fail(function() {
		showModalDialog("Error", "Could not connect to the server");
	});
}*/

function delete_row(no, email, role) {
	if (email !== null && email !== "undefine" && email !== "") {
		showModalDialogConfirmDel("Confirm","Do you want delete " + role + " " + $("#fullname_" + role + no).text() + "?");
		$("#deleteButton").on("click", function(e) {
			var classId = $("#classId").text();
			var url = "http://localhost:8080/SchoolAdmin/clazzes/delete_user";
			var csrfValue = $("input[name='_csrf']").val();
			var params = {id: classId, email: email, _csrf: csrfValue};
			
			$.post(url, params, function(respone) {
				var result = respone;
				if (result !== "") {
					$("#data_table_" + role + " tr#row_" + role + no).remove();
					switch (role) {
						case "teacher":
							let numberT = $("#numberOfTeacher").text();
							$("#numberOfTeacher").text(parseInt(numberT) - 1);
							break;
						case "student":
							let numberS = $("#numberOfStudent").text();
							$("#numberOfStudent").text(parseInt(numberS) - 1);
							break;
					}
				} else {
					showModalDialog("Error", "Can not delete this User");
				}
			}).fail(function(xhr, ajaxOptions, thrownError) {
				console.log("xhr " + xhr);
				console.log("ajaxOptions " + ajaxOptions);
				console.log("thrownError " + thrownError);
				showModalDialog("Error", "Could not connect to the server");
			});
			$("#modalDialogConfirm").modal("toggle");
		});
	}
}

function add_row(role) {
	if (classId !== null && classId !== "undefine" && classId !== "" && role !== null && role !== "undefine") {
		var new_fullname = $("#new_fullname").val();
		var new_email = $("#new_email_"+ role).val();
		var new_course = $("#new_course").val();
		var classId = $("#classId").text();
		
		var url = "http://localhost:8080/SchoolAdmin/clazzes/add_user";
		var csrfValue = $("input[name='_csrf']").val();
		var params = {id: classId, email: new_email, role : role, _csrf: csrfValue};
		
		var table_len = $("#data_table_" + role + " > tbody > tr").length - 1;
		var controlButton = "<button class='delete btn btn-danger m-1' onclick=delete_row('"+table_len+"','"+new_email+"','"+role+"')><i class='fas fa-trash'></i></button>";
		var controlButton2 = "<button id='edit_button"+table_len+"' class='edit btn btn-secondary m-1' onclick='edit_row("+table_len+")'><i class='fas fa-pen'></i></button>"
							+ "<button id='save_button"+table_len+"' class='save btn btn-primary m-1' onclick='save_row("+table_len+")'><i class='fas fa-save'></i></button>"
							+ "<button class='delete btn btn-danger m-1' onclick='delete_row("+table_len+")'><i class='fas fa-trash'></i></button>";
		$.post(url, params, function(respone) {
			var user = respone;
			if (user !== "") {
				if (table_len === 0) {
					$("#data_table_" + role +" > tbody > tr").eq(table_len).before(
						"<tr id='row_"+ role+table_len+"'>"
						+ "<td id='fullname_"+ role+table_len+"'>" + user.fullName + "</td>"
						+ "<td id='email_"+ role+table_len+"'>" + user.email + "</td>"
						+ "<td id='course_"+ role+table_len+"'>" + new_course + "</td>"
						+ "<td>" + controlButton + "</td>"
						+ "</tr"
					);
				} else if (table_len > 0) {
					table_len_before = table_len - 1;
					$("#data_table_" + role + " > tbody > tr").eq(table_len_before).after(
						"<tr id='row_"+ role+table_len+"'>"
						+ "<td id='fullname_"+ role+table_len+"'>" + user.fullName + "</td>"
						+ "<td id='email_"+ role+table_len+"'>" + user.email + "</td>"
						+ "<td id='course_"+ role+table_len+"'>" + new_course + "</td>"
						+ "<td>" + controlButton + "</td>"
						+ "</tr"
					);
				}
				switch (role) {
					case "teacher":
						let numberT = $("#numberOfTeacher").text();
						$("#numberOfTeacher").text(parseInt(numberT) + 1);
						break;
					case "student":
						let numberS = $("#numberOfStudent").text();
						$("#numberOfStudent").text(parseInt(numberS) + 1);
						break;
				}
				$("#new_email_"+ role).val("");
			} else {
				showModalDialog("Error", "Not found User with this email");
			}
		}).fail(function(xhr, ajaxOptions, thrownError) {
			console.log("xhr " + xhr);
			console.log("ajaxOptions " + ajaxOptions);
			console.log("thrownError " + thrownError);
			showModalDialog("Error", "Could not connect to the server");
		});
	}
}

function check_unique_email_inClass(role) {
	var classId = $("#classId").text();
	var new_email = $("#new_email_"+ role).val();
	var url = "http://localhost:8080/SchoolAdmin/clazzes/check_user_inClass";
	var csrfValue = $("input[name='_csrf']").val();
	var params = {id: classId, email: new_email, _csrf: csrfValue};
	
	if (classId !== null && classId !== "undefine" && classId !== "") {
		$.post(url, params, function(respone) {
			var user = respone;
			if (user === false) {
				add_row(role);
			} else {
				showModalDialog("Error", "This User is duplicated");
			}
		}).fail(function(xhr, ajaxOptions, thrownError) {
			console.log("xhr " + xhr);
			console.log("ajaxOptions " + ajaxOptions);
			console.log("thrownError " + thrownError);
			showModalDialog("Error", "Could not connect to the server");
		});
	}
}

function showModalDialogConfirmDel(title, message) {
	$("#modalTitleConfirm").text(title);
	$("#modalBodyConfirm").text(message);
	$("#modalDialogConfirm").modal();
}

function showModalDialog(title, message) {
	$("#modalTitle").text(title);
	$("#modalBody").text(message);
	$("#modalDialog").modal();
}
