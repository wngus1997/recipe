package com.food.recipe.chtting;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class StompChatController {
	
	@Autowired
    private SimpMessagingTemplate template; //특정 Broker로 메세지를 전달
	
	@Autowired
    private ChatDAO dao;

    //Client가 SEND할 수 있는 경로
    //stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
    //"/pub/chat/enter"
    @MessageMapping(value = "/chat/message")
    public void message(ChatVO message){
    	
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("memId", message.getSender());
    	map.put("roomNo", message.getRoomNo());
    	if(message.getSender().equals("admin") || dao.roomAuthority(map)==1) {
    		dao.chatInsert(message);
    		template.convertAndSend("/sub/chat/room/" + message.getRoomNo(), message);
    	}
		
    }
}