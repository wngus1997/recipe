package com.food.recipe.service;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.food.recipe.dao.IMemberDAO;
import com.food.recipe.mail.MailUtils;
import com.food.recipe.mail.MailVO;
import com.food.recipe.mail.TempKey;
import com.food.recipe.model.MemberVO;

@Service
public class MemberService implements IMemberService {
	
	@Autowired
	@Qualifier("IMemberDAO")
	IMemberDAO dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	JavaMailSender mailSender;
	
	@Value("${google.id}")
	String googleId;
	
	@Value("${google.pw}")
	String googlePw;
	
	@Override
	public void insertMember(MemberVO vo) {
		
		// 비밀번호를 암호화
		String encodedPassword = passwordEncoder.encode(vo.getMemPw());
		
		vo.setMemPw(encodedPassword);
		
		// 현재 시간
		// long timeNum = System.currentTimeMillis(); date나 calendar에 비해 정확함
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
		String currentTime = fmt.format(new Date());
		
		vo.setMemDate(currentTime);
		
		dao.insertMember(vo);
		
	}

	@Override
	public boolean loginCheck(HashMap<String, String> map) {
		
		String encodedPw = dao.loginCheck((String)map.get("memId"));
		
		boolean result = false;
		
		if(encodedPw != null && passwordEncoder.matches((String)map.get("memPw"), encodedPw)) {
			result = true;
		}
		
		return result;
	}

	@Override
	public int memIdCheck(String memId) {
		// TODO Auto-generated method stub
		return dao.memIdCheck(memId);
	}

	@Override
	public int memNickCheck(String memNick) {
		// TODO Auto-generated method stub
		return dao.memNickCheck(memNick);
	}
	
	
	@Override
	public int favorCheck(int rcpNo, String memId) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rcpNo", rcpNo);
		map.put("memId", memId);
		
		return dao.favorCheck(map);
	}

	@Override
	public int favorTF(int rcpNo, String memId) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rcpNo", rcpNo);
		map.put("memId", memId);
		int TF = dao.favorCheck(map);
		if(TF == 0) {
			dao.favorInsert(map);
			dao.rcpFavorInsert(rcpNo);
			return 1;
		}else{
			dao.favorDelete(map);
			dao.rcpFavorDelete(rcpNo);
			return 0;
		}
		
	}

	@Override
	public MailVO mailAuthInsert(MailVO vo){
		
		String mail_Key = new TempKey().getKey(15, false);
		String memId = new TempKey().getKey(10, false);
		vo.setMailKey(mail_Key);
		vo.setMailTime(0);
		vo.setMemId(memId);
		dao.mailAuthInsert(vo);
		
		try {
			MailUtils sendMail = new MailUtils(mailSender);
			sendMail.setSubject("[자취요리 인증메일 입니다.]");
			sendMail.setText(
					"<h1>메일인증</h1><br>"+
					"아래 [이메일 인증 확인]을 눌러주세요.<br>" +
					"<a href='http://localhost:8080/mail/auth?mailKey=" + mail_Key +
					"&email=" + vo.getEmail() + "&memId=" + memId +"' target='_blank'>이메일 인증 확인</a>");
			sendMail.setFrom(googleId, "자취요리");
			sendMail.setTo(vo.getEmail());
			sendMail.send();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public int mailAuthChk(MailVO vo) {
		return dao.mailAuthChk(vo);
	}

	@Override
	public void mailAuthUpdate(MailVO vo) {
		dao.mailAuthUpdate(vo);
	}

	@Override
	public int emailChk(String email) {
		// TODO Auto-generated method stub
		return dao.emailChk(email);
	}

	@Override
	public int findID(String memEmail) {
		String memId = dao.findID(memEmail);
		if(memId == "" || memId == null) {
			return 0;
		}else {
			try {
				MailUtils sendMail = new MailUtils(mailSender);
				sendMail.setSubject("[자취요리 메일 입니다.]");
				sendMail.setText(
						"<h1>아이디 찾기</h1><br>"+
						"회원님의 아이디는 ["+ memId + "] 입니다");
				sendMail.setFrom(googleId, "자취요리");
				sendMail.setTo(memEmail);
				sendMail.send();
			} catch (MessagingException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return 1;
		}
	}

	@Override
	public int findPW(String memId, String memEmail) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("memId", memId);
		map.put("memEmail", memEmail);
		int val = dao.findPW(map);
		
		if(val==1) {
			
			String pw = new TempKey().getKey(15, false);
			String mail_Key = new TempKey().getKey(15, false);
			
			// 인증키 추가
			
			MailVO vo = new MailVO();
			
			vo.setMailKey(mail_Key);
			vo.setMailTime(0);
			vo.setMemId(memId);
			vo.setEmail(memEmail);
			dao.mailAuthInsert(vo);
			
			try {
				MailUtils sendMail = new MailUtils(mailSender);
				sendMail.setSubject("[자취요리 메일 입니다.]");
				sendMail.setText(
						"<h1>비밀번호 변경</h1><br>"+
						"아래 [비밀번호 변경]을 누르면 [" + pw + "]로 비밀번호가 변경됩니다.<br>" +
						"<a href='http://localhost:8080/findPWChange?pw=" + pw + "&mail_Key=" + mail_Key +
						"&memId=" + memId + "&memEmail=" + memEmail +"' target='_blank'>비밀번호 변경</a><br>"+
						"<h3>변경된 비밀번호 : " + pw + "</h3>");
				sendMail.setFrom(googleId, "자취요리");
				sendMail.setTo(memEmail);
				sendMail.send();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 1;
		}else {
			return 0;
		}
	}

	@Override
	public void findPWChange(String memId, String memPw) {
		
		String encodedPassword = passwordEncoder.encode(memPw);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("memId", memId);
		map.put("memPw", encodedPassword);
		
		dao.findPWChange(map);
		
	}

	

}
