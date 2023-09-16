/**
 * findID.js
 */


$(document).ready(function(){
	
	$("#findIDBtn").on("click", function(){
		event.preventDefault();
		var memEmail = $("#memEmail").val();
		$.ajax({ 
			type:"post",
			url:"/findID",
			data: {"memEmail":memEmail},
			success:function(result){
				if(result == 1){
					alert("해당 이메일로 아이디를 발송하였습니다.");
				}else{
					alert("가입된 정보가 없습니다.");
				}
			
			},
		});
	});
	
	$("#findPWBtn").on("click", function(){
		event.preventDefault();
		var memEmail = $("#memEmail2").val();
		var memId = $("#memId").val();
		$.ajax({ 
			type:"post",
			url:"/findPW",
			data: {"memEmail":memEmail,
				   "memId":memId},
			success:function(result){
				if(result == 1){
					alert("해당 이메일로 비밀번호를 발송하였습니다.");
				}else{
					alert("가입된 정보가 없습니다.");
				}
			
			},
		});
	});
	
});




