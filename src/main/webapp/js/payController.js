$(document).ready(function(){
	
	$.ajax({
		type : "POST",
		url : $.gmallHost+"/pay/payType.shtml",
		async : false,
		dataType: "json",
		success : function(obj) {
				if(obj.code==1){
					if (obj.data != null) {				
						for (var i = 0; i < obj.data.length; i++) {
							var temp = "<input id="+obj.data[i].payCode+" name=\"payType\" type=\"radio\" >" + obj.data[i].payDesc+"</input><br/>";
							$(".pay-list").append(temp);
						}
					}
				} else {
					alert(obj.msg);
				}
			}
		}
	);

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

function submitOrder() {
	$.ajax({
		type : "POST",
		url : "http://www.gmall.com/gmall/order/orderConfirm.shtml",
		async : false,
		data : {"consigneeId" : 1,"payType":4},
		dataType: "json",
		success : function(obj) {
			if (obj.data != null) {
				window.location.href = obj.data.payOrderUrl;
			}
			}
		}
	);
}
