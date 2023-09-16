package com.food.recipe.chtting;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChatController {
	
	@Autowired
    private ChatRoomRepository repository;
	
	@Autowired
    private ChatDAO dao;
	
	@RequestMapping("/chat")
    public String chatAllList(HttpSession session, Model model, @RequestParam(value="memId", required=false, defaultValue="qwerwt") String memId){
		ArrayList<RoomVO> vo = repository.findAllRooms(memId);
		for(int i = 0; i<vo.size(); i++) {
			if(vo.get(i).getHost().equals(memId)) {
				vo.get(i).setMyself(dao.idToNick(vo.get(i).getHost()));
				vo.get(i).setOp(dao.idToNick(vo.get(i).getGuest()));
			}else {
				vo.get(i).setMyself(dao.idToNick(vo.get(i).getGuest()));
				vo.get(i).setOp(dao.idToNick(vo.get(i).getHost()));
			}
		}
		model.addAttribute("roomAllList", vo);
		
		model.addAttribute("memId", memId);
        return "/chat/chatAllList";
    }
	
	@RequestMapping("/chat/create")
    public String chatCreate(HttpSession session, @RequestParam String memNick, @RequestParam String memId){
		repository.createRoomVO(memNick, memId);

        return "redirect:/chat";
    }
	@ResponseBody
    @RequestMapping("/getChat")
    public ArrayList<ChatVO> getChat(int roomNo){
		
        return dao.chatAll(roomNo);
    }
	
	@RequestMapping("/chat/delete")
    public String chatDelete(int roomNo, String memId){
		RoomVO vo = dao.roomOne(roomNo);
		String who = "";
		if(vo.getHost().equals(memId)) {
			who = "host";
		}else if(vo.getGuest().equals(memId)) {
			who = "guest";
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("memId", memId);
    	map.put("roomNo", roomNo);
    	map.put("who", who);
		dao.roomUpdate(map);
		dao.roomDelete(roomNo);
		
		return "redirect:/chat";
    }
}
