package com.huyu.entity;

public class Goods_type {
	private int id;
	private String type;//类型类别
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Goods_type(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}
	public Goods_type(String type) {
		super();
		this.type = type;
	}
	public Goods_type() {
		super();
	}
	
	

}
