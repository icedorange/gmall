function login() {
	var loginName = $('#loginName').val();
	var password = $('#password').val();
	$.ajax({
		type : "POST",
		url : "http://localhost:80/gmall/user/login.shtml",
		data : {
			"loginName" : loginName,
			"password" : password,
		},
		dataType : "json",
		success : function(data) {
			if (data.code == 1) {
				window.location.href = "home.html";
			} else {
				alert(data.msg);
			}
		}
	});
}