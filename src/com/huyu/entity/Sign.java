package com.huyu.entity;

public class Sign {
	private int id;
	private int user_id;//用户ID
	private String signdate;//签到时间精确到年月日2017/06/16
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getSigndate() {
		return signdate;
	}
	public void setSigndate(String signdate) {
		this.signdate = signdate;
	}
	public Sign(int id, int user_id, String signdate) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.signdate = signdate;
	}
	public Sign(int user_id, String signdate) {
		super();
		this.user_id = user_id;
		this.signdate = signdate;
	}
	public Sign() {
		super();
	}
	

}
