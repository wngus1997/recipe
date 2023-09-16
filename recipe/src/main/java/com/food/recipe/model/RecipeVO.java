package com.food.recipe.model;

public class RecipeVO {
	private int rcpNo;
	private int state;  // 이거 자바스크립트로 버튼 누르면 그거에 맞게 가져와야돼
	private int view;
	private int grade;
	private String memId;
	private String rcpTitle;
	private String rcpDcb;
	private String caut;
	private String foodName;
	private String rcpDate;
	private int ctg1;
	private int ctg2;
	private int ctg3;
	private String idnt;
	private String repImg;
	private String comImg;
	private String memNick;
	
	public String getMemNick() {
		return memNick;
	}
	public void setMemNick(String memNick) {
		this.memNick = memNick;
	}
	public int getRcpNo() {
		return rcpNo;
	}
	public void setRcpNo(int rcpNo) {
		this.rcpNo = rcpNo;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getRcpTitle() {
		return rcpTitle;
	}
	public void setRcpTitle(String rcpTitle) {
		this.rcpTitle = rcpTitle;
	}
	public String getRcpDcb() {
		return rcpDcb;
	}
	public void setRcpDcb(String rcpDcb) {
		this.rcpDcb = rcpDcb;
	}
	public String getCaut() {
		return caut;
	}
	public void setCaut(String caut) {
		this.caut = caut;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getRcpDate() {
		return rcpDate;
	}
	public void setRcpDate(String rcpDate) {
		this.rcpDate = rcpDate;
	}
	public int getCtg1() {
		return ctg1;
	}
	public void setCtg1(int ctg1) {
		this.ctg1 = ctg1;
	}
	public int getCtg2() {
		return ctg2;
	}
	public void setCtg2(int ctg2) {
		this.ctg2 = ctg2;
	}
	public int getCtg3() {
		return ctg3;
	}
	public void setCtg3(int ctg3) {
		this.ctg3 = ctg3;
	}
	public String getIdnt() {
		return idnt;
	}
	public void setIdnt(String idnt) {
		this.idnt = idnt;
	}
	public String getRepImg() {
		return repImg;
	}
	public void setRepImg(String repImg) {
		this.repImg = repImg;
	}
	public String getComImg() {
		return comImg;
	}
	public void setComImg(String comImg) {
		this.comImg = comImg;
	}
	
}
