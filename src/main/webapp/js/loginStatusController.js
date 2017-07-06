$(document).ready(function(){	
	//登录状态	
	$.ajax({
		type : "POST",
		url : $.gmallHost+"/user/loginStatus.shtml",
		dataType : "json",
		success : function(obj) {	
			if (obj.code == 1) {			
				if (obj.data == null) {
					$(".menu-hd:first").html("<a href=\"login.html\" target=\"_top\" class=\"h\">亲，请登录 </a>" +
					"<a href=\"register.html\" target=\"_top\">免费注册</a>");
					$("#mc-menu-hd").attr("href","http://localhost:80/gmall/home/login.html");
					$("#shopCart a").attr("href","http://localhost:80/gmall/home/login.html");
				}else {
					if(obj.data.username !=null){
						$(".menu-hd:first").html("你好! " + obj.data.username +" " + "<a onclick=\"logout();\">退出</a>");		
					}else if(obj.data.mobile !=null){
						$(".menu-hd:first").html("你好! " + obj.data.mobile +" " + "<a onclick=\"logout();\">退出</a>");	
					}else if(!obj.data.email!=null){
						$(".menu-hd:first").html("你好! " + obj.data.email +" " + "<a onclick=\"logout();\">退出</a>");
					}
				}					
			} else {
				alert(obj.msg);
			}
		}
	});
});
//登出
function logout() {
	$.ajax({
		type : "POST",
		url : $.gmallHost+"/user/logout.shtml",
		dataType : "json",
		success : function(obj) {
			window.location.href=$.gmallHost+"/home/login.html";
		}
	});
}