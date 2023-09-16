package com.food.recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/member/findIDFrom")
	public String findIDFrom() {
		
		return "/member/findID";
	}
	@RequestMapping("/member/findPWFrom")
	public String findPWFrom() {
		
		return "/member/findID";
	}
	@RequestMapping("/top")
	public String top() {
		
		return "/layout/top";
	}
	@RequestMapping("/sideMenu")
	public String sideMenu() {
		
		return "/layout/sideMenu";
	}
	@RequestMapping("/mypage/meminf")
	public String memInf() {
		
		return "/mypage/memberInf";
	}
}
