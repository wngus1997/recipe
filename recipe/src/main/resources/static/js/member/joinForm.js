/**
 * joinForm.js
 */
var idMes = false;
var pwMes = false;
var nickMes = false;
var mailMes = false;
var pwStart = false;
var emailMemId = "";
$(document).ready(function(){
	
	$("#joinBtn").on("click", function(){
		event.preventDefault();
		var email = $("#memEmailF").val() + "@" + $("#memEmailB").val();
		$("#memEmail").val(email);
		console.log($("#memEmail").val());
		$.ajax({ 
			type:"post",
			url:"/member/mailAuthChk",
			data: {"memId":emailMemId,
				   "email":email},
			dataType:'text',
			async : false,
			success:function(result){
				if(result == 1){
					mailMes = true;
				}else{
					alert("이메일 인증을 완료해주세요");
					mailMes = false;
				}
			
			},
		});
		if(!mailMes){
			return false;
		}
		var a = [idMes, pwMes, nickMes];
		
		for(var i = 0; i<a.length;i++){
			if(a[i] == false){
				alert("빈 곳이 있는지 다시 확인해주세요");
				return false;
			}
		}
		$("#joinForm").submit();
	});
	
	
	$("#memId").on("change", function(){
		var idMes = false;
	});
	$("#memNick").on("change", function(){
		var nickMes = false;
	});
	$("#memPw").on("change", function(){
		if(pwStart){
			pwPwchk();
		}
	});
	$("#memPwChk").on("change", function(){
		pwStart = true;
		pwPwchk();
	});
	
	$("#memIdBtn").on("click", function(){
		var regExp = /^[a-z]+[a-z0-9]{3,8}$/g;
		var memId = $('#memId').val();
		var chk = regExp.test($('#memId').val());
		if(!chk || memId.length>12){
			alert("아이디는 영문, 숫자 4~12자까지 가능합니다");	
			return false;
		}
		$.ajax({ 
			type:"post",
			url:"/member/memIdChk",
			data: {"memId":$('#memId').val()},
			dataType:'text',
			success:function(result){
				if(result == 0){
					$('#idMes').empty();
					$("#idMes").html("사용 가능한 아이디입니다.");
					$("#idMes").css("color", "green");
					idMes = true;
				}else{
					$('#idMes').empty();
					$("#idMes").html("사용 불가능한 아이디입니다.");
					$("#idMes").css("color", "red");
					idMes = false;
					
				}
			
			},
		});
	});
	
	$("#memNickBtn").on("click", function(){
		var memNick = $('#memNick').val();
		if(memNick.length>10 || memNick.length<2){
			alert("2~10자까지 가능합니다");	
			return false;
		}
		$.ajax({ 
			type:"post",
			url:"/member/memNickChk",
			data: {"memNick":$('#memNick').val()},
			dataType:'text',
			success:function(result){
				if(result == 0){
					$('#nickMes').empty();
					$("#nickMes").html("사용 가능한 닉네임입니다.");
					$("#nickMes").css("color", "green");
					nickMes = true;
				}else{
					$('#nickMes').empty();
					$("#nickMes").html("사용 불가능한 닉네임입니다.");
					$("#nickMes").css("color", "red");
					nickMes = false;
					
				}
			
			},
		});
	});
	
	$("#emailBtn").on("click", function(){
		
		var email = $("#memEmailF").val() + "@" + $("#memEmailB").val();
		
		$.ajax({ 
			type:"post",
			url:"/emailInsert",
			data: {"email":email},
			success:function(result){
				if(result.value==1){
					alert("이미 등록된 이메일입니다.");
				}else{
					alert("이메일 인증이 발송되었습니다.");
					emailMemId =result.memId;
				}
				
			},
		});
	});
	
	
});

function pwPwchk(){
	var pwChk = $("#memPwChk").val();
	var pw = $("#memPw").val();
	if(pwChk == pw){
		pwMes = true;
		$('#pwMes').empty();
		$("#pwMes").html("비밀번호가 일치합니다.");
		$("#pwMes").css("color", "green");
	}else{
		pwMes = false;
		$('#pwMes').empty();
		$("#pwMes").html("비밀번호가 일치하지 않습니다.");
		$("#pwMes").css("color", "red");
	}
};



