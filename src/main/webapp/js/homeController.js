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
					$(".member-login").hide();
					$(".member-logout").show();
					$("#mc-menu-hd").attr("href","http://localhost:80/gmall/home/login.html");
					$("#shopCart a").attr("href","http://localhost:80/gmall/home/login.html");
					return;
				}		
				if(obj.data.username !=null){
					$(".menu-hd:first").html("你好! " + obj.data.username +" " + "<a onclick=\"logout();\">退出</a>");
					$(".member-logout").hide();
					$(".member-login").show();
					$(".s-name").text(obj.data.username);
					$.ajax({
						type : "POST",
						url : $.gmallHost+"/cart/showCart.shtml",
						async : false,
						dataType: "json",
						success : function(obj) {
								if(obj.code==1){
									var numberCart = 0;
									for (var i=0; i<obj.data.cartGoodsVo.length; i++) {
										numberCart = numberCart +1;
									 }
									$("#mc-menu-hd").attr("href","http://localhost:80/gmall/home/shopcart.html");
									$("#shopCart a").attr("href","http://localhost:80/gmall/home/shopcart.html");
									$("#J_MiniCartNum").text(numberCart);
									$("#shopCart .cart_num").text(numberCart);
								} else {
									alert(obj.msg);
								}
							}
						}
					);
				}else if(obj.data.mobile !=null){
					$(".menu-hd:first").html("你好! " + obj.data.mobile +" " + "<a onclick=\"logout();\">退出</a>");
					$(".member-logout").hide();
					$(".member-login").show();
					$(".s-name").text(obj.data.mobile);
					$.ajax({
						type : "POST",
						url : $.gmallHost+"/cart/showCart.shtml",
						async : false,
						dataType: "json",
						success : function(obj) {
								if(obj.code==1){
									var number = 0;
									var numberCart = 0;
									for (var i=0; i<obj.data.cartGoodsVo.length; i++)
									{
										numberCart = numberCart +1;
									 }
									$("#J_MiniCartNum").text(numberCart);
									$("#shopCart .cart_num").text(numberCart);
									
								} else {
									alert(obj.msg);
								}
							}
						}
					);
				}else if(!obj.data.email!=null){
					$(".menu-hd:first").html("你好! " + obj.data.email +" " + "<a onclick=\"logout();\">退出</a>");
					$(".member-logout").hide();
					$(".member-login").show();
					$(".s-name").text(obj.data.email);
					$.ajax({
						type : "POST",
						url : $.gmallHost+"/cart/showCart.shtml",
						async : false,
						dataType: "json",
						success : function(obj) {
								if(obj.code==1){
									var number = 0;
									var numberCart = 0;
									for (var i=0; i<obj.data.cartGoodsVo.length; i++)
									{
										numberCart = numberCart +1;
									 }
									$("#J_MiniCartNum").text(numberCart);
									$("#shopCart .cart_num").text(numberCart);
								} else {
									alert(obj.msg);
								}
							}
						}
					);
				}
			} else {
				alert(obj.msg);
			}					
		}
	});
	//渲染类目	
	var conent = "<div class=\"category-info\">"
				+ "<h3 class=\"category-name b-category-name\"><i><img src=\"../images/cake.png\"></i><a class=\"ml-22\" title=\"点心\">点心/蛋糕</a></h3>"
				+ "<em>&gt;</em></div>"
				+ "<div class=\"menu-item menu-in top\">"
				+ "<div class=\"area-in\">"
				+ "<div class=\"area-bg\">"
				+ "<div class=\"menu-srot\">"
				+ "<div class=\"sort-side\">" + "</div>" + "</div>"
				+ "</div>" + "</div>" + "</div>"
				+ "<b class=\"arrow\"></b>";
	
	var categoryFirst = "<li class=\"appliance js_toggle relative first\">"+conent+"</li>";
	
	var categoryLast = "<li class=\"appliance js_toggle relative last\">"+conent+"</li>";
	
	$.ajax({
		type : "POST",
		url : $.gmallHost+"/category/showCategory.shtml",
		async : false,
		dataType: "json",
		success : function(obj) {
				if(obj.code==1){	
					$(".category-content .category-list").html("");
					
					var firstLevel = obj.data;
					//一级类目
					for (var i = 0; i < firstLevel.length; i++) {
						//alert(firstLevel[i].name);
						$(".category-content .category-list").append(categoryFirst);
						if(i==0){		
						}else if (i==firstLevel.length-1) {
							$(".category-content .category-list li:last").removeClass("first").addClass("last");
						}else {
							$(".category-content .category-list:last").removeClass("first");
						}
						$(".category-content .category-list .ml-22:last").html(firstLevel[i].name).attr("title",firstLevel[i].name);
						//二级类目
						var secondLevel = firstLevel[i].categoryVoList;
						for (var j = 0; j < secondLevel.length; j++) {
							$(".category-content .category-list .sort-side:last").append("<dl class=\"dl-sort\">"+
									"<dt><span title=\""+secondLevel[j].name+"\">"+secondLevel[j].name+"</span></dt>"+
								"</dl>");
							//三级类目
							var thirdLevel = secondLevel[j].categoryVoList;
							for (var k = 0; k < thirdLevel.length; k++) {
								$(".category-content .category-list .dl-sort:last").append("<dd><a title=\""+thirdLevel[k].name+"\" href=\"#\"><span>"+thirdLevel[k].name+"</span></a></dd>");
							}
						}
					}	
				} else {
					alert(obj.msg);
				}
			}
		}
	);	
	//列表
	$.ajax({
		type : "POST",
		url : $.gmallHost+"/goods/goodsList.shtml",
		async : false,
		dataType: "json",
		success : function(obj) {
				if(obj.code==1){
					$.each(obj.data, function (index, object) { 						
							$("#goods"+(index+1)+" .title").html(object.product);
							$("#goods"+(index+1)+" .sub-title").html("¥"+object.promotionPrice/100);
							$("#goods"+(index+1)+" img").attr("src","http://localhost:80/gmall/"+ object.picture);
							$("#goods"+(index+1)+" a").attr("href","http://localhost:80/gmall/home/introduction.html?id="+ object.id);
							
					});
				} else {
					alert(obj.msg);
				}
			}
		}
	);
	
	
});
//登出
function logout() {
	$.ajax({
		type : "POST",
		url : $.gmallHost+"/user/logout.shtml",
		dataType : "json",
		success : function(obj) {
			window.location.href=$.gmallHost+"/home/home.html";
		}
	});
}


