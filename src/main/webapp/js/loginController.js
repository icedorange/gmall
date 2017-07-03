function login() {
	var loginName = $('#loginName').val();
	var password = $('#password').val();
	$.ajax({
		type : "POST",
		url :$.gmallHost+"/user/login.shtml",
		data : {
			"loginName" : loginName,
			"password" : password,
		},
		dataType : "json",
		success : function(data) {
			if (data.code == 1) {
				window.location.href = $.gmallHost+"/home/home.html";
			} else {
				alert(data.msg);
			}
		}
	});
}