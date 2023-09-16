/**
 * insertForm.js
 */
 var stepId = 2;
 
$(document).ready(function(){
	
	// 이미지취소와 미리보기삭제
	$(document).on("click",".imgDel",function(){
		
		var imgBox = $(this).parent();
		var fileBox = $(this).parent().prev();
		
		event.preventDefault();
		imgBox.css("cursor","pointer");
		fileBox.val('');
		imgBox.attr("for", fileBox.attr("id"));
		$(this).parent().empty();
		
	});
	
	
	// 저장 누름
	$(document).on("click","#save",function(){
		
		if($('input[name="rcpTitle"]').val().trim() == ""){
			alert("제목을 입력해주세요.");
			
			return false;
		}
		
		$("#state").val(0);
		idntSet();
		
		var save = confirm('저장하시겠습니까?');
		if(save){
			$("#contentBox").submit();
		}
	});
	
	// 공개 누름
	$(document).on("click","#public",function(){
		
		if($('input[name="rcpTitle"]').val().trim() == ""){
			alert("제목을 입력해주세요.");
			$('input[name="rcpTitle"]').focus();
			return false;
		}else if($('input[name="mainImg"]').val() == ""){
			alert("대표 이미지를 업로드해주세요.");
			return false;
		}else if($('select[name="ctg1"]').val() == "0" ||
				 $('select[name="ctg2"]').val() == "0" ||
				 $('select[name="ctg3"]').val() == "0"){
			alert("카테고리를 선택해주세요.");
			$('select[name="ctg1"]').focus();
			return false;
		}else if($('input[name="foodName"]').val().trim() == ""){
			alert("음식명을 입력해주세요");
			$('input[name="foodName"]').focus();
			return false;
		}else if($('textarea[name="ordDcb"]').val().trim() == ""){
			alert("요리순서를 입력해주세요");
			$('input[name="ordDcb"]').focus();
			return false;
		}
		
		$("#state").val(1);
		idntSet();
		var public = confirm('공개하시겠습니까?');
		if(public){
			$("#contentBox").submit();
		}
	});
	
	//취소
	$(document).on("click","#cancel",function(){
		
		var cf = confirm('취소하고 나가시겠습니까?');
		if(cf){
			location.href="/";
		}
		
	});
	
});// ready함수 끝

// 미리보기추가
function setThumbnail(event, a) {
	var reader = new FileReader();

	reader.onload = function(event) {
		var img = document.createElement("img");
		img.setAttribute("src", event.target.result);
		$("#"+a).next().append(img);
		$("#"+a).next().append("<div class='imgDel'><i class='fa-solid fa-xmark'></i></div>");
		$("#"+a).next().css("cursor","default");
		$("#"+a).next().attr("for", "");
	};
	reader.readAsDataURL(event.target.files[0]);
}

// 재료 데이터 추가
function idntSet(){

	var idnt = new Object();
	var idntArray = new Array();
	
	$(".idntBox").each(function (index, idntBox_tag) {

		var idntTitle = $(idntBox_tag).find(".idntTitle").find("input").val();
		
		var idntBox = new Object();
			idntBox.idntTitle = idntTitle;
			
		var idntOneArray = new Array();
		$(idntBox_tag).find(".idntOne").each(function (j, one) {
			
			var idntName = $(one).find(".idntName").find("input").val();
			var idntQty = $(one).find(".idntQty").find("input").val();
			var idntOne = new Object();
			idntOne.idntName = idntName;
			idntOne.idntQty = idntQty;
			
			idntOneArray.push(idntOne);
		});
		
		idntBox.idntOne = idntOneArray;
		idntArray.push(idntBox);
	});
	
	idnt.idntArray = idntArray;
	idnt = JSON.stringify(idnt);
	//idnt = idnt.replace(/\"/g,"&quot;");
	$("#idnt").val(idnt);
} //재료 데이터 추가 끝

// 묶음 삭제
function idntBoxDel(e){
	var del = $(e).parent().parent().parent();
	del.fadeOut("normal", function() { $(this).remove(); });
}// 묶음 삭제 끝

