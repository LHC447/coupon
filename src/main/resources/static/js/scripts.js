$(".coupon-create button[type=submit]").click(addCoupon);
function addCoupon(e){
	e.preventDefault();
	console.log("click");

	var queryString = $(".coupon-create").serialize();
	console.log("query: " + queryString);
	
	var url = $(".coupon-create").attr("action");
	console.log("url: " + url);

//=============================================
	 $.ajax({
		  url : "/coupon/pageble",
		  type : "post",
		  dataType : 'json',
		  success : function(data) {
			  console.log(data);
			  if(data.numberOfElements < 5){
					$.ajax({
					    type : 'post',
					    url : url,
					    data : queryString,
					    dataType : 'json',
					    error : onError,
					    success : function(data,status) {
					    	console.log(data);
					    	var couponTemplate = $("#couponTemplate").html();
					    	var template = couponTemplate.format(data.id, data.email, data.code, data.formattedCreateDate);
					    	$(".coupon-list").append(template);
					 	  }
					});
			  } else{
					$.ajax({
					    type : 'post',
					    url : url,
					    data : queryString,
					    dataType : 'json',
					    error : onError,
					    success : function(data,status) {
					    	console.log(data);
					    	var couponTemplate = $("#couponTemplate").html();
					    	var template = couponTemplate.format(data.id, data.email, data.code, data.formattedCreateDate);
					 	  }
					});
			  }  
		  }
		 }); 
			
	
	
//	2. 버튼 눌렀을때 중복email 경고문 띄우기		
	 var query = {email : $("#email").val()};
	 
	 $.ajax({
	  url : "/coupon/checkEmail",
	  type : "post",
	  data : query,
	  success : function(data) {
	  
	   if(data == 1) {
	    $(".result .msg").text("이미 등록된 이메일입니다.");
	    $(".result .msg").attr("style", "color:#f00");     
	    
	   } else{
		$(".result .msg").text("");   
		
	   }
	  }
	 }); 
	
}

function onError(){
	
}

/*function onSuccess(data, status){
	console.log(data);
	var couponTemplate = $("#couponTemplate").html();
	var template = couponTemplate.format(data.id, data.email, data.code, data.formattedCreateDate);
	$(".coupon-list").append(template);
	
}*/

String.prototype.format = function() {
	  var args = arguments;
	  return this.replace(/{(\d+)}/g, function(match, number) {
	    return typeof args[number] != 'undefined'
	        ? args[number]
	        : match
	        ;
	  });
	};

//============================================
//3. input값에 중복email 들어왔을때  경고문 띄우기
	
$("#email").keyup(function(){
		 
		 var query = {email : $("#email").val()};
		 
		 $.ajax({
		  url : "/coupon/checkEmail",
		  type : "post",
		  data : query,
		  success : function(data) {
		  
		   if(data == 1) {
		    $(".result .msg").text("이미 등록된 이메일입니다.");
		    $(".result .msg").attr("style", "color:#f00");     
		    
		   } else{
			$(".result .msg").text("");   
			
		   }
		  }
		 });  // ajax 
		 
		});


