function emailRegister() {
	if(!$('#reader-me').prop('checked')){
		alert("请点击同意商城协议！");
		return ;
	}			
	var email = $('#email').val();
	var password = $('#password').val();
	var passwordRepeat = $('#passwordRepeat').val();
	$.ajax({
			type : "POST",
			url : "http://localhost:80/gmall/user/registerByEmail.shtml",
			data : {
				"email" : email,
				"password" : password,
				"passwordRepeat" : passwordRepeat
			},
			dataType : "json",
			success : function(data) {
				if (data.code == 1) {
					window.location.href="login.html";
				} else {
					alert(data.msg);
				}
			}
	});
}
	
function phoneRegister() {
	if(!$('#reader-me1').prop('checked')){
		alert("请点击同意商城协议！");
		return ;
	}
	var mobile = $('#phone').val();
	var code = $('#code').val();
	var password = $('#password1').val();
	var passwordRepeat = $('#passwordRepeat1').val();
	$.ajax({
			type : "POST",
			url : "http://localhost:80/gmall/user/registerByMobile.shtml",
			data : {
				"mobile" : mobile,
				"code" : code,
				"password" : password,
				"passwordRepeat" : passwordRepeat
			},
			dataType : "json",
			success : function(data) {
				if (data.code == 1) {
					window.location.href="login.html";
				} else {
					alert(data.msg);
				}
			}
	});
}

$(document).ready(function(){
	$('#sendMobileCode').on('click',sendMobileCode);
});

function sendMobileCode() {
	var phone = $('#phone').val();
	var phoneReg = /^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\D])|(18[0,5-9]))\d{8}$/;
	if (!phoneReg.test(phone)) {
		alert("请输入有效的手机号");
		return;
	}
	$.ajax({
		type : "POST",
		url : "http://localhost:80/gmall/user/createCode.shtml",
		data : {
			"phone" : $('#phone').val()
		},
		dataType : "json",
		success : function(data) {
			$('#sendMobileCode').off('click');
			var time = 5;
			var setTime = setInterval(function() {
				if (time <= 0) {
					$('#sendMobileCode').on('click', sendMobileCode);
					$("#dyMobileButton").text("获取");
					clearInterval(setTime);
					return;
				}

				time--;
				$("#dyMobileButton").text(time);
			}, 1000);
		}
	});
}