// 재료 하나 삭제
function idntOneDel(e){ // 스텝 박스도 같이 해놨음
	var del = $(e).parent();
	del.fadeOut("normal", function() { $(this).remove(); });
}// 재료 하나 삭제

// 재료 하나 추가
function idntOneAdd(e){
	$(e).parent().prev().append(
	'<div class="idntOne">'+
		'<div><i class="fa-solid fa-circle ui-state-default"></i></div>'+
		'<div class="idntName">'+
			'<input type="text">'+
		'</div>'+
		'<div class="idntQty">'+
			'<input type="text">'+
		'</div>'+
		'<div onclick="idntOneDel(this)"><i class="fa-regular fa-circle-xmark"></i></div>'+
	'</div>');
}

// 재료묶음 추가
function idntBoxAdd(e){

	$(e).parent().prev().append(
	'<div class="idntBox">'+
		'<div class="idntTitle">'+
			'<div>'+
				'<div><i class="fa-solid fa-circle ui-state-default"></i></div>'+
				'<div><input type="text" value="재료"></div>'+
			'</div>'+
			'<div class="bdleIdnt_del">'+
				'<button type="button" onclick="idntBoxDel(this)"><i class="fa-solid fa-xmark"></i>&nbsp;묶음 삭제</button>'+
			'</div>'+
		'</div>'+
		'<div class="idntNameBox">'+
			'<div class="indntOneSpace">'+
				'<div class="idntOne">'+
					'<div><i class="fa-solid fa-circle ui-state-default"></i></div>'+
					'<div class="idntName">'+
						'<input type="text" placeholder="돼지고기">'+
					'</div>'+
					'<div class="idntQty">'+
						'<input type="text" placeholder="300g">'+
					'</div>'+
					'<div onclick="idntOneDel(this)"><i class="fa-regular fa-circle-xmark"></i></div>'+
				'</div>'+
				'<div class="idntOne">'+
					'<div><i class="fa-solid fa-circle ui-state-default"></i></div>'+
					'<div class="idntName">'+
						'<input type="text" placeholder="돼지고기">'+
					'</div>'+
					'<div class="idntQty">'+
						'<input type="text" placeholder="300g">'+
					'</div>'+
					'<div onclick="idntOneDel(this)"><i class="fa-regular fa-circle-xmark"></i></div>'+
				'</div>'+
			'</div>'+
			'<div class="idntOne_add"><button type="button" onclick="idntOneAdd(this)"><i class="fa-solid fa-plus"></i>&nbsp;추가</button></div>'+
		'</div>'+
	'</div>'
	)
	$(function() {
	    $(".indntOneSpace").sortable();
	    $(".indntOneSpace").disableSelection();
	});
	
}

// 요리순서 삭제
function stepDel(e){
	var del = $(e).parent();
	del.remove();
	
	a();
}// 요리순서 삭제


// 요리순서 추가
function stepBoxAdd(e){
	
	$(e).parent().prev().append(
		'<div class="stepBox">'+
			'<div class="stepNum"><div class="ui-state-default">Step1</div></div>'+
			'<div><textarea name="ordDcb"></textarea></div>'+
			'<div>'+
				'<i class="fa-solid fa-plus"></i>'+
				'<input type="file" id="stepImg' + stepId + '" class="hidden" name="ordImg" onChange="setThumbnail(event, this.id);">'+
				'<label class="stepImg" for="stepImg' + stepId + '"></label>'+
			'</div>'+
			'<div onclick="stepDel(this)"><i class="fa-regular fa-circle-xmark"></i></div>'+
		'</div>'
	);
	a();
	stepId++;
}

// 순서대로 정렬
function a(){
	$(".stepNum").each(function (index, stepNum) {
		$(stepNum).children().text("Step"+(index+1));
	});
}

$(function() {
	$("#stepDiv").sortable({
    	
		stop : function() {
			a();
		}
	});
    $("#stepDiv").disableSelection();
    $("#idntDiv").sortable();
    $("#idntDiv").disableSelection();
    $(".indntOneSpace").sortable();
    $(".indntOneSpace").disableSelection();
});