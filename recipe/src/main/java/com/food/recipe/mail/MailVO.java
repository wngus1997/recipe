package com.food.recipe.mail;

public class MailVO {
	private int mailTime;
	private String mailKey;
	private int mailAuth;
	private String email;
	private String memId;
	private int value;
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public int getMailTime() {
		return mailTime;
	}
	public void setMailTime(int mailTime) {
		this.mailTime = mailTime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMailKey() {
		return mailKey;
	}
	public void setMailKey(String mailKey) {
		this.mailKey = mailKey;
	}
	public int getMailAuth() {
		return mailAuth;
	}
	public void setMailAuth(int mailAuth) {
		this.mailAuth = mailAuth;
	}
	
}
