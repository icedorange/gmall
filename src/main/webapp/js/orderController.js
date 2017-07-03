$(document).ready(function(){	
	//所有订单
	$.ajax({
		type : "POST",
		url : "http://localhost:80/gmall/order/orderItem.shtml",
		data : {"transactionStatus" : 2000},
		dataType : "json",
		success : function(obj) {
			if (obj.code == 1) {		  
				if (obj.data != null) {
					//方法三
					/*var app4 = new Vue({
						  el: '#app-4',
						  data: {
						    todos: obj.data
						  }
						})*/
					$.each(obj.data, function (index, object) {
						if (object.transactionStatus==2001) {
							var status = "待付款";
						}else if (object.transactionStatus==2002) {
							var status = "待发货";
						}else if (object.transactionStatus==2003) {
							var status = "待收货";
						}else if (object.transactionStatus==2004) {
							var status = "待评价";
						}
						//方法二
						var order = $("mall-for").html().replace
						("{orderid}",object.id).replace
						("{paymentTime}",object.paymentTime).replace
						("{ordersum}",object.sum/100).replace
						("{orderNo}",object.id).replace
						("{transactionStatus}",status).replace
						("{orderN}",object.id).replace
						("{orderInfo}","http://localhost:80/gmall/person/orderinfo.html?id=" + object.id);
						
						$(".order-content").append(order);
						$("mall-for").hide();
						//方法一
						/*$(".order-content").append(
								"<div class=\"order-top\">"+
								"<div class=\"th th-item\">"+
								"<td class=\"td-inner\">"+ object.id+"</td>"+
								"</div>"+
								"<div class=\"th th-price\">"+
									"<td class=\"td-inner\">"+object.paymentTime +"</td>"+
								"</div>"+
								"<div class=\"th th-amount\">"+
									"<td class=\"td-inner\">"+object.sum/100 +"</td>"+
								"</div>"+
								"<div class=\"th th-operation\">"+
									"<td class=\"td-inner\">"+object.id +"</td>"+
								"</div>"+
								"<div class=\"th th-status\">"+
									"<td class=\"td-inner\">"+status+"</td>"+
								"</div>"+
								"<div class=\"th th-change\">"+
									"<td class=\"td-inner\">"+object.id +"</td>"+
								"</div>"+
								"</div>");*/
					});
				}							
			} else {
				alert(obj.msg);
			}					
		}
	});	
});
