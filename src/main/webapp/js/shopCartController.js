$(document).ready(function(){	
	//购物车
	$.ajax({
		type : "POST",
		url : $.gmallHost+"/cart/showCart.shtml",
		async : false,
		dataType: "json",
		success : function(obj) {
				if(obj.code==1){
					if (obj.data != null) {
						var app4 = new Vue({
							  el: '#cartTable',
							  data: {
							    todos: obj.data.cartGoodsVo
							  }
						});
					}
				} else {
					alert(obj.msg);
				}
			}
		}
	);
});