package com.huyu.entity;


public class Car {
	private int id;
	private int goods_id;//商品ID
	private int amount;//商品数量
	private int user_id;//用户ID
	private double real_price;//商品真实价格
	private String color_norms;//颜色和规格
	private double integral;//积分购买
	private int type;//1,2,3 积分兑换，vip商城，vip券
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public double getReal_price() {
		return real_price;
	}
	public void setReal_price(double real_price) {
		this.real_price = real_price;
	}
	
	public String getColor_norms() {
		return color_norms;
	}
	public void setColor_norms(String color_norms) {
		this.color_norms = color_norms;
	}

	public double getIntegral() {
		return integral;
	}

	public void setIntegral(double integral) {
		this.integral = integral;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Car(int id, int goods_id, int amount, int user_id, double real_price, String color_norms, double integral, int type) {
		this.id = id;
		this.goods_id = goods_id;
		this.amount = amount;
		this.user_id = user_id;
		this.real_price = real_price;
		this.color_norms = color_norms;
		this.integral = integral;
		this.type = type;
	}

	public Car(int goods_id, int amount, int user_id, double real_price, String color_norms, double integral, int type) {
		this.goods_id = goods_id;
		this.amount = amount;
		this.user_id = user_id;
		this.real_price = real_price;
		this.color_norms = color_norms;
		this.integral = integral;
		this.type = type;
	}

	public Car() {
		super();
	}

	@Override
	public String toString() {
		return "Car{" +
				"id=" + id +
				", goods_id=" + goods_id +
				", amount=" + amount +
				", user_id=" + user_id +
				", real_price=" + real_price +
				", color_norms='" + color_norms + '\'' +
				", integral=" + integral +
				", type=" + type +
				'}';
	}
}
