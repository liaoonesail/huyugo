package com.huyu.entity;

public class Tuangou_Details {
	private int id;
	private int tuangou_id;//团购活动ID
	private int user_id;//用户ID
	private String date_time;//购买时间
	private double pay_amount;//购买数量
	private double pay_money;//花费金额
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTuangou_id() {
		return tuangou_id;
	}
	public void setTuangou_id(int tuangou_id) {
		this.tuangou_id = tuangou_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getDate_time() {
		return date_time;
	}
	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}
	public double getPay_amount() {
		return pay_amount;
	}
	public void setPay_amount(double pay_amount) {
		this.pay_amount = pay_amount;
	}
	public double getPay_money() {
		return pay_money;
	}
	public void setPay_money(double pay_money) {
		this.pay_money = pay_money;
	}
	public Tuangou_Details(int id, int tuangou_id, int user_id,
			String date_time, double pay_amount, double pay_money) {
		super();
		this.id = id;
		this.tuangou_id = tuangou_id;
		this.user_id = user_id;
		this.date_time = date_time;
		this.pay_amount = pay_amount;
		this.pay_money = pay_money;
	}
	public Tuangou_Details(int tuangou_id, int user_id, String date_time,
			double pay_amount, double pay_money) {
		super();
		this.tuangou_id = tuangou_id;
		this.user_id = user_id;
		this.date_time = date_time;
		this.pay_amount = pay_amount;
		this.pay_money = pay_money;
	}
	public Tuangou_Details() {
		super();
	}
	

}
