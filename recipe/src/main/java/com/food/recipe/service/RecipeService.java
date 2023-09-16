package com.food.recipe.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.food.recipe.dao.IRecipeDAO;
import com.food.recipe.model.CtgInfVO;
import com.food.recipe.model.IdntOrderVO;
import com.food.recipe.model.RcpCommentVO;
import com.food.recipe.model.RecipeFilterVO;
import com.food.recipe.model.RecipeVO;

@Service
public class RecipeService implements IRecipeService {
	
	@Autowired
	@Qualifier("IRecipeDAO")
	IRecipeDAO dao;
	
	@Override
	public int recipeInsert(RecipeVO vo) {
		
		return dao.recipeInsert(vo);
	}

	@Override
	public void OrderInsert(IdntOrderVO vo) {
		dao.OrderInsert(vo);
	}

	@Override
	public ArrayList<RecipeVO> listAllRecipe(RecipeFilterVO vo) {
		
		return dao.listAllRecipe(vo);
	}

	@Override
	public int noticeCnt() {
		
		return dao.noticeCnt();
	}

	@Override
	public ArrayList<CtgInfVO> listCtg() {
		
		return dao.listCtg();
	}

	@Override
	public ArrayList<RecipeVO> listSearchRecipe(RecipeFilterVO vo) {
		return dao.listSearchRecipe(vo);
	}

	@Override
	public RecipeVO detailRecipe(int rcpNo){
		
		RecipeVO recipe = dao.detailRecipe(rcpNo);
		
		String date = recipe.getRcpDate();
		recipe.setRcpDate(date.substring(0, 4)+"-"+date.substring(4, 6)+"-"+date.substring(6, 8));

		return recipe;
	}

	@Override
	public ArrayList<RcpCommentVO> listComment(int rcpNo, int top) {
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("rcpNo", rcpNo);
		map.put("top", top);
		
		return dao.listComment(map);
	}

	@Override
	public void commentInsert(RcpCommentVO vo) {
		dao.commentInsert(vo);
		
	}

	@Override
	public void commentUpdate(RcpCommentVO vo) {
		dao.commentUpdate(vo);
		
	}

	@Override
	public void commentDelete(RcpCommentVO vo) {
		dao.commentDelete(vo);
		
	}

	@Override
	public String ctgToString(int ctgNo) {
		// TODO Auto-generated method stub
		return dao.ctgToString(ctgNo);
	}

	@Override
	public ArrayList<IdntOrderVO> orderOne(int rcpNo) {
		
		return dao.orderOne(rcpNo);
	}

	@Override
	public void viewPlus(int rcpNo) {
		dao.viewPlus(rcpNo);
		
	}

	@Override
	public int noticeSearchCnt(RecipeFilterVO vo) {
		// TODO Auto-generated method stub
		return dao.noticeSearchCnt(vo);
	}
	
}
