package com.huyu.entity;

public class Goods_type_type {
	private int id;
	private String type;//商品类型
	private int goods_type_id;//类型类别ID
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
	public int getGoods_type_id() {
		return goods_type_id;
	}
	public void setGoods_type_id(int goods_type_id) {
		this.goods_type_id = goods_type_id;
	}
	public Goods_type_type(int id, String type, int goods_type_id) {
		super();
		this.id = id;
		this.type = type;
		this.goods_type_id = goods_type_id;
	}
	public Goods_type_type(String type, int goods_type_id) {
		super();
		this.type = type;
		this.goods_type_id = goods_type_id;
	}
	public Goods_type_type() {
		super();
	}
	
	

}
