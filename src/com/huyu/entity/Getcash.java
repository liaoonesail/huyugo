package com.huyu.entity;

/**
 * 银行卡提现
 * @author Administrator
 *
 */
public class Getcash {
	private int id;
	private int userId;//用户Id
	private double price;//提现金额
	private int status;//提现状态
	private String time;//申请时间
	private Userinfo userinfo;//用户信息
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public Userinfo getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}
	public Getcash(int id, int userId, double price, int status, String time) {
		super();
		this.id = id;
		this.userId = userId;
		this.price = price;
		this.status = status;
		this.time = time;
	}
	public Getcash(int id, int userId, double price, int status, String time,
			Userinfo userinfo) {
		super();
		this.id = id;
		this.userId = userId;
		this.price = price;
		this.status = status;
		this.time = time;
		this.userinfo = userinfo;
	}
	public Getcash() {
		super();
	}

}
