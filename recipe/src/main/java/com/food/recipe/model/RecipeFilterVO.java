package com.food.recipe.model;

public class RecipeFilterVO {
	
	private int[] ctg1;
	private int[] ctg2;
	private int[] ctg3;
	private String memNick;
	private String orderBy;
	private String searchInp;
	private String search;
	private int startN;
	private int noticeCnt;
	
	
	public int getStartN() {
		return startN;
	}
	public void setStartN(int startN) {
		this.startN = startN;
	}
	public int getNoticeCnt() {
		return noticeCnt;
	}
	public void setNoticeCnt(int noticeCnt) {
		this.noticeCnt = noticeCnt;
	}
	public String getSearchInp() {
		return searchInp;
	}
	public void setSearchInp(String searchInp) {
		this.searchInp = searchInp;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getMemNick() {
		return memNick;
	}
	public void setMemNick(String memNick) {
		this.memNick = memNick;
	}
	public int[] getCtg1() {
		return ctg1;
	}
	public void setCtg1(int[] ctg1) {
		this.ctg1 = ctg1;
	}
	public int[] getCtg2() {
		return ctg2;
	}
	public void setCtg2(int[] ctg2) {
		this.ctg2 = ctg2;
	}
	public int[] getCtg3() {
		return ctg3;
	}
	public void setCtg3(int[] ctg3) {
		this.ctg3 = ctg3;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
}
