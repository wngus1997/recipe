package com.food.recipe.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.food.recipe.model.CtgInfVO;
import com.food.recipe.model.IdntOrderVO;
import com.food.recipe.model.IdntVO;
import com.food.recipe.model.PagingVO;
import com.food.recipe.model.RcpCommentVO;
import com.food.recipe.model.RecipeFilterVO;
import com.food.recipe.model.RecipeVO;
import com.food.recipe.service.FileService;
import com.food.recipe.service.MemberService;
import com.food.recipe.service.RecipeService;

@Controller
public class RecipeController {

	private FileService fileService;
	private RecipeService rcpService;
	private MemberService memService;
	
	@Autowired
	public RecipeController(FileService fileService, RecipeService rcpService, MemberService memService) {
		super();
		this.fileService = fileService;
		this.rcpService = rcpService;
		this.memService = memService;
		
	}
	
	@RequestMapping("/")
	public String index(Model model, RecipeFilterVO vo,
			@RequestParam(value="currentP", required=false, defaultValue="1") int currentP
			) {
		
		PagingVO page = new PagingVO(10, 5, rcpService.noticeCnt(), currentP);
		
		vo.setStartN(page.getStartN());
		vo.setNoticeCnt(page.getNoticeCnt());
		
		ArrayList<RecipeVO> recipeList = rcpService.listAllRecipe(vo);
		ArrayList<CtgInfVO> ctgList = rcpService.listCtg();
		int index = 0;
		if(recipeList.size()%5 != 0) {
			index = 5 - recipeList.size()%5;
		}
		
		
		model.addAttribute("ctgList", ctgList);
		model.addAttribute("recipeList", recipeList);
		model.addAttribute("page", page);
		model.addAttribute("index", index);
		
		return "index";
	}
	
	@RequestMapping("/recipe/search")
	public String Filter(Model model, RecipeFilterVO vo,
			@RequestParam(value="currentP", required=false, defaultValue="1") int currentP
			) {
		
		PagingVO page = new PagingVO(10, 5, rcpService.noticeSearchCnt(vo), currentP);
		
		vo.setStartN(page.getStartN());
		vo.setNoticeCnt(page.getNoticeCnt());
		
		ArrayList<RecipeVO> recipeList = rcpService.listSearchRecipe(vo);		
		ArrayList<CtgInfVO> ctgList = rcpService.listCtg();
		int index = 0;
		if(recipeList.size()%5 != 0) {
			index = 5 - recipeList.size()%5;
		}
		
		model.addAttribute("ctgList", ctgList);
		model.addAttribute("recipeList", recipeList);
		model.addAttribute("page", page);
		model.addAttribute("index", index);
		
		
		return "/recipe/recipeBox";
	}
	
