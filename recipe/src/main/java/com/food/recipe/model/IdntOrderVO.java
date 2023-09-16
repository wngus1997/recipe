package com.food.recipe.model;

public class IdntOrderVO {
	
	private int orderNo;
	private int rcpNo;
	private int step;
	private String ordDcb;
	private String ordImg;
	
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getRcpNo() {
		return rcpNo;
	}
	public void setRcpNo(int rcpNo) {
		this.rcpNo = rcpNo;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public String getOrdDcb() {
		return ordDcb;
	}
	public void setOrdDcb(String ordDcb) {
		this.ordDcb = ordDcb;
	}
	public String getOrdImg() {
		return ordImg;
	}
	public void setOrdImg(String ordImg) {
		this.ordImg = ordImg;
	}
	
}
