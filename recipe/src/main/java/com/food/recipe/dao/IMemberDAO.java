package com.food.recipe.dao;

import java.util.HashMap;

import com.food.recipe.mail.MailVO;
import com.food.recipe.model.MemberVO;

public interface IMemberDAO {

	public void insertMember(MemberVO vo); // 회원정보 등록
	public String loginCheck(String id); // 로그인 체크
	int emailChk(String email);
	int memIdCheck(String memId);
	int memNickCheck(String memNick);
	void mailAuthInsert(MailVO vo); // 인증보냈다
	int mailAuthChk(MailVO vo); // 인증요청 및 인증완료 확인
	void mailAuthUpdate(MailVO vo); // 인증했다면 0-> 1
	int favorCheck(HashMap<String, Object> map); // 즐겨찾기 되어있는지 찾기
	void favorInsert(HashMap<String, Object> map); //즐겨찾기 추가
	void rcpFavorInsert(int rcpNo); //즐겨찾기 추가2
	void favorDelete(HashMap<String, Object> map); //즐겨찾기 삭제
	void rcpFavorDelete(int rcpNo); //즐겨찾기 삭제2
	void favorNo(int rcpNo);
	String findID(String memEmail); // 아이디찾기
	int findPW(HashMap<String, String> map); //비밀번호찾기
	void findPWChange(HashMap<String, String> map); // 찾은 후 비밀번호 변경
}
