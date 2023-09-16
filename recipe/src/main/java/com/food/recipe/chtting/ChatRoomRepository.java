package com.food.recipe.chtting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChatRoomRepository {

    @Autowired
    private ChatDAO dao;

    // 내가 만든거
    public ArrayList<RoomVO> findAllRooms(String memId){
        
    	ArrayList<RoomVO> result = dao.roomAllList(memId);
        Collections.reverse(result);
        
        return result;
    }
    public void createRoomVO(String memNick, String memId){
    	// 닉네임을 아이디로
    	String guest = dao.nickToId(memNick);
    	if(guest != null && guest!="") {
    		RoomVO vo = new RoomVO();
    		
    		String[] arr = new String[2];
    		arr[0] = memId;
    		arr[1] = guest;
    		Arrays.sort(arr);
    		String roomName = arr[0] + "_" + arr[1];
    		if(dao.roomCheck(roomName)!=1) {
    			vo.setRoomName(roomName);
    			vo.setHost(memId);
    			vo.setGuest(guest);
    			dao.roomCreate(vo);
    		}else {
    			
    		}
    	}
    }
    // 여기까지
}