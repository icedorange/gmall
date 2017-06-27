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
	
});