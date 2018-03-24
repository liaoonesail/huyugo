package com.huyu.entity;

public class Dixianjin_details {
	private int id;
	private int user_id;
	private int order_id;
	private int goods_id;
	private int status;//是否使用0否 1是
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
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Dixianjin_details(int id, int user_id, int order_id, int goods_id,
			int status) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.order_id = order_id;
		this.goods_id = goods_id;
		this.status = status;
	}
	public Dixianjin_details(int user_id, int order_id, int goods_id, int status) {
		super();
		this.user_id = user_id;
		this.order_id = order_id;
		this.goods_id = goods_id;
		this.status = status;
	}
	public Dixianjin_details() {
		super();
	}
	

}
