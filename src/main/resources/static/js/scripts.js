$(".coupon-create button[type=submit]").click(addCoupon);
function addCoupon(e){
	e.preventDefault();
	console.log("click");
	
	var queryString = $(".coupon-create").serialize();
	console.log("query: " + queryString);
	
	var url = $(".coupon-create").attr("action");
	console.log("url: " + url);
	
	
	$.ajax({
	    type : 'post',
	    url : url,
	    data : queryString,
	    dataType : 'json',
	    error : onError,
	    success : onSuccess});
}

function onError(){
	
}

function onSuccess(data, status){
	console.log(data);
	var couponTemplate = $("#couponTemplate").html();
	var template = couponTemplate.format(data.id, data.email, data.code, data.formattedCreateDate);
	$(".coupon-list").prepend(template);
	
}

String.prototype.format = function() {
	  var args = arguments;
	  return this.replace(/{(\d+)}/g, function(match, number) {
	    return typeof args[number] != 'undefined'
	        ? args[number]
	        : match
	        ;
	  });
	};





