/**
 * 
 */
// Login.
$('#btnLogin').on('click', function() {
	const dataToPost = {
		"email": $('#email').val(),
		"password": $('#password').val()
	}
	const jsonToPost = JSON.stringify(dataToPost);
	console.log(jsonToPost);
	$.ajax({
		url: 'http://localhost:8080/school-social/api-login',
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: jsonToPost
	}).done(function(res) {
		if (res.authenticated === true) {
			console.log('res', 	res);
			Swal.fire({
				title: 'Done!',
				text: "",
				icon: 'success',
				confirmButtonText: 'OK'
			});
		}
	}).fail(function(jqXHR, textStatus, err) {
		console.error(jqXHR);
		console.error(textStatus);
		console.error(err);
	});
});