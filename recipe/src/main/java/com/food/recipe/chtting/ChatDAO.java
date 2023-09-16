package com.food.recipe.chtting;

import java.util.ArrayList;
import java.util.HashMap;

public interface ChatDAO {
	void roomCreate(RoomVO vo); // 방 생성
	ArrayList<RoomVO> roomAllList(String memId); // 방 전체 불러오기
	int roomCheck(String roomName); // 방 생성전 중복 확인
	int roomAuthority(HashMap<String, Object> map); // 방 수정 권한 있는지 확인
	RoomVO roomOne(int roomNo);
	void roomDelete(int roomNo); // 방 삭제
	void roomUpdate(HashMap<String, Object> map); // 방 나가기
	void chatInsert(ChatVO vo);
	ArrayList<ChatVO> chatAll(int roomNo);
	String nickToId(String memNick);
	String idToNick(String memId);
}
