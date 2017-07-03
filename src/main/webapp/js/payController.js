$(document).ready(function(){
	$.ajax({
		type : "POST",
		url : $.gmallHost+"/cart/showCart.shtml",
		async : false,
		data : {"isSelected" : 1},
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

