/**
 * index.js
 */
var ctg1 = [];
var ctg2 = [];
var ctg3 = [];
var search = "foodName";
var currentP = 1;
var orderBy = "rcpDate";

$(document).ready(function(){
	$("#ctgOCBox").on('change', function(){
	
		if ($(this).is(':checked')) {
			$("#upIcon").css("display", "inline");
			$("#downIcon").css("display", "none");
		}
		else {
			$("#upIcon").css("display", "none");
			$("#downIcon").css("display", "inline");
		}
		
	});
	
	$("input:checkbox[name*='ctg']").on('change', function(){
		if(this.name == 'ctg1') ctg1 = ctgMethod(this.name);
		if(this.name == 'ctg2') ctg2 = ctgMethod(this.name);
		if(this.name == 'ctg3') ctg3 = ctgMethod(this.name);
	});
	
	$("select[name='search']").on('change', function(){
		search = $(this).val();
	});
	
	$("#searchInp").on("keyup",function(key){
        if(key.keyCode==13) {
        	currentP = 1;
            searchAjax();
        }
    });
	
});

function ctgMethod(ctg){
	var list = [];
	$("input[name='"+ ctg +"']:checked").each(function(i){
		list.push(this.id);
	});
	return list;
}
function curMethod(curP){
	currentP = curP;
	searchAjax();
}
function orderBtn(order){
	orderBy = order;
	//currentP = 1; 이건 다시 첫페이지로 감
	searchAjax();
}
function searchAjax(){
	var searchInp = $("#searchInp").val();
	$.ajax({
		type:"post",
		url:"/recipe/search",
		data: {"ctg1":ctg1,
			   "ctg2":ctg2,
			   "ctg3":ctg3,
			   "search":search,
			   "currentP":currentP,
			   "orderBy":orderBy,
			   "searchInp":searchInp   
		},
		success:function(result){	 
			$('#listPageBox').empty();
			$('#listPageBox').html(result);
			selectedElt();
		},
	});
}

function selectedElt(){
	$("#selectedBox").empty();
	$("input[name='ctg1']:checked").each(function(i){
		var ctg = $(this).val();
		$("#selectedBox").append("<div class='ctg1'>" + ctg + "</div>");
	});
	$("input[name='ctg2']:checked").each(function(i){
		var ctg = $(this).val();
		$("#selectedBox").append("<div class='ctg2'>" + ctg + "</div>");
	});
	$("input[name='ctg3']:checked").each(function(i){
		var ctg = $(this).val();
		$("#selectedBox").append("<div class='ctg3'>" + ctg + "</div>");
	});
}

function reset(){
	ctg1 = [];
	ctg2 = [];
	ctg3 = [];
	currentP = 1;
	$("#searchInp").val("");
	console.log(ctg1);
	console.log(ctg2);
	console.log(ctg3);
	console.log(currentP);
	console.log($("#searchInp").val(""));
	$("input[name*='ctg']:checked").prop("checked", false);
}