	@RequestMapping("/recipe/insert")
	public String reInsert(RecipeVO rcpVO, 
			@RequestParam("comImg1") MultipartFile[] comImg,
			@RequestParam("ordImg") MultipartFile[] ordImg, 
			@RequestParam("ordDcb") String[] ordDcb, 
			@RequestParam("mainImg") MultipartFile mainImg
			, HttpSession session) throws IOException {
		
		String memId = (String)session.getAttribute("sid");
		// 대표이미지 업로드 및 이름 반환
		if(!(mainImg.isEmpty())) {
			rcpVO.setRepImg(fileService.fileUpload(mainImg));
		}
		// comImg 파일 이름 값 넣기
		String comImgName = "";
		
		for(int i = 0; i<comImg.length;i++) {
			
			if(comImg[i] != null && !(comImg[i].isEmpty())) {
				comImgName += fileService.fileUpload(comImg[i]) + ",";
			}
		}
		rcpVO.setComImg(comImgName.replaceAll(",$", ""));
		
		// 현재 날짜
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar c = Calendar.getInstance();
        rcpVO.setRcpDate(sdf.format(c.getTime()));
		rcpVO.setMemId(memId);
		
		rcpService.recipeInsert(rcpVO);
		int rcpNo = rcpVO.getRcpNo();
		
		// 요리순서 설정해서 DB에 넣기
		for(int i = 0; i<ordDcb.length;i++) {
			if(ordDcb[i].trim() == "") {
				break;
			}
			IdntOrderVO vo = new IdntOrderVO();
			
			vo.setStep(i+1);
			vo.setOrdDcb(ordDcb[i]);
			
			if(ordImg[i] != null && !(ordImg[i].isEmpty())) {
				vo.setOrdImg(fileService.fileUpload(ordImg[i]));
			}
			
			vo.setRcpNo(rcpNo);
			
			rcpService.OrderInsert(vo);
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/recipe/insertForm")
	public String reInsertForm(Model model) {
		
		ArrayList<CtgInfVO> ctgList = rcpService.listCtg();
		
		model.addAttribute("ctgList", ctgList);
		
		return "/recipe/insertForm";
	}
	
	@RequestMapping("/recipe/detailView/{rcpNo}")
	public String detailView(@PathVariable("rcpNo") int rcpNo, Model model, HttpSession session,
							HttpServletRequest request,
							HttpServletResponse response) {
		
		String memId = (String)session.getAttribute("sid");
		
		RecipeVO recipe = rcpService.detailRecipe(rcpNo);
		ArrayList<RcpCommentVO> cmtList = rcpService.listComment(rcpNo, 0);
		int favorCheck = memService.favorCheck(rcpNo, memId);
		String ctg1 = rcpService.ctgToString(recipe.getCtg1());
		String ctg2 = rcpService.ctgToString(recipe.getCtg2());
		String ctg3 = rcpService.ctgToString(recipe.getCtg3());
		ArrayList<IdntOrderVO> orderOne = rcpService.orderOne(rcpNo);
		
		model.addAttribute("recipe", recipe);
		model.addAttribute("cmtList", cmtList);
		model.addAttribute("ctg1", ctg1);
		model.addAttribute("ctg2", ctg2);
		model.addAttribute("ctg3", ctg3);
		model.addAttribute("favorCheck", favorCheck);
		model.addAttribute("orderOne", orderOne);
		
		String[] idntTitle  = null;
		ArrayList<IdntVO> idnt = new ArrayList<>();
		String a = recipe.getIdnt();
		if(!(recipe.getIdnt().isEmpty())) {
			JSONObject obj = new JSONObject(recipe.getIdnt());
			JSONArray idntArray = obj.getJSONArray("idntArray");

			if(idntArray.length() != 0) {
				
				idntTitle = new String[idntArray.length()];
				
				for(int i=0; i<idntArray.length(); i++) {
					idntTitle[i] = idntArray.getJSONObject(i).getString("idntTitle");
					JSONArray idntOne = idntArray.getJSONObject(i).getJSONArray("idntOne");
					for(int j=0; j<idntOne.length(); j++) {
						IdntVO vo = new IdntVO();
						vo.setIdntName(idntOne.getJSONObject(j).getString("idntName"));
						vo.setIdntQty(idntOne.getJSONObject(j).getString("idntQty"));
						vo.setIdntTitle(idntTitle[i]);
						
						idnt.add(vo);
					}
				}
			}
		}	
		model.addAttribute("idntTitle", idntTitle);
		model.addAttribute("idnt", idnt);
		model.addAttribute("memId", memId);
		LocalTime now = LocalTime.now();
		int h = now.getHour();
        int m = now.getMinute();
        int s = now.getSecond();
		Cookie[] cookies = request.getCookies();
		Cookie myCookie = null;
		if(cookies!=null) {
			for(Cookie c: cookies) {
				if(c.getName().equals("views")) {
					myCookie = c;
				}
			}
		}if(myCookie == null) {
			Cookie c = new Cookie("views", rcpNo+"");
			c.setMaxAge(24*60*60-h*60*60-m*60-s);
			response.addCookie(c);
			rcpService.viewPlus(rcpNo);
		}else {
			if(!myCookie.getValue().contains("" + rcpNo)) {
				rcpService.viewPlus(rcpNo);
				myCookie.setMaxAge(24*60*60-h*60*60-m*60-s);
				myCookie.setValue(myCookie.getValue()+"/"+rcpNo);
				response.addCookie(myCookie);
			}
		}
		return "/recipe/detailView";
	}
	
	@ResponseBody
	@RequestMapping("/favorInOut")
	public int favorInOut(@RequestParam("rcpNo") int rcpNo, HttpSession session) {
		
		
		String memId = (String)session.getAttribute("sid");
		
		if(memId == null) {return 3;}
		int a = memService.favorTF(rcpNo, memId);
		return a;
	}
	@ResponseBody
	@RequestMapping("/comment/insert")
	public void commentInsert(RcpCommentVO vo, HttpSession session) {
		
		String memId = (String)session.getAttribute("sid");
		vo.setMemId(memId);
		rcpService.commentInsert(vo);
		System.out.println(vo.getCmtDcb());
		System.out.println(vo.getRcpNo());
	}
	
	@RequestMapping("/comment/update")
	public void commentUpdate(RcpCommentVO vo, HttpSession session) {
		
		String memId = (String)session.getAttribute("sid");
		vo.setMemId(memId);
		rcpService.commentUpdate(vo);
	}
	@ResponseBody
	@RequestMapping("/comment/delete")
	public void commentDelete(RcpCommentVO vo, HttpSession session) {
		
		String memId = (String)session.getAttribute("sid");
		vo.setMemId(memId);
		rcpService.commentDelete(vo);
	}
	
}
