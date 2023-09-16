/**
 * detailView.js
 */
 
$(document).ready(function(){
	
	
	
});
function cmtInsert(){
	
	var cmtDcb = $("#cmtDcb").val().replace(/\n/g, '<br>');
	var rcpNo = $("#rcpNo").val();
	
	var a = confirm("댓글을 등록하시겠습니까?");
	if(a){
		$.ajax({
			type:"post",
			url:"/comment/insert",
			data: {"rcpNo":rcpNo,
				   "cmtDcb":cmtDcb},
			success:function(result){
				location.reload();
			},
		});
	}
}

function favorInOut(){
	
	var rcpNo = $("#rcpNo").val();
	$.ajax({
		type:"post",
		url:"/favorInOut",
		data: {"rcpNo":rcpNo},
		success:function(result){
			$('.TF').empty();
			if(result == 1){
				alert("즐겨찾기에 추가되었습니다.");
				$('.TF').html("★");
			}else if(result == 3){
				alert("로그인 후 이용가능합니다.");
				$('.TF').html("☆");
			}else{
				$('.TF').html("☆");
			}
		},
	});
}

function cmtDelete(cmtNo){
	var rcpNo = $("#rcpNo").val();
	var cmtNo = cmtNo;
	var a = confirm("정말로 삭제하시겠습니까?");
	if(a){
		$.ajax({
			type:"post",
			url:"/comment/delete",
			data: {"rcpNo":rcpNo,
				   "cmtNo":cmtNo},
			success:function(result){
				location.reload();
			},
		});
	}
}