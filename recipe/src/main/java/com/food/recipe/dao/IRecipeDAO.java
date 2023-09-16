package com.food.recipe.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.food.recipe.model.CtgInfVO;
import com.food.recipe.model.IdntOrderVO;
import com.food.recipe.model.RcpCommentVO;
import com.food.recipe.model.RecipeFilterVO;
import com.food.recipe.model.RecipeVO;

public interface IRecipeDAO {
	
	int recipeInsert(RecipeVO vo); // 레시피 인서트
	void OrderInsert(IdntOrderVO vo); // 요리순서 인서트
	ArrayList<RecipeVO> listAllRecipe(RecipeFilterVO vo); // 모든 레시피 가져오기
	int noticeCnt(); // 레시피 수
	ArrayList<CtgInfVO> listCtg(); // 카테고리 불러오기
	ArrayList<RecipeVO> listSearchRecipe(RecipeFilterVO vo); // 레시피 카테고리
	RecipeVO detailRecipe(int rcpNo); // 레시피 하나 가져오기
	ArrayList<IdntOrderVO> orderOne(int rcpNo);
	ArrayList<RcpCommentVO> listComment(HashMap<String, Integer> map); // 댓글 가져오기
	void commentInsert(RcpCommentVO vo); // 댓글 입력
	void commentUpdate(RcpCommentVO vo); // 댓글 수정
	void commentDelete(RcpCommentVO vo); // 댓글 삭제
	int noticeSearchCnt(RecipeFilterVO vo); // 검색된 레시피 수
	String ctgToString(int ctgNo); // 번호를 스트링으로
	void viewPlus(int rcpNo);
}
