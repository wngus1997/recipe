package com.food.recipe.service;

import java.util.ArrayList;

import com.food.recipe.model.CtgInfVO;
import com.food.recipe.model.IdntOrderVO;
import com.food.recipe.model.RcpCommentVO;
import com.food.recipe.model.RecipeFilterVO;
import com.food.recipe.model.RecipeVO;

public interface IRecipeService {
	int recipeInsert(RecipeVO vo);
	void OrderInsert(IdntOrderVO vo);
	ArrayList<RecipeVO> listAllRecipe(RecipeFilterVO vo);
	int noticeCnt();
	ArrayList<CtgInfVO> listCtg();
	ArrayList<RecipeVO> listSearchRecipe(RecipeFilterVO vo);
	RecipeVO detailRecipe(int rcpNo); // 레시피 하나 가져오기
	ArrayList<IdntOrderVO> orderOne(int rcpNo); // 요리순서 가져오기
	ArrayList<RcpCommentVO> listComment(int rcpNo, int top); // 댓글 가져오기
	void commentInsert(RcpCommentVO vo); // 댓글 입력
	void commentUpdate(RcpCommentVO vo); // 댓글 수정
	void commentDelete(RcpCommentVO vo); // 댓글 삭제
	String ctgToString(int ctgNo); // 번호를 스트링으로
	void viewPlus(int rcpNo);
	int noticeSearchCnt(RecipeFilterVO vo); // 검색된 레시피 수
}
