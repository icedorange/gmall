$(document).ready(function(){	
	$.each(province, function (index, obj) { 
		var option = "<option value='" + obj.ProID + "'>" + obj.name + "</option>";
		$("#province").append(option);
	});
	
	$("#province").change(function () {
		var selValue = $(this).val(); 
		$("#city option:gt(0)").remove();
		$.each(city, function (index, obj) { 
			if (obj.ProID == selValue) {
				var option = "<option value='" + obj.CityID + "'>" + obj.name + "</option>";
				$("#city").append(option);
			}
		});
	});

	$("#city").change(function () {
		var selValue = $(this).val(); 
		$("#area option:gt(0)").remove();
		$.each(area, function (index, obj) { 
			if (obj.CityID == selValue) {
				var option = "<option value='" + obj.Id + "'>" + obj.DisName + "</option>";
				$("#area").append(option);
			}
		});
	});
	//商品详情
	$.ajax({
		type : "POST",
		url : $.gmallHost+"/goods/goodsDetails.shtml",
		data : {id : document.URL.getQuery("id")},
		async : false,
		dataType: "json",
		success : function(obj) {
				if(obj.code==1){
					if (obj.data != null) {
						var goods = obj.data;
						var picture = "http://localhost:80/gmall/" + goods.picture;
						$(".tb-s310 img").attr({"src":picture,"rel":picture});
						$(".tb-s310 a").attr({"href":picture});
						$(".tb-detail-hd h1").text(goods.product);
						$(".sys_item_price").text(goods.promotionPrice/100);
						$(".sys_item_mktprice").text(goods.originalPrice/100);
					}
				} else {
					alert(obj.msg);
				}
			}
		}
	);
});