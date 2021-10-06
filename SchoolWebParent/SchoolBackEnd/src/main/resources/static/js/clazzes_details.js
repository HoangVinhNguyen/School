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

function delete_data_row(no, email, role) {
	var classId = $("#classId").text();
	var url = "http://localhost:8080/SchoolAdmin/clazzes/delete_user";
	var csrfValue = $("input[name='_csrf']").val();
	var params = { id: classId, email: email, _csrf: csrfValue };

	$.post(url, params, function(respone) {
		var result = respone;
		if (result !== "") {
			$("#data_table_" + role + " tr#row_" + role + no).remove();
			$("#data_row_less_" + role + no).remove();
			var table_len = $("#data_table_" + role + " > tbody > tr").length - 1;
			switch (role) {
				case "teacher":
					$("#numberOfTeacher").text(parseInt(table_len));
					break;
				case "student":
					$("#numberOfStudent").text(parseInt(table_len));
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
}

function delete_row(no, email, role) {
	if (email !== null && email !== "undefine" && email !== "") {
		showModalDialogConfirmDel("Confirm","Do you want delete " + role + " " + $("#fullname_" + role + no).text() + "?");
		$("#deleteButton").attr("onclick", "delete_data_row('"+no+"', '"+email+"', '"+role+"')");
	}
}

function add_row(role) {
		var classId = $("#classId").text();
		var new_email_less = $("#new_email_less_"+ role).val();
		var new_email = $("#new_email_"+ role).val();
		
	if (classId !== null && classId !== "undefine" && classId !== "" && role !== null && role !== "undefine") {
		var email;
		if (new_email_less !== null && new_email_less !== "") {
			email = new_email_less;
		} else {
			email = new_email;
		}
		var url = "http://localhost:8080/SchoolAdmin/clazzes/add_" + role;
		var csrfValue = $("input[name='_csrf']").val();
		var params = {id: classId, email: email, role : role, _csrf: csrfValue};
		
		var table_len = $("#data_table_" + role + " > tbody > tr").length - 1;
		var table_len_less = $("#data_less_" + role).children(".row").length;
			
		
		$.post(url, params, function(respone) {
			var user = respone;
			if (user !== "") {
				if (table_len === 0) {
					$("#data_table_" + role +" > tbody > tr").eq(table_len).before(
						"<tr id='row_"+ role+table_len+"'>"
						+ "<td id='id_"+ role+table_len+"'>" + user.id + "</td>"
						+ "<td id='fullname_"+ role+table_len+"'>" + user.fullName + "</td>"
						+ "<td id='email_"+ role+table_len+"'>" + user.email + "</td>"
						+ "<td id='course_"+ role+table_len+"'></td>"
						+ "<td>" + 
							"<a class='btn btn-info m-1' th:href=@{/users/edit/ + ${user.id}} target='_blank'><i class='fas fa-user-circle'></i></a>"
							+ "<button class='btn btn-danger m-1' onclick=delete_row('"+table_len+"','"+user.email+"','"+role+"');><i class='fas fa-trash'></i></button>"
						+ "</td>"
						+ "</tr"
					);
				} else if (table_len > 0) {
					table_len_before = table_len - 1;
					$("#data_table_" + role + " > tbody > tr").eq(table_len_before).after(
						"<tr id='row_"+ role+table_len+"'>"
						+ "<td id='id_"+ role+table_len+"'>" + user.id + "</td>"
						+ "<td id='fullname_"+ role+table_len+"'>" + user.fullName + "</td>"
						+ "<td id='email_"+ role+table_len+"'>" + user.email + "</td>"
						+ "<td id='course_"+ role+table_len+"'></td>"
						+ "<td>" + 
							"<a class='btn btn-info m-1' href='/SchoolAdmin/users/edit/" + user.id + "' target='_blank'><i class='fas fa-user-circle'></i></a>"
							+ "<button class='btn btn-danger m-1' onclick=delete_row('"+table_len+"','"+user.email+"','"+role+"');><i class='fas fa-trash'></i></button>"
						+ "</td>"
						+ "</tr"
					);
				}
				$("#data_less_" + role).append(
						"<div class='row m-1' id='data_row_less_"+role+ table_len_less +"'>"
						 	+ "<div class='col-8' id='data_col_less_"+role+ table_len_less +"'>"
							+	"<div id='fullname_less_"+role + table_len_less +"'>" + user.fullName + "</div>"
							+	"<div id='email_less_"+role + table_len_less +"'>" + user.email + "</div>"
							+	"<div id='course_less_"+role + table_len_less +"'></div>"
							+	"<div class='mt-2'>"
							+		"<a class='btn btn-info m-1' href='/SchoolAdmin/users/edit/"+ user.id +"' target='_blank'>"
							+			"<i class='fas fa-user-circle'></i>"
							+		"</a>"
							+		"<button class='btn btn-danger m-1' onclick=delete_row('"+table_len_less+"','"+user.email+"','"+role+"');>"
							+			"<i class='fas fa-trash'></i>"
							+		"</button>"
							+	"</div>"
							+ "</div>"
							+ "</div>"
				);
				switch (role) {
					case "teacher":
						$("#numberOfTeacher").text(parseInt(table_len) + 1);
						break;
					case "student":
						$("#numberOfStudent").text(parseInt(table_len) + 1);
						break;
				}
				$("#new_email_"+ role).val("");
				$("#new_email_less_"+ role).val("");
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
	var url = "http://localhost:8080/SchoolAdmin/clazzes/check_user_inclass";
	var csrfValue = $("input[name='_csrf']").val();
	var params = {id: classId, email: new_email, _csrf: csrfValue};
	
	if (classId !== null && classId !== "undefine" && classId !== "") {
		$.post(url, params, function(respone) {
			var user = respone;
			if (user === false) {
				add_row(role);
			} else {
				showModalDialog("Error", "This User is duplicated or is not a " + role);
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
