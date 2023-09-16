package com.food.recipe.model;

public class RcpCommentVO {
	
	private int cmtNo;
	private int rcpNo;
	private int top;
	private String memNick;
	private String memId;
	private String cmtDcb;
	private String cmtDate;
	
	
	public String getCmtDate() {
		return cmtDate;
	}
	public void setCmtDate(String cmtDate) {
		this.cmtDate = cmtDate;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public int getCmtNo() {
		return cmtNo;
	}
	public void setCmtNo(int cmtNo) {
		this.cmtNo = cmtNo;
	}
	public int getRcpNo() {
		return rcpNo;
	}
	public void setRcpNo(int rcpNo) {
		this.rcpNo = rcpNo;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public String getMemNick() {
		return memNick;
	}
	public void setMemNick(String memNick) {
		this.memNick = memNick;
	}
	public String getCmtDcb() {
		return cmtDcb;
	}
	public void setCmtDcb(String cmtDcb) {
		this.cmtDcb = cmtDcb;
	}
}
