function addCart() {
	//购物车
	$.ajax({
		type : "POST",
		url : $.gmallHost+"/cart/addCart.shtml",
		async : false,
		data : {"gid" : document.URL.getQuery("id"),"number" : $("#text_box").val()},
		dataType: "json",
		success : function(obj) {
				if(obj.code==1){
					$.ajax({
						type : "POST",
						url : $.gmallHost+"/cart/showCart.shtml",
						async : false,
						dataType: "json",
						success : function(obj) {
								if(obj.code==1){
									var numberCart = 0;
									for (var i=0; i<obj.data.cartGoodsVo.length; i++)
									{
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
				} else {
					alert(obj.msg);
				}
			}
		}
	);
}