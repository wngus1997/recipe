/**
 * chatAllList.js
 */
var sockJs;
var stomp = null;
var roomNo = 0;
var memId = $('#memId').val();

$(document).ready(function(){
	
	$(document).on("change","input[name='roomSelect']",function(e){
		if(stomp != null){
			stomp.disconnect();
		}
		$("#textBox").empty();
		roomNo = this.id;
		memId = $('#memId').val();
		getChatting();
		connect();
	});
	function connect(){
		sockJs = new SockJS("/stomp/chat");
		stomp = Stomp.over(sockJs);
		stomp.connect({}, function(){
			stomp.subscribe("/sub/chat/room/" + roomNo, function (chat) {
				
				var content = JSON.parse(chat.body);
				var sender = content.sender;
				var str = '';
				if(roomNo != 0){
					if(sender == memId){
						str = "<div class='myself'>";
						str += "<div>" + content.message + "</div>";
						str += "</div></div>";
						$("#textBox").append(str);
					}else if(sender == "admin"){
						str = "<div class='admin'>";
						str += "<div>상대방이 채팅을 나가셨습니다.</div>";
						str += "</div>";
						$("#textBox").append(str);
					}else{
						str = "<div class='opponent'>";
						str += "<div>" + content.message + "</div>";
						str += "</div></div>";
						$("#textBox").append(str);
					}
				}
			});
		});
	}
	$(document).on("click","#submitBtn",function(){
		if(roomNo==0){
			return false;
		}
		if($("#textarea").val()==""){
			return false;
		}
	
		var text = $("#textarea").val().replace(/\n/g, '<br>');
        stomp.send('/pub/chat/message', {}, JSON.stringify({"roomNo": roomNo, "message": text, "sender": memId}));
        $("#textarea").val('');
        $("#textarea").css("height", "45px");
	});
	
	$(document).on("keydown","#textarea",function(event){
		if(roomNo==0){
			return false;
		}
		if(event.keyCode == 13){
			if(!event.shiftKey){
				if($("#textarea").val()==""){
					return false;
				}
				event.preventDefault();
				var text = $("#textarea").val().replace(/\n/g, '<br>');
				stomp.send('/pub/chat/message', {}, JSON.stringify({"roomNo": roomNo, "message": text, "sender": memId}));
				$("#textarea").val('');
				$("#textarea").css("height", "45px");
			}
		}
    });
    
    $(document).on("click","#chatDel",function(){
    	
    	if(roomNo==0){
			return false;
		}
    	stomp.send('/pub/chat/message', {}, JSON.stringify({"roomNo": roomNo, "message": "상대방이 채팅을 나가셨습니다.", "sender": "admin"}));
    	$.ajax({ 
			type:"post",
			url:"/chat/delete",
			data: {"roomNo" : roomNo,
				   "memId" : memId},
			success:function(result){
				location.reload();
			},
		});
    });
    
});
function resize(obj) {
	obj.style.height = '45px';
	if(obj.scrollHeight>=90){
		obj.style.height = '99px';
		return false;
	}
	if(obj.scrollHeight>43){
		obj.style.height = (12 + obj.scrollHeight) + 'px';
	}
}
function getChatting(){
	
	$.ajax({ 
		type:"post",
		url:"/getChat",
		data: {"roomNo" : roomNo},
		success:function(result){
			for(var i=0; i < result.length; i++){
				if(result[i].sender == memId){
					str = "<div class='myself'>";
					str += "<div>"+ result[i].message + "</div>";
					str += "</div></div>";
					$("#textBox").append(str);
				}else if(result[i].sender == "admin"){
					str = "<div class='admin'>";
					str += "<div>상대방이 채팅을 나가셨습니다</div>";
					str += "</div>";
					$("#textBox").append(str);
				}else{
					str = "<div class='opponent'>";
					str += "<div>"+ result[i].message + "</div>";
					str += "</div></div>";
					$("#textBox").append(str);
				}
			}
		},
	});
}


