package com.huyu.entity;

public class Vip_order {
	private int id;
	private int user_id;//用户ID
	private String order_num;//订单号
	private String order_time;//下单时间
	private String payment;//支付方式
	private double total;//订单金额 500
	private int status;//订单状态
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
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	public String getOrder_time() {
		return order_time;
	}
	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Vip_order(int id, int user_id, String order_num, String order_time,
			String payment, double total, int status) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.order_num = order_num;
		this.order_time = order_time;
		this.payment = payment;
		this.total = total;
		this.status = status;
	}
	public Vip_order(int user_id, String order_num, String order_time,
			String payment, double total, int status) {
		super();
		this.user_id = user_id;
		this.order_num = order_num;
		this.order_time = order_time;
		this.payment = payment;
		this.total = total;
		this.status = status;
	}
	public Vip_order() {
		super();
	}
	

}
