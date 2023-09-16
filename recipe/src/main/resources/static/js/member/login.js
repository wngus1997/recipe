/**
 * login.js
 */

 
$(document).ready(function(){

	var a = function(){setTimeout(function() {$('#memMesBox').animate({top:-100}, 300);}, 2000);};

	$("#memId").on("keyup",function(key){
        if(key.keyCode==13) {
            login();
        }
    });
	$("#memPw").on("keyup",function(key){
        if(key.keyCode==13) {
            login();
        }
    });
	$('#loginForm').on('submit', function(){
		login();
	});
});
function login(){
	event.preventDefault();
	
	var memId = $('#memId').val();
	var memPw = $('#memPw').val();
	
	console.log(memId);
	
	if(memId == null || memId == ""){
		
		$('#memMesBox').html("아이디를 입력해주세요");
		$('#memMesBox').animate({top:0}, 300);
		$('#memId').focus();
		a();
		
		return;
	};
	
	if(memPw == null || memPw == ""){
		
		$('#memMesBox').html("비밀번호를 입력해주세요");
		$('#memMesBox').animate({top:0}, 300);
		$('#memPw').focus();
		
		setTimeout(function() {
			$('#memMesBox').animate({top:-100}, 300);
		}, 2000);
		
		return;
	};
	
	// 서버에 전송하고 결과 받아서 처리
	$.ajax({
		type:"post",
		url:"/member/login",
		data: {"memId":$('#memId').val(),
		"memPw":$('#memPw').val()},
		dataType:'text',
		success:function(result){
			if(result == "true"){
				location.href="/";
			}else{
				$('#memMesBox').html("아이디 또는 비밀번호가 일치하지 않습니다.");
				$('#memMesBox').animate({top:0}, 300);
				
				setTimeout(function() {
					$('#memMesBox').animate({top:-100}, 300);
				}, 2000);
			}
		
		},
	});
}