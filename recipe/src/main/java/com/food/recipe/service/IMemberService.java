package com.food.recipe.service;

import java.util.HashMap;

import com.food.recipe.mail.MailVO;
import com.food.recipe.model.MemberVO;

public interface IMemberService {
	
	public void insertMember(MemberVO vo); // 회원정보 등록
	public boolean loginCheck(HashMap<String, String> map); // 로그인 체크
	int emailChk(String email);
	int memIdCheck(String memId);
	int memNickCheck(String memNick);
	MailVO mailAuthInsert(MailVO vo);
	int mailAuthChk(MailVO vo); 
	void mailAuthUpdate(MailVO vo);
	int favorCheck(int rcpNo, String memId); // 즐겨찾기 되어있는지 찾기
	int favorTF(int rcpNo, String memId); //즐겨찾기 추가
	int findID(String memEmail);
	int findPW(String memId, String memEmail);
	void findPWChange(String memId, String memPw); // 찾은 후 비밀번호 변경
}
