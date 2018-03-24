package com.huyu.entity;
/**
 * 
 * @author onesail 低价促销
 *
 */
public class Cuxiao {
	private int id;
	private double real_price;
	private int goods_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getReal_price() {
		return real_price;
	}
	public void setReal_price(double real_price) {
		this.real_price = real_price;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public Cuxiao(int id, double real_price, int goods_id) {
		super();
		this.id = id;
		this.real_price = real_price;
		this.goods_id = goods_id;
	}
	public Cuxiao(double real_price, int goods_id) {
		super();
		this.real_price = real_price;
		this.goods_id = goods_id;
	}
	public Cuxiao() {
		super();
	}
	
}
