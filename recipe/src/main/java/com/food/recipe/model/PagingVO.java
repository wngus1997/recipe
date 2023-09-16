package com.food.recipe.model;

public class PagingVO {
	private int totalP; // 게시글 수와 게시글 몇 개씩에 따라 달라짐, 첫 페이지 = 1, 마지막 페이지 = totalP
	private int currentP; // 디폴트1, jsp에서 받아옴, noticeCnt와 함께 limit 지정
	private int currentJ; // 현재 장, 이게 1이면 이전 비활성화, 이게 endJ랑 같으면 다음 비활성화
	private int endJ; // 마지막 장, pageCnt와 연계됨
	private int noticeCnt; // 사용자가 지정
	private int pageCnt; // 사용자가 지정
	private int totalN; // 모든 게시글 수 DB로 가져옴
	private int startN; //시작하는 게시글
	private int startP; // 시작하는 페이지
	private int endP; // 그 장에서 끝나는 페이지
	

	public PagingVO(int noticeCnt, int pageCnt, int totalN, int currentP) {
		this.noticeCnt = noticeCnt;
		this.pageCnt = pageCnt;
		this.totalN = totalN;
		this.currentP = currentP;
		this.totalP = (totalN%noticeCnt==0) ? totalN/noticeCnt : (totalN/noticeCnt)+1;
		this.currentJ = (currentP%pageCnt==0) ? currentP/pageCnt: (currentP/pageCnt)+1;
		this.endJ = (totalP%pageCnt==0) ? totalP/pageCnt: (totalP/pageCnt)+1;
		this.startN = (currentP-1)*noticeCnt;
		this.startP = (currentJ-1)*pageCnt+1;
		this.endP = startP + pageCnt - 1;
		if(endP>totalP) {
			this.endP = totalP;
		}
	}

	public int getEndP() {
		return endP;
	}
	public void setEndP(int endP) {
		this.endP = endP;
	}
	public int getStartP() {
		return startP;
	}
	public void setStartP(int startP) {
		this.startP = startP;
	}
	public int getStartN() {
		return startN;
	}
	public void setStartN(int startN) {
		this.startN = startN;
	}
	public int getTotalP() {
		return totalP;
	}
	public void setTotalP(int totalP) {
		this.totalP = totalP;
	}
	public int getCurrentP() {
		return currentP;
	}
	public void setCurrentP(int currentP) {
		this.currentP = currentP;
	}
	public int getCurrentJ() {
		return currentJ;
	}
	public void setCurrentJ(int currentJ) {
		this.currentJ = currentJ;
	}
	public int getEndJ() {
		return endJ;
	}
	public void setEndJ(int endJ) {
		this.endJ = endJ;
	}
	public int getNoticeCnt() {
		return noticeCnt;
	}
	public void setNoticeCnt(int noticeCnt) {
		this.noticeCnt = noticeCnt;
	}
	public int getPageCnt() {
		return pageCnt;
	}
	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}
	public int getTotalN() {
		return totalN;
	}
	public void setTotalN(int totalN) {
		this.totalN = totalN;
	} 
}
