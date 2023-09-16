package com.food.recipe.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.food.recipe.mail.MailVO;
import com.food.recipe.model.MemberVO;
import com.food.recipe.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService service;
	
	
	// 회원가입 창
	@RequestMapping("/member/joinForm")
	public String joinForm() {
		
		return "/member/joinForm";
	}
	
	// 회원정보 등록
	@RequestMapping("/member/insert")
	public String join(MemberVO vo) {
		
		service.insertMember(vo);
		
		return "member/loginForm";
	}
	
	// 로그인 창
	@RequestMapping("/member/loginForm")
	public String loginForm() {
		
		return "member/loginForm";
	}
	
	// 로그인
	@ResponseBody
	@RequestMapping("/member/login")
	public boolean login(@RequestParam HashMap<String, String> param, HttpSession session) {
		
		// 로그인 체크
		boolean result = service.loginCheck(param);
		
		if(result == true) {
			
			session.setAttribute("sid", param.get("memId"));
			
		}
		// requestHttpServlet getHeader이용해서 회원가입 창이 아니면 그 전 url로 돌아가게 하기
		return result;
	}
	
	// 로그아웃
	@RequestMapping("/member/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	@ResponseBody
	@RequestMapping("/member/memIdChk")
	public int memIdChk(String memId) {
		return service.memIdCheck(memId);
	}
	@ResponseBody
	@RequestMapping("/member/memNickChk")
	public int memNickChk(String memNick) {
		return service.memNickCheck(memNick);
	}
	@ResponseBody
	@RequestMapping("/emailInsert")
	public MailVO emailInsert(String email) {
		
		MailVO vo = new MailVO();
		vo.setEmail(email);
//		if(service.emailChk(email) != 0) {
//			vo.setValue(1);
//		}
//		else {
			vo.setValue(0);
			vo = service.mailAuthInsert(vo);
			vo.setMailKey("");
//		}
		
		return vo;
	}
	@RequestMapping("/mail/auth")
	public String emailAuth(String mail_Key, String email, String memId) {
		
		MailVO vo = new MailVO();
		vo.setEmail(email);
		vo.setMailKey(mail_Key);
		vo.setMailAuth(0);
		vo.setMemId(memId);
		if(service.mailAuthChk(vo)==1) {
			vo.setMailAuth(1);
			service.mailAuthUpdate(vo);
			return "member/authS";
		}else {
			
			return "member/authF";
		}
	}
	@ResponseBody
	@RequestMapping("/member/mailAuthChk")
	public int emailAuthChk(MailVO vo) {
		vo.setMailAuth(1);
		return service.mailAuthChk(vo);
	}
	@ResponseBody
	@RequestMapping("/findID")
	public int findID(String memEmail) {
		return service.findID(memEmail);
	}
	@ResponseBody
	@RequestMapping("/findPW")
	public int findPW(String memEmail, String memId) {
		
		return service.findPW(memId, memEmail);
	}
	@ResponseBody
	@RequestMapping("/findPWChange")
	public String findPWChange(String pw, String memId, String mail_Key, String memEmail) {
		
		MailVO vo = new MailVO();
		
		vo.setMailAuth(0);
		vo.setMemId(memId);
		vo.setEmail(memEmail);
		int val = service.mailAuthChk(vo);
		if(val == 1) {
			vo.setMailAuth(1);
			service.mailAuthUpdate(vo);
			service.findPWChange(memId, pw);
			return "비밀번호 변경 성공";
		}else {
			//실패
			return "비밀번호 변경 실패";
		}
		
		
		
	}
	
	
